package ru.cherry.itask;

import org.junit.jupiter.api.Test;
import ru.cherry.itask.model.Subtask;
import ru.cherry.itask.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask1 = new Subtask(1, "Title", "Desc", 10, TaskStatus.NEW);
        Subtask subtask2 = new Subtask(1, "Different", "Different", 20, TaskStatus.DONE);

        assertEquals(subtask1.getId(), subtask2.getId(), "Подзадачи с одинаковым ID должны быть равны");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtask subtask = (Subtask) o;
        return id == subtask.id;
    }

    @Test
    void shouldNotAllowSelfAsEpic() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subtask(1, "Subtask", "Desc", TaskStatus.NEW, 1);
        });
    }
}