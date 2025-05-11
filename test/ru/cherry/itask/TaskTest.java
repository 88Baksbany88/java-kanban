package ru.cherry.itask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cherry.itask.model.model.Epic;
import ru.cherry.itask.model.model.Subtask;
import ru.cherry.itask.model.model.Task;
import ru.cherry.itask.model.model.TaskStatus;
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

    static class TaskManagerTest {
        private TaskManager manager;

        @BeforeEach
        void setUp() {
            manager = Managers.getDefault();
        }

        @Test
        void shouldNotAllowEpicToBeItsOwnSubtask() {
            Epic epic = manager.createEpic(new Epic(0, "Epic", "Desc"));
            assertThrows(IllegalArgumentException.class, () -> {
                manager.createSubtask(new Subtask(0, "Invalid", "Desc", TaskStatus.NEW, epic.getId()));
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
            Task manualIdTask = new Task(100, "Manual", "Desc", TaskStatus.NEW);
            Task autoIdTask = new Task(0, "Auto", "Desc", TaskStatus.NEW);

            Task createdManual = manager.createTask(manualIdTask);
            Task createdAuto = manager.createTask(autoIdTask);

            assertEquals(100, createdManual.getId());
            assertNotEquals(0, createdAuto.getId());
            assertNotEquals(createdManual.getId(), createdAuto.getId());
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
}