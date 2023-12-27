package tasks.jpa;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tasks.model.Task;

public class AddTask {
     public static void main(String[] args) {

          Task task = new Task();
          task.setDescription("Ler Effective Java");
          task.setConcluded(false);
        //   task.setConclusionDate(Calendar.getInstance());

          EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("tasks");
          EntityManager manager = factory.createEntityManager();

          manager.getTransaction().begin();     
          manager.persist(task);
          manager.getTransaction().commit();

          System.out.println("ID da task: " + task.getId());

          manager.close();
      }
}
