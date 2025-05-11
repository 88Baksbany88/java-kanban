package ru.cherry.itask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cherry.itask.model.Epic;
import ru.cherry.itask.model.Subtask;
import ru.cherry.itask.model.Task;
import ru.cherry.itask.model.TaskStatus;
import ru.cherry.itask.model.manager.Managers;
import ru.cherry.itask.model.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = Managers.getDefault();
    }

    @Test
    void shouldNotAllowEpicToBeItsOwnSubtask() {
        Epic epic = new Epic(1, "Epic", "Description");
        assertThrows(IllegalArgumentException.class, () -> {
            taskManager.addSubtask(new Subtask(1, "Subtask", "Desc", epic.getId(), epic.getId()));
        });
    }

    @Test
    void shouldAddAndFindAllTaskTypes() {
        Task task = manager.createTask(new Task(0, "Task", "Desc", TaskStatus.NEW));
        Epic epic = manager.createEpic(new Epic(0, "Epic", "Desc"));
        Subtask subtask = manager.createSubtask(new Subtask(0, "Subtask", "Desc", TaskStatus.NEW, epic.getId()));

        assertNotNull(manager.getTask(task.getId()));
        assertNotNull(manager.getEpic(epic.getId()));
        assertNotNull(manager.getSubtask(subtask.getId()));
    }

    @Test
    void shouldHandleManualAndAutoIds() {
        Task manualTask = new Task(100, "Manual", "Desc"); // Явно заданный ID
        taskManager.addTask(manualTask);
        assertEquals(100, manualTask.getId(), "ID ручного задания должно сохраняться");

        Task autoTask = new Task("Auto", "Desc"); // Автоинкремент ID
        taskManager.addTask(autoTask);
        assertNotEquals(100, autoTask.getId(), "Авто ID не должно конфликтовать с ручными");
    }

    @Test
    void shouldPreserveTaskFieldsWhenAdded() {
        Task original = new Task(0, "Original", "Original desc", TaskStatus.NEW);
        Task created = manager.createTask(original);

        assertEquals(original.getTitle(), created.getTitle());
        assertEquals(original.getDescription(), created.getDescription());
        assertEquals(original.getStatus(), created.getStatus());
    }
}
