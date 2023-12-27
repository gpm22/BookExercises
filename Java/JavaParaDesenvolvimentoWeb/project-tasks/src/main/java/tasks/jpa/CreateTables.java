package tasks.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTables {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");

        factory.close();
    }
}
