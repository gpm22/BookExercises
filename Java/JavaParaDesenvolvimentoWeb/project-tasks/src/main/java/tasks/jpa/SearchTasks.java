package tasks.jpa;

import tasks.model.Task;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SearchTasks {
    public static void main(String[] args) {

          EntityManagerFactory factory = Persistence.
              createEntityManagerFactory("tasks");
            EntityManager manager = factory.createEntityManager();

          Query query = manager
              .createQuery("select t from Task as t "+
                "where t.concluded = :paramConcluded");
          query.setParameter("paramConcluded", true);

          List<Task> tasks = query.getResultList();

          for (Task t : tasks) {
            System.out.println(t.getDescription());
          }

          manager.close();
      }
}
