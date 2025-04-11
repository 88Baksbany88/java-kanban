package tests;

import manager.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = Managers.getDefault();
    }

    @Test
    void shouldCreateTask() {
        Task task = manager.createTask(new Task(0, "Task", "Desc", TaskStatus.NEW));
        assertNotNull(task.getId(), "Задача должна получить ID при создании");
        assertEquals(task, manager.getTask(task.getId()), "Созданная задача должна быть доступна по ID");
    }

    @Test
    void shouldUpdateEpicStatusAutomatically() {
        Epic epic = manager.createEpic(new Epic(0, "Epic", "Desc"));
        Subtask subtask = manager.createSubtask(new Subtask(0, "Subtask", "Desc", TaskStatus.NEW, epic.getId()));

        assertEquals(TaskStatus.NEW, epic.getStatus(), "Статус эпика должен быть NEW");

        subtask.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask);
        assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus(), "Статус эпика должен обновиться");
    }

    @Test
    void shouldDeleteAllTasks() {
        manager.createTask(new Task(0, "Task", "Desc", TaskStatus.NEW));
        manager.deleteAllTasks();
        assertTrue(manager.getAllTasks().isEmpty(), "Все задачи должны быть удалены");
    }
}