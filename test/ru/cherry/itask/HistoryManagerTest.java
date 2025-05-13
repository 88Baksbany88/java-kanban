package ru.cherry.itask;

import org.junit.jupiter.api.BeforeEach;
import ru.cherry.itask.model.manager.HistoryManager;
import ru.cherry.itask.model.manager.Managers;
import ru.cherry.itask.model.Task;
import ru.cherry.itask.model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        task.setStatus(TaskStatus.DONE); // Модифицируем задачу

        Task fromHistory = historyManager.getHistory().get(0);
        assertEquals(TaskStatus.NEW, fromHistory.getStatus(), "История должна хранить исходное состояние");
    }
}