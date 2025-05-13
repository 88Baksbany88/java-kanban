package ru.cherry.itask.model;

/**
 * Класс подзадачи (Subtask), наследуется от Task.
 * Содержит ссылку на эпик, к которому относится.
 */
public class Subtask extends Task {
    private final int epicId;

    /**
     * Конструктор подзадачи.
     *
     * @param id          идентификатор подзадачи
     * @param title       название подзадачи
     * @param description описание подзадачи
     * @param status      статус подзадачи
     * @param epicId      идентификатор эпика, к которому относится подзадача
     * @throws IllegalArgumentException если epicId равен id подзадачи
     */
    public Subtask(int id, String title, String description, TaskStatus status, int epicId) {
        super(id, title, description, status);

        if (id == epicId) {
            throw new IllegalArgumentException("Подзадача не может быть своим же эпиком");
        }

        this.epicId = epicId;
    }

    /**
     * Возвращает идентификатор эпика, к которому относится подзадача.
     *
     * @return идентификатор эпика
     */
    public int getEpicId() {
        return epicId;
    }

    /**
     * Переопределение метода toString для удобного вывода информации о подзадаче.
     *
     * @return строковое представление подзадачи
     */
    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }

    /**
     * Переопределение equals для сравнения подзадач по id.
     *
     * @param o объект для сравнения
     * @return true, если подзадачи равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    /**
     * Переопределение hashCode для корректной работы с коллекциями.
     *
     * @return хэш-код подзадачи
     */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + epicId;
    }
}