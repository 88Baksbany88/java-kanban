package ru.cherry.itask.model.model.manager;

import ru.cherry.itask.model.model.Task;
import java.util.List;

public interface HistoryManager {
    void add(Task task);

    void remove(int id);

    List<Task> getHistory();
}