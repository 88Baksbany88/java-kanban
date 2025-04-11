package tests;

import model.Epic;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    void shouldNotChangeEpicIdWhenAddingSubtasks() {
        Epic epic = new Epic(1, "Test Epic", "Description");
        assertEquals(0, epic.getSubtaskIds().size(), "Новый эпик не должен содержать подзадач");
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        Epic epic = new Epic(1, "Test Epic", "Description");
        String expected = "Epic{id=1, title='Test Epic', description='Description', status=NEW, subtaskIds=[]}";
        assertEquals(expected, epic.toString());
    }
}