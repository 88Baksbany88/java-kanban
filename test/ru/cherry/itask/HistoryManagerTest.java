package ru.cherry.itask;

import manager.*;
import org.junit.jupiter.api.*;
import ru.cherry.itask.model.model.Task;
import ru.cherry.itask.model.model.TaskStatus;

class HistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void shouldPreserveTaskStateInHistory() {
        Task task = new Task(1, "Task", "Description", TaskStatus.NEW);
        historyManager.add(task);

        task.setTitle("Modified");
        task.setStatus(TaskStatus.DONE);

        Task fromHistory = historyManager.getHistory().get(0);
        assertEquals("Task", fromHistory.getTitle());
        assertEquals(TaskStatus.NEW, fromHistory.getStatus());
    }
}