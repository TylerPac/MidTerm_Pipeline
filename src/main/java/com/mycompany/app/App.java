package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/tasks")
public class App {
    private List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(9090); // Change 9090 to any available port
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        if (task != null && !task.trim().isEmpty()) {
            tasks.add(task);
            return "Task added: " + task;
        }
        return "Invalid task";
    }

    @DeleteMapping("/remove")
    public String removeTask(@RequestParam String task) {
        if (tasks.remove(task)) {
            return "Task removed: " + task;
        }
        return "Task not found";
    }

    @GetMapping("/list")
    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    @GetMapping("/")
    public ResponseEntity<String> getHomePage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/index.html");
        String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok().body(content);
    }
}
