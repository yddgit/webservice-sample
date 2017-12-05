package com.my.project.model;

public class User {
	private String username;
	private String password;
	private String nickname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public User() {
		super();
	}

	public User(String username, String password, String nickname) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", nickname=" + nickname + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof User && this.username != null) {
			User u = (User)obj;
			if(this.username.equals(u.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
