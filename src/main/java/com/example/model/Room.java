package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
	@JsonProperty("room")
	private String roomId;
	
	public Room (String id) {
		this.roomId = id;
	}
	
	public String getId() {
		return this.roomId;
	}
	
	

}
