package tests;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(1, "Task 1", "Description", TaskStatus.NEW);
        Task task2 = new Task(1, "Task 2", "Description 2", TaskStatus.IN_PROGRESS);
        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
    }

    @Test
    void shouldUpdateStatus() {
        Task task = new Task(1, "Task", "Desc", TaskStatus.NEW);
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus(), "Статус должен обновляться");
    }
}