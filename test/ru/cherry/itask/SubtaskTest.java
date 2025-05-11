package ru.cherry.itask;

import org.junit.jupiter.api.Test;
import ru.cherry.itask.model.model.Subtask;
import ru.cherry.itask.model.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask1 = new Subtask(1, "Subtask 1", "Desc 1", TaskStatus.NEW, 10);
        Subtask subtask2 = new Subtask(1, "Subtask 2", "Desc 2", TaskStatus.DONE, 20);

        assertEquals(subtask1, subtask2);
        assertEquals(subtask1.hashCode(), subtask2.hashCode());
    }

    @Test
    void shouldNotAllowSelfAsEpic() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subtask(1, "Subtask", "Desc", TaskStatus.NEW, 1);
        });
    }
}