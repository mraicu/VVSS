package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testTaskCreation() throws ParseException {
        try {
            task = new Task("Task1",
                    Task.getDateFormat().parse("2025-04-03 10:00"),
                    Task.getDateFormat().parse("2025-04-06 20:05"),
                    5
            );
            task.setActive(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("Task1", task.getTitle());
        assertEquals("2025-04-03 10:00", task.getFormattedDateStart());
        assertEquals("2025-04-06 20:05", task.getFormattedDateEnd());
        assertEquals(5, task.getRepeatInterval());
        assertTrue(task.isActive());

        try {
            task = new Task( null,
                    Task.getDateFormat().parse("2025-04-07 05:00"),
                    Task.getDateFormat().parse("2025-04-10 12:23"),
                    7
            );
            task.setActive(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("", task.getTitle());
        assertEquals("2025-04-07 05:00", task.getFormattedDateStart());
        assertEquals("2025-04-10 12:23", task.getFormattedDateEnd());
        assertEquals(5, task.getRepeatInterval());
        assertTrue(task.isActive());

        try {
            task = new Task("Task2",
                    Task.getDateFormat().parse("2025-04-15 07:00"),
                    Task.getDateFormat().parse("2025-04-20 13:05"),
                    -200
            );
            task.setActive(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("Task1", task.getTitle());
        assertEquals("2025-04-03 10:00", task.getFormattedDateStart());
        assertEquals("2025-04-06 20:05", task.getFormattedDateEnd());
        assertEquals(5, task.getRepeatInterval());
        assertTrue(task.isActive());

        try {
            task = new Task("Task3",
                    Task.getDateFormat().parse("data inceput"),
                    Task.getDateFormat().parse("2025-04-10 13:05"),
                    7
            );
            task.setActive(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("Task1", task.getTitle());
        assertEquals("2025-04-03 10:00", task.getFormattedDateStart());
        assertEquals("2025-04-06 20:05", task.getFormattedDateEnd());
        assertEquals(5, task.getRepeatInterval());
        assertTrue(task.isActive());

        try {
            task = new Task("Task4",
                    Task.getDateFormat().parse("2025-04-15 10:00"),
                    Task.getDateFormat().parse("2025-04-20 20:05"),
                    -7
            );
            task.setActive(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("Task1", task.getTitle());
        assertEquals("2025-04-03 10:00", task.getFormattedDateStart());
        assertEquals("2025-04-06 20:05", task.getFormattedDateEnd());
        assertEquals(5, task.getRepeatInterval());
        assertTrue(task.isActive());
    }

    @AfterEach
    void tearDown() {
    }
}