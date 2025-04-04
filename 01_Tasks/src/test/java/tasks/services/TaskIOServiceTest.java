package tasks.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskIOServiceTest {
    private Task task;

    @BeforeAll
    static void BeforeAll() {
        TaskIOService.setTaskList(new ArrayList<>());
    }

    @Test
    void TC1_ECP() {

        try {
            task = new Task("Task1",
                    Task.getDateFormat().parse("2025-04-03 10:00"),
                    Task.getDateFormat().parse("2025-04-06 20:05"),
                    5
            );
            task.setActive(true);
            TaskIOService.add(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Task lastTask = null;
        try {
            lastTask = TaskIOService.getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Task1", lastTask.getTitle());
        assertEquals("2025-04-03 10:00", lastTask.getFormattedDateStart());
        assertEquals("2025-04-06 20:05", lastTask.getFormattedDateEnd());
        assertEquals(5, lastTask.getRepeatInterval());
        assertTrue(lastTask.isActive());

    }


    @Test
    void TC2_ECP() {
        try {
            task = new Task("Task3",
                    Task.getDateFormat().parse("data inceput"),
                    Task.getDateFormat().parse("2025-04-10 13:05"),
                    7
            );
            task.setActive(false);
            TaskIOService.add(task);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("Unparseable date: \"data inceput\"", e.getMessage());
        }
    }

    @Test
    void TC3_ECP() {

        try {
            task = new Task("Task4",
                    Task.getDateFormat().parse("2025-04-15 10:00"),
                    Task.getDateFormat().parse("2025-04-20 20:05"),
                    -7
            );
            task.setActive(false);
            TaskIOService.add(task);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("interval should be > 0", e.getMessage());
        }
    }

    @Test
    void TC1_BVA() {
        try {
            task = new Task("",
                    Task.getDateFormat().parse("2025-04-07 05:00"),
                    Task.getDateFormat().parse("2025-04-10 12:23"),
                    7
            );
            task.setActive(false);
            TaskIOService.add(task);
        } catch (Exception e) {
            assertEquals("Title cannot be empty", e.getMessage());

            e.printStackTrace();
        }
    }

    @Test
    void TC12_BVA() {
        try {
            task = new Task("Task4",
                    Task.getDateFormat().parse("2025-04-15 10:00"),
                    Task.getDateFormat().parse("2034-04-20 20:05"),
                    -7
            );
            task.setActive(false);
            TaskIOService.add(task);
        } catch (Exception e) {
            assertEquals("interval should be > 0", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void TC8_BVA() {

        try {
            task = new Task("Task5",
                    Task.getDateFormat().parse("2025-04-15 10:00"),
                    Task.getDateFormat().parse("1950-04-20 20:05"),
                    10
            );
            task.setActive(true);
            TaskIOService.add(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Task lastTask = null;
        try {
            lastTask = TaskIOService.getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Task5", lastTask.getTitle());
        assertEquals("2025-04-15 10:00", lastTask.getFormattedDateStart());
        assertEquals("1950-04-20 20:05", lastTask.getFormattedDateEnd());
        assertEquals(10, lastTask.getRepeatInterval());
        assertTrue(lastTask.isActive());

    }


    @Test
    void TC16_BVA() {
        try {
            task = new Task("Task7",
                    Task.getDateFormat().parse("2025-04-15 10:00"),
                    Task.getDateFormat().parse("2025-04-20 20:05"),
                    1
            );
            task.setActive(true);
            TaskIOService.add(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Task lastTask = null;
        try {
            lastTask = TaskIOService.getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Task7", lastTask.getTitle());
        assertEquals("2025-04-15 10:00", lastTask.getFormattedDateStart());
        assertEquals("2025-04-20 20:05", lastTask.getFormattedDateEnd());
        assertEquals(1, lastTask.getRepeatInterval());
        assertTrue(lastTask.isActive());
    }
}