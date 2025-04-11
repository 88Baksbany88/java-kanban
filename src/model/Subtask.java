package model;

public class Subtask extends Task {
    private int epicId;

    public Subtask (int id, String title, String description, TaskStatus status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        if (this.epicId == epicId) {
            return;  // Ничего не меняем, если ID тот же
        }
        if (epicId == this.getId()) {
            throw new IllegalArgumentException("Subtask cannot be its own epic");
        }
        this.epicId = epicId;
    }
}
