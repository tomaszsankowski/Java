package main;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
        EntityManager em = emf.createEntityManager();

        createDatabase(em);

        printDatabase(em);

        deleteTower(em, "Don Gun Dur");

        printDatabase(em);

        addMage(em, new Mage("Guacamole",12,null));
        deleteMage(em, "Anna");

        printDatabase(em);

        Tower t = new Tower("Don Gun Dur", 80, new ArrayList<>());
        addTower(em, t);
        addMage(em, new Mage("Anna", 42, t));
        addMage(em, new Mage("Radagast", 62, t));
        addMage(em, new Mage("Sauron", 1, t));

        printDatabase(em);

        String sqlQuery1 = "SELECT * FROM Mages WHERE level > 50";
        executeQuery(em, sqlQuery1);

        String sqlQuery2 = "SELECT * FROM Towers WHERE height < 100";
        executeQuery(em, sqlQuery2);

        String sqlQuery3 = "SELECT m.* FROM Mages m JOIN Towers t ON m.tower_id = t.id WHERE m.level > 50 AND t.name = 'Don Gun Dur'";
        executeQuery(em, sqlQuery3);


        em.close();
        emf.close();
    }

    private static void deleteMage(EntityManager em, String mageName) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        List<Mage> mages = em.createNamedQuery("Mage.findByName", Mage.class)
                .setParameter("name", mageName)
                .getResultList();

        for (Mage mage : mages) {
            Tower tower = mage.getTower();

            if(tower != null){
                tower.getMages().remove(mage);

                em.merge(tower);
            }

            em.remove(mage);
        }

        tx.commit();
    }

    private static void deleteTower(EntityManager em, String name)
    {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        List<Tower> towers = em.createNamedQuery("Tower.findByName", Tower.class)
                .setParameter("name", name)
                .getResultList();

        for (Tower tower : towers) {
            em.remove(tower);
        }

        tx.commit();
    }

    private static void createDatabase(EntityManager em)
    {
        Tower t1 = new Tower("Don Gun Dur", 120, new ArrayList<>());
        Tower t2 = new Tower("Apassa", 120, new ArrayList<>());

        Mage mage1 = new Mage("Guido", 87, t1);
        Mage mage2 = new Mage("Mario", 55, t1);
        Mage mage3 = new Mage("Fernando", 1, t1);
        Mage mage4 = new Mage("Fiona", 21, t2);
        Mage mage5 = new Mage("Anna", 42, t2);
        Mage mage6 = new Mage("Guacamole", 100, t2);
        /*
        t1.getMages().add(mage1);
        t1.getMages().add(mage2);
        t1.getMages().add(mage3);
        t2.getMages().add(mage4);
        t2.getMages().add(mage5);
        t2.getMages().add(mage6);
         */

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(mage1);
        em.persist(mage2);
        em.persist(mage3);
        em.persist(mage4);
        em.persist(mage5);
        em.persist(mage6);
        em.persist(t1);
        em.persist(t2);
        tx.commit();
    }

    private static void addMage(EntityManager em, Mage mage)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(mage);
        tx.commit();
    }

    private static void addTower(EntityManager em, Tower tower)
    {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(tower);
        tx.commit();
    }

    private static void printDatabase(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        List<Mage> mages = em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();

        List<Tower> towers = em.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();

        System.out.println("\nDATABASE CONTENT: \n");
        for (Mage mage : mages) {
            System.out.println(mage);
        }
        System.out.println();
        for (Tower tower : towers) {
            System.out.println(tower);
        }

        tx.commit();
    }

    public static void executeQuery(EntityManager em, String sqlQuery) {
        Query query = em.createNativeQuery(sqlQuery);
        try {
            List<Object[]> results = query.getResultList();

            for (Object[] row : results) {
                for (Object column : row) {
                    System.out.print(column + " ");
                }
                System.out.println();
            }
        } catch (PersistenceException e) {
            System.out.println("Invalid SQL query!");
        }
    }
}