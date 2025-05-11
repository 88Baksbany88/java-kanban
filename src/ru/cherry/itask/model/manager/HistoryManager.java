package ru.cherry.itask.model.manager;

import ru.cherry.itask.model.service.Task;
import java.util.List;

public interface HistoryManager {
    void add(Task task);

    void remove(int id);

    List<Task> getHistory();
}