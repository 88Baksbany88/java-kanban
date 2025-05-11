package ru.cherry.itask;

import ru.cherry.itask.model.manager.HistoryManager;
import ru.cherry.itask.model.manager.Managers;
import ru.cherry.itask.model.Task;
import ru.cherry.itask.model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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