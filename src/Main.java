public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Тестирование функционала
        Task task1 = manager.createTask(new Task(0, "Task 1", "Description 1", TaskStatus.NEW));
        Task task2 = manager.createTask(new Task(0, "Task 2", "Description 2", TaskStatus.IN_PROGRESS));

        Epic epic1 = manager.createEpic(new Epic(0, "Epic 1", "Epic description"));
        Subtask subtask1 = manager.createSubtask(new Subtask(0, "Subtask 1", "Desc", TaskStatus.NEW, epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask(0, "Subtask 2", "Desc", TaskStatus.NEW, epic1.getId()));

        // Вывод всех задач
        System.out.println("All tasks:");
        manager.getAllTasks().forEach(System.out::println);

        System.out.println("\nAll epics:");
        manager.getAllEpics().forEach(System.out::println);

        System.out.println("\nAll subtasks:");
        manager.getAllSubtasks().forEach(System.out::println);

        // Изменение статусов
        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);

        System.out.println("\nEpic after subtasks update:");
        System.out.println(manager.getEpic(epic1.getId()));

        // Удаление
        manager.deleteTask(task1.getId());
        manager.deleteEpic(epic1.getId());

        System.out.println("\nAfter deletion:");
        System.out.println("Tasks: " + manager.getAllTasks().size());
        System.out.println("Epics: " + manager.getAllEpics().size());
        System.out.println("Subtasks: " + manager.getAllSubtasks().size());
    }
}