package tests;

import manager.*;
import model.*;
import org.junit.jupiter.api.*;
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