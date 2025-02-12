package com.example.tasks.services.impl;

import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.repositiories.TaskListRepository;
import com.example.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepositort;

    public TaskListServiceImpl(TaskListRepository taskListRepositort) {
        this.taskListRepositort = taskListRepositort;
    }


    @Override
    public List<TaskList> listTaskList() {
        return taskListRepositort.findAll();
    }
}
