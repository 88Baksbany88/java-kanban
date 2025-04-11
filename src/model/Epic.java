package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List <Integer> subtaskIds = new ArrayList<>();

    public Epic (int id, String title, String description) {
        super(id, title, description, TaskStatus.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }
}