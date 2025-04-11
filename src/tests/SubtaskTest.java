package tests;

import model.Subtask;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    void shouldCreateSubtaskWithEpicId() {
        Subtask subtask = new Subtask(1, "Subtask", "Desc", TaskStatus.NEW, 10);
        assertEquals(10, subtask.getEpicId(), "Неверный EpicID у подзадачи");
    }

    @Test
    void shouldNotAllowSelfAsEpic() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subtask(1, "Subtask", "Desc", TaskStatus.NEW, 1);
        }, "Подзадача не может быть своим эпиком");
    }
}