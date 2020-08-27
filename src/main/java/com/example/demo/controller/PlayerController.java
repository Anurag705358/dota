package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Hero;
import com.example.demo.bean.PlayerDetails;

@RestController
public class PlayerController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/api/{accountId}")
	public List<Hero> getInfo(@PathVariable String accountId) {
		String uri = "https://api.opendota.com/api/players/" + accountId + "/heroes";

		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().set("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");// Set
																																				// the
																																				// header
																																				// for
																																				// each
																																				// request
				return execution.execute(request, body);
			}
		});
		ResponseEntity<PlayerDetails[]> response = restTemplate.getForEntity(uri, PlayerDetails[].class);
		PlayerDetails[] player = response.getBody();
		List<PlayerDetails> answer;
		if (player != null) {
			answer = Arrays.stream(player).collect(Collectors.toList());
			return findTop3Details(answer);
		}
		return Collections.emptyList();
	}

	private List<Hero> findTop3Details(List<PlayerDetails> answer) {
		Comparator<PlayerDetails> reverseComp = Comparator.comparingInt(x -> -x.getGames());
		answer.sort(reverseComp);
		Set<String> idSet = answer.stream().map(PlayerDetails::getHeroId).limit(3).collect(Collectors.toSet());
		String heroApi = "https://api.opendota.com/api/heroes";
		ResponseEntity<Hero[]> response = restTemplate.getForEntity(heroApi, Hero[].class);
		Hero[] hero = response.getBody();

		if (hero == null) {
			return Collections.emptyList();
		}

		return Arrays.stream(hero).filter(x -> idSet.contains(String.valueOf(x.getHeroId())))
				.collect(Collectors.toList());
	}

}