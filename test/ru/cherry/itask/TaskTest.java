package ru.cherry.itask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cherry.itask.model.Epic;
import ru.cherry.itask.model.Subtask;
import ru.cherry.itask.model.Task;
import ru.cherry.itask.model.TaskStatus;
import ru.cherry.itask.model.manager.TaskManager;
import ru.cherry.itask.model.manager.Managers;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(1, "Task 1", "Description 1", TaskStatus.NEW);
        Task task2 = new Task(1, "Task 2", "Description 2", TaskStatus.IN_PROGRESS);

        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }


}