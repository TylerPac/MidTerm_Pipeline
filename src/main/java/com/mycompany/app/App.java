package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class App {
    private List<String> tasks;

    public App() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (task != null && !task.trim().isEmpty()) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    public boolean containsTask(String task) {
        return tasks.contains(task);
    }
}
