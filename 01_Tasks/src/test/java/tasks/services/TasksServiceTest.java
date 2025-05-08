package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksServiceTest {

    private ArrayTaskList mockTaskList;
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        mockTaskList = mock(ArrayTaskList.class);
        tasksService = new TasksService(mockTaskList);
    }

    @Test
    void testGetIntervalInHours() {
        Task mockTask = mock(Task.class);
        when(mockTask.getRepeatInterval()).thenReturn(3660); // returnează 3660 secunde

        String result = tasksService.getIntervalInHours(mockTask);

        assertEquals("01:01", result);
    }

    @Test
    void testFilterTasksWithinInterval() {
        Task mockTask = mock(Task.class);
        Task mockTask2 = mock(Task.class);

        // Setup pentru Date
        Date now = new Date();
        Date inOneHour = new Date(now.getTime() + 3600 * 1000); // +1h

        // Setup pentru nextTimeAfter()
        when(mockTask.nextTimeAfter(now)).thenReturn(inOneHour);
        when(mockTask2.nextTimeAfter(now)).thenReturn(null); // task inactiv

        when(mockTask.getTitle()).thenReturn("Task activ");

        // Lista de taskuri returnată de getAll()
        when(mockTaskList.getAll()).thenReturn(List.of(mockTask, mockTask2));

        Iterable<Task> result = tasksService.filterTasks(now, new Date(inOneHour.getTime() + 1000)); // +1h + 1s

        List<Task> resultList = (List<Task>) result;
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(mockTask));
    }

    // Poți adăuga și un test negativ:
    @Test
    void testFilterTasksThrowsIfEndBeforeStart() {
        Date start = new Date();
        Date end = new Date(start.getTime() - 1000); // end < start

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> tasksService.filterTasks(start, end)
        );

        assertEquals("Filter end date should be after start date", exception.getMessage());
    }
}
