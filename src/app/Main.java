package app;

import manager.Managers;
import manager.TaskManager;
import model.*;

public class Main {
    public static void main(String[] args) {
        // Используем Managers для создания менеджера
        TaskManager manager = Managers.getDefault();

        // Создаем задачи
        Task task1 = manager.createTask(new Task(0, "Task 1", "Description 1", TaskStatus.NEW));
        Task task2 = manager.createTask(new Task(0, "Task 2", "Description 2", TaskStatus.IN_PROGRESS));

        // Создаем эпик и подзадачи
        Epic epic1 = manager.createEpic(new Epic(0, "Epic 1", "Epic description"));
        Subtask subtask1 = manager.createSubtask(new Subtask(0, "Subtask 1", "Desc", TaskStatus.NEW, epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask(0, "Subtask 2", "Desc", TaskStatus.NEW, epic1.getId()));

        // Просматриваем некоторые задачи для истории
        manager.getTask(task1.getId());
        manager.getEpic(epic1.getId());
        manager.getSubtask(subtask1.getId());

        // Вывод всех задач
        printAllTasks(manager);

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

        // Вывод истории просмотров
        System.out.println("\nView history:");
        manager.getHistory().forEach(System.out::println);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("\nAll tasks:");
        manager.getAllTasks().forEach(System.out::println);

        System.out.println("\nAll epics:");
        manager.getAllEpics().forEach(epic -> {
            System.out.println(epic);
            manager.getEpicSubtasks(epic.getId()).forEach(subtask ->
                    System.out.println("  " + subtask));
        });

        System.out.println("\nAll subtasks:");
        manager.getAllSubtasks().forEach(System.out::println);
    }
}