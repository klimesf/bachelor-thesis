
package cz.cvut.fel.adaptiverestfulapi.example;

import cz.cvut.fel.adaptiverestfulapi.example.model.Bug;
import cz.cvut.fel.adaptiverestfulapi.example.model.Employee;
import cz.cvut.fel.adaptiverestfulapi.example.model.Project;
import cz.cvut.fel.adaptiverestfulapi.example.model.Task;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Entity;

import java.util.*;


public class ExampleData {

    private static Set<Employee> employees = new HashSet<>();
    private static Set<Project> projects = new HashSet<>();
    private static Set<Task> tasks = new HashSet<>();
    private static Set<Bug> bugs = new HashSet<>();

    public static void init() {

        Employee employee = new Employee("Dominic", "Strother");
        employee.setId(1L);
        employees.add(employee);

        Employee employee2 = new Employee("Nataly", "Knowlton");
        employee2.setId(2L);
        employees.add(employee2);

        Employee employee3 = new Employee("Margarita", "Trumper");
        employee3.setId(3L);
        employees.add(employee3);

        Employee employee4 = new Employee("Jadyn", "Colby");
        employee4.setId(4L);
        employees.add(employee4);

        Project project = new Project();
        project.setName("Project A");
        project.setManager(employee2);
        project.setId(1L);
        projects.add(project);

        Project project2 = new Project();
        project2.setName("Project B");
        project2.setManager(employee4);
        project2.setId(2L);
        projects.add(project2);

        Task task = new Task(null, "Task 1", project);
        task.setId(1L);
        tasks.add(task);

        Task task2 = new Task(new Date(), "Task 2", project);
        task2.setId(2L);
        tasks.add(task2);

        Bug bug = new Bug(null, "Bug #1", project);
        bug.setId(1L);
        bugs.add(bug);

        Task task3 = new Task(null, "Task 3", project2);
        task3.setId(3L);
        tasks.add(task3);

        Bug bug2 = new Bug("java.lang.NullPointerException: line 32", "Bug #2", project2);
        bug2.setId(2L);

        bugs.add(bug2);

        Bug bug3 = new Bug("java.lang.IndexOutOfBoundsException: line 123", "Bug #3", project2);
        bug3.setId(3L);

        bugs.add(bug3);
    }

    public static Object find(Entity entity, Object identifier) {
        if (entity.getEntityClass().getSimpleName().equals("Employee")) {
            for (Employee employee : employees) if (employee.getId().equals(identifier)) return employee;
            return null;
        }
        if (entity.getEntityClass().getSimpleName().equals("Project")) {
            for (Project project : projects) if (project.getId().equals(identifier)) return project;
            return null;
        }
        if (entity.getEntityClass().getSimpleName().equals("Task")) {
            for (Task task : tasks) if (task.getId().equals(identifier)) return task;
            return null;
        }
        if (entity.getEntityClass().getSimpleName().equals("Bug")) {
            for (Bug bug : bugs) if (bug.getId().equals(identifier)) return bug;
            return null;
        }
        return null;
    }

    public static List findAll(Entity entity) {
        if (entity.getEntityClass().getSimpleName().equals("Employee")) return new ArrayList<>(employees);
        if (entity.getEntityClass().getSimpleName().equals("Project")) return new ArrayList<>(projects);
        if (entity.getEntityClass().getSimpleName().equals("Task")) return new ArrayList<>(tasks);
        if (entity.getEntityClass().getSimpleName().equals("Bug")) return new ArrayList<>(bugs);
        return null;
    }
}
