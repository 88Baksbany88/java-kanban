import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int nextId = 1;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

        // Методы для задач
        public List<Task> getAllTasks() {
            return new ArrayList<>(tasks.values());
        }

        public void deleteAllTasks() {
            tasks.clear();
        }

        public Task getTask(int id) {
            return tasks.get(id);
        }

        public Task createTask(Task task) {
            task = new Task(nextId++, task.getTitle(), task.getDescription(), task.getStatus());
            tasks.put(task.getId(), task);
            return task;
        }

        public void updateTask(Task task) {
            if (tasks.containsKey(task.getId())) {
                tasks.put(task.getId(), task);
            }
        }

        public void deleteTask(int id) {
            tasks.remove(id);
        }

        // Методы для эпиков
        public List<Epic> getAllEpics() {
            return new ArrayList<>(epics.values());
        }

        public void deleteAllEpics() {
            epics.clear();
            subtasks.clear();
        }

        public Epic getEpic(int id) {
            return epics.get(id);
        }

        public Epic createEpic(Epic epic) {
            epic = new Epic(nextId++, epic.getTitle(), epic.getDescription());
            epics.put(epic.getId(), epic);
            return epic;
        }

        public void updateEpic(Epic epic) {
            Epic savedEpic = epics.get(epic.getId());
            if (savedEpic != null) {
                savedEpic.setTitle(epic.getTitle());
                savedEpic.setDescription(epic.getDescription());
            }
        }

        public void deleteEpic(int id) {
            Epic epic = epics.remove(id);
            if (epic != null) {
                for (int subtaskId : epic.getSubtaskIds()) {
                    subtasks.remove(subtaskId);
                }
            }
        }

        // Методы для подзадач
        public List<Subtask> getAllSubtasks() {
            return new ArrayList<>(subtasks.values());
        }

        public void deleteAllSubtasks() {
            subtasks.clear();
            for (Epic epic : epics.values()) {
                epic.getSubtaskIds().clear();
                updateEpicStatus(epic.getId());
            }
        }

        public Subtask getSubtask(int id) {
            return subtasks.get(id);
        }

        public Subtask createSubtask(Subtask subtask) {
            if (!epics.containsKey(subtask.getEpicId())) return null;

            subtask = new Subtask(nextId++, subtask.getTitle(), subtask.getDescription(),
                    subtask.getStatus(), subtask.getEpicId());
            subtasks.put(subtask.getId(), subtask);
            epics.get(subtask.getEpicId()).getSubtaskIds().add(subtask.getId());
            updateEpicStatus(subtask.getEpicId());
            return subtask;
        }

        public void updateSubtask(Subtask subtask) {
            if (subtasks.containsKey(subtask.getId())) {
                subtasks.put(subtask.getId(), subtask);
                updateEpicStatus(subtask.getEpicId());
            }
        }

        public void deleteSubtask(int id) {
            Subtask subtask = subtasks.remove(id);
            if (subtask != null) {
                Epic epic = epics.get(subtask.getEpicId());
                if (epic != null) {
                    epic.getSubtaskIds().remove((Integer) id);
                    updateEpicStatus(epic.getId());
                }
            }
        }

        public List<Subtask> getEpicSubtasks(int epicId) {
            if (!epics.containsKey(epicId)) return null;

            List<Subtask> result = new ArrayList<>();
            for (int subtaskId : epics.get(epicId).getSubtaskIds()) {
                result.add(subtasks.get(subtaskId));
            }
            return result;
        }

        private void updateEpicStatus(int epicId) {
            Epic epic = epics.get(epicId);
            if (epic == null) return;

            List<Integer> subtaskIds = epic.getSubtaskIds();
            if (subtaskIds.isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
                return;
            }

            boolean allDone = true;
            boolean anyInProgress = false;

            for (int subtaskId : subtaskIds) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask == null) continue;

                if (subtask.getStatus() != TaskStatus.DONE) {
                    allDone = false;
                }
                if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                    anyInProgress = true;
                }
            }

            if (allDone) {
                epic.setStatus(TaskStatus.DONE);
            } else if (anyInProgress) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            } else {
                epic.setStatus(TaskStatus.NEW);
            }
        }
}