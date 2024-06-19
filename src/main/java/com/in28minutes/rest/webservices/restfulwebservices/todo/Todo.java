package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;


@Entity
public class Todo {

	public Todo() {
		
	}
	public Todo(boolean check){
		super();
		this.targetDate = LocalDate.now();
 	}
	public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done) {
		super();
		this.todoId = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	@Id
	@GeneratedValue
	private Integer todoId;

	private String username;
	
	private String description;
	private LocalDate targetDate;
	private boolean done;


	public Integer getId() {
		return todoId;
	}

	public void setId(Integer id) {
		this.todoId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}



	@Override
	public String toString() {
		return "Todo [id=" + todoId + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}

}