package tasks.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tasks.model.Task;

public class LoadTasks {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
        EntityManager manager = factory.createEntityManager();

        Task taskWithID1 = manager.find(Task.class, 1L);
        System.out.println(taskWithID1.getDescription());

        Task taskWithID2 = manager.find(Task.class, 2L);
        System.out.println(taskWithID2.getDescription());

        manager.close();
    }
}
