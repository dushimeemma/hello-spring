package com.todo.todo.service;


import com.todo.todo.models.Todo;
import com.todo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo newTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(newTodo.getTitle());
                    todo.setDescription(newTodo.getDescription());
                    todo.setCompleted(newTodo.isCompleted());
                    return todoRepository.save(todo);
                }).orElseThrow(() -> new RuntimeException("Todo not found!"));
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
