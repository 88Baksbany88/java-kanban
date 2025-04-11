package tests;

import manager.*;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TaskManagerTest {
    private final TaskManager manager = Managers.getDefault();

    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(1, "Task 1", "Description", TaskStatus.NEW);
        Task task2 = new Task(1, "Task 2", "Description 2", TaskStatus.IN_PROGRESS);
        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    @Test
    void shouldNotAddEpicToItselfAsSubtask() {
        Epic epic = manager.createEpic(new Epic(0, "Epic", "Description"));
        Subtask subtask = new Subtask(0, "Subtask", "Desc", TaskStatus.NEW, epic.getId());

        assertDoesNotThrow(() -> manager.createSubtask(subtask),
                "Не должно быть ошибки при добавлении подзадачи");

        Subtask invalidSubtask = new Subtask(0, "Invalid", "Desc", TaskStatus.NEW, epic.getId());
        assertThrows(IllegalArgumentException.class, () -> {
            invalidSubtask.setEpicId(invalidSubtask.getId()); // Попытка сделать эпиком себя
            manager.updateSubtask(invalidSubtask);  // Используем update вместо create
        }, "Должна быть ошибка при попытке сделать подзадачу своим же эпиком");
    }

    @Test
    void historyShouldContainLast10ViewedTasks() {
        // Создаем задачи
        Task task = manager.createTask(new Task(0, "Task", "Desc", TaskStatus.NEW));
        Epic epic = manager.createEpic(new Epic(0, "Epic", "Desc"));
        Subtask subtask = manager.createSubtask(new Subtask(0, "Subtask", "Desc", TaskStatus.NEW, epic.getId()));

        // Просматриваем задачи
        manager.getTask(task.getId());
        manager.getEpic(epic.getId());
        manager.getSubtask(subtask.getId());

        // Проверяем историю
        List<Task> history = manager.getHistory();
        assertEquals(3, history.size(), "История должна содержать 3 просмотренные задачи");
        assertEquals(task, history.get(0), "Первая задача в истории не совпадает");
        assertEquals(epic, history.get(1), "Вторая задача в истории не совпадает");
        assertEquals(subtask, history.get(2), "Третья задача в истории не совпадает");
    }
}