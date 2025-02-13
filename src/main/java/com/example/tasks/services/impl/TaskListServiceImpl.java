package com.example.tasks.services.impl;

import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.repositiories.TaskListRepository;
import com.example.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepositorty) {
        this.taskListRepository = taskListRepositorty;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()){
            throw new IllegalArgumentException("Task List already has an ID");
        };

        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId()){
            throw new IllegalArgumentException("Task list must have an Id");
        }

        if(!Objects.equals(taskList.getId(), taskListId)){
            throw new IllegalArgumentException("Attempting to change task list ID, not permitted");
        }

        TaskList exisitingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
                new IllegalArgumentException("Task list not found"));

        exisitingTaskList.setTitle(taskList.getTitle());
        exisitingTaskList.setDescription(taskList.getDescription());
        exisitingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(exisitingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
