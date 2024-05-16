package com.ssafy.enjoytrip.model.dto;

public class TripPlan {
	private int id;
	private String name;
	private String route;
	private String userId;

	@Override
	public String toString() {
		// json
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"route\":" + route + ", \"userId\":\"" + userId
				+ "\"}";
	}

	public TripPlan() {
		super();
	}

	public TripPlan(int id, String name, String route, String userId) {
		this.id = id;
		this.name = name;
		this.route = route;
		this.userId = userId;
	}

	public TripPlan(String name, String route, String userId) {
		this.name = name;
		this.route = route;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	 public boolean isValid() {
	        return name != null && !name.isEmpty() &&
	               route != null && !route.isEmpty() &&
	               userId != null;
	    }
}
