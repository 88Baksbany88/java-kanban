package manager;

import model.*;
import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();
    void deleteAllTasks();
    Task getTask(int id);
    Task createTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);

    List<Epic> getAllEpics();
    void deleteAllEpics();
    Epic getEpic(int id);
    Epic createEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpic(int id);

    List<Subtask> getAllSubtasks();
    void deleteAllSubtasks();
    Subtask getSubtask(int id);
    Subtask createSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int id);

    List<Subtask> getEpicSubtasks(int epicId);
    List<Task> getHistory();
}