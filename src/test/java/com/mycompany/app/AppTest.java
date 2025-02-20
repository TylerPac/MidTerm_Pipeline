package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddTask() {
        ResponseEntity<String> response = restTemplate.postForEntity("/tasks/add?task=Buy groceries", null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List> tasksResponse = restTemplate.getForEntity("/tasks/list", List.class);
        assertTrue(tasksResponse.getBody().contains("Buy groceries"));
    }

    @Test
    public void testRemoveTask() {
        restTemplate.postForEntity("/tasks/add?task=Walk the dog", null, String.class);
        restTemplate.delete("/tasks/remove?task=Walk the dog");

        ResponseEntity<List> tasksResponse = restTemplate.getForEntity("/tasks/list", List.class);
        assertFalse(tasksResponse.getBody().contains("Walk the dog"));
    }

    @Test
    public void testGetTasks() {
        restTemplate.postForEntity("/tasks/add?task=Read a book", null, String.class);
        ResponseEntity<List> response = restTemplate.getForEntity("/tasks/list", List.class);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testAddInvalidTask() {
        restTemplate.postForEntity("/tasks/add?task=", null, String.class);
        ResponseEntity<List> response = restTemplate.getForEntity("/tasks/list", List.class);

        assertFalse(response.getBody().contains(""));
    }
}
