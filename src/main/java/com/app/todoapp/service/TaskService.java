package com.app.todoapp.service;

import com.app.todoapp.exceptions.ToDoExceptions;
import com.app.todoapp.mapper.TaskInDTOToTask;
import com.app.todoapp.persistence.entity.Task;
import com.app.todoapp.persistence.entity.TaskStatus;
import com.app.todoapp.persistence.repository.TaskRepository;
import com.app.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    //Principio Solid (D: Dependency Inversion Principle)
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){//Mapper
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);

    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }
    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not Found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
