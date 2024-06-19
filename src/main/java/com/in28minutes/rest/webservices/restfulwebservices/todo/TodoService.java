package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;

	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
		return todo;
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
	public Todo updateTodoStatus(Integer id, boolean isDone) {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		if (optionalTodo.isPresent()) {
			Todo todo = optionalTodo.get();
			todo.setDone(isDone);
			return todoRepository.save(todo);
		}
		return null;
	}
}