package tasks.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class TasksServiceIntegrationTest {
//    step 3: integrare E (se testează S + R cu E);
private TasksService tasksService;

    @BeforeEach
    void setUp() {
        // We'll initialize the service with the real ArrayTaskList in each test
    }

    @Test
    void testFilterTasksWithRealArrayTaskList() {
        ArrayTaskList realTaskList = new ArrayTaskList();
        tasksService = new TasksService(realTaskList);

        Task mockTask = mock(Task.class);
        Date now = new Date();
        Date inOneHour = new Date(now.getTime() + 3600 * 1000);

        when(mockTask.nextTimeAfter(now)).thenReturn(inOneHour);
        when(mockTask.getTitle()).thenReturn("Mock Task");

        realTaskList.add(mockTask);

        Iterable<Task> result = tasksService.filterTasks(now, new Date(inOneHour.getTime() + 1000));
        List<Task> resultList = (List<Task>) result;

        assertEquals(1, resultList.size());
        assertEquals("Mock Task", resultList.get(0).getTitle());
    }

    @Test
    void testFilterTasksWithRealTaskAndArrayTaskList() {
        ArrayTaskList realTaskList = new ArrayTaskList();
        tasksService = new TasksService(realTaskList);

        Date now = new Date();
        Date start = new Date(now.getTime() + 1000);             // +1 second
        Date end = new Date(now.getTime() + 3600 * 1000);        // +1 hour

        Task realTask = new Task("Real Task", start, end, 60);   // Repeats every 60 seconds
        realTask.setActive(true);

        realTaskList.add(realTask);

        Iterable<Task> result = tasksService.filterTasks(now, new Date(end.getTime() + 1000));
        List<Task> resultList = (List<Task>) result;

        assertEquals(1, resultList.size());
        assertEquals("Real Task", resultList.get(0).getTitle());
    }
}
