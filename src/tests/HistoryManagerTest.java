package tests;

import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    private final HistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void shouldAddTasksToHistory() {
        Task task = new Task(1, "Task", "Description", TaskStatus.NEW);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть null");
        assertEquals(1, history.size(), "Неверное количество задач в истории");
        assertEquals(task, history.get(0), "Задачи не совпадают");
    }

    @Test
    void shouldLimitHistorySize() {
        for (int i = 0; i < 15; i++) {
            historyManager.add(new Task(i, "Task" + i, "Desc", TaskStatus.NEW));
        }
        assertEquals(10, historyManager.getHistory().size(), "История должна ограничиваться 10 задачами");
    }
}