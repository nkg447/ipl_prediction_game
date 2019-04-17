package com.ipl.service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
	private String pid;
	private String name;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return pid == player.pid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pid);
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				'}';
	}
}
