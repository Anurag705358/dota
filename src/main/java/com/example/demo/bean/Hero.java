package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Hero {

	@JsonProperty(index=0)
	private int id;
	@JsonProperty(value="name",index=1)
	private String name;
	@JsonProperty(value="localized_name",index=2)
	private String localizedName;
	
	
	@JsonProperty("hero_id")
	public int getHeroId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocalizedName() {
		return localizedName;
	}
	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

}

