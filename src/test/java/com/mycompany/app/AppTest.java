
package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void testAddTask() {
        app.addTask("Buy groceries");
        assertTrue(app.containsTask("Buy groceries"));
    }

    @Test
    public void testRemoveTask() {
        app.addTask("Buy groceries");
        app.removeTask("Buy groceries");
        assertFalse(app.containsTask("Buy groceries"));
    }

    @Test
    public void testGetTasks() {
        app.addTask("Buy groceries");
        app.addTask("Walk the dog");
        assertEquals(2, app.getTasks().size());
    }

    @Test
    public void testAddInvalidTask() {
        app.addTask("");
        app.addTask(null);
        assertTrue(app.getTasks().isEmpty());
    }
}
