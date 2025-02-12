package com.example.tasks.services.impl;

import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.repositiories.TaskListRepository;
import com.example.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepositort;

    public TaskListServiceImpl(TaskListRepository taskListRepositort) {
        this.taskListRepositort = taskListRepositort;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepositort.findAll();
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
        return taskListRepositort.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
