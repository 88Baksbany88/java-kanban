package manager;

import model.*;

import java.util.List;

public interface TaskManager {
    // Методы для задач
    List<Task> getAllTasks();
    void deleteAllTasks();
    Task getTask(int id);
    Task createTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);

    // Методы для эпиков
    List<Epic> getAllEpics();
    void deleteAllEpics();
    Epic getEpic(int id);
    Epic createEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpic(int id);

    // Методы для подзадач
    List<Subtask> getAllSubtasks();
    void deleteAllSubtasks();
    Subtask getSubtask(int id);
    Subtask createSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int id);

    List<Subtask> getEpicSubtasks(int epicId);
    List<Task> getHistory();
}