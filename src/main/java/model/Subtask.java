package model;

public class Subtask extends Task {
    private int epicId;

    public class Subtask extends Task {
        private final int epicId;

        public Subtask(int id, String title, String description, TaskStatus status, int epicId) {
            super(id, title, description, status);

            if (id == epicId) {  // Проверка, что epicId не равен id подзадачи
                throw new IllegalArgumentException("Subtask cannot be its own epic");
            }

            this.epicId = epicId;
        }

    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", epicId=" + epicId +
                '}';
    }
}