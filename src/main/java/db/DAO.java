package db;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

/**
 * @author Michael König
 */
public class DAO implements AutoCloseable{

    private final static DAO INSTANCE = new DAO();

    // private Constructor
    private DAO() {}

    public static DAO getINSTANCE() {
        return INSTANCE;
    }

    public Optional<Customer> getCustomerById(Integer id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return Optional.ofNullable(em.find(Customer.class, id));
        } finally {
            em.close();
        }
    }

    public List<Customer> findAll() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery("select c from Customer c").getResultList();
        } finally {
            em.close();
        }
    }

    public boolean persist(Customer customer) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(customer.getAddress());
            em.persist(customer);
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean insertTestData() {
        Publisher p1 = new Publisher("entwickler.press", "Fachverlag für Softwareentwicklung");
        Publisher p2 = new Publisher("Michael Müller Verlag", "Reiseführer und Romane");

        BookCategory bc1 = new BookCategory("EDV-Fachbuch");
        BookCategory bc2 = new BookCategory("Datenbanken");
        BookCategory bc3 = new BookCategory("Reiseführer");

        LocalDate d1 = LocalDate.of(2012, 3, 20);
        LocalDate d2 = LocalDate.of(2008, 1, 17);

        Book b1 = new Book("9783868020144", "JPA mit Hibernate", "Daniel Röder", d1, p1);
        b1.addBookCategory(bc1);
        b1.addBookCategory(bc2);
        Book b2 = new Book("9783777020144", "Das Java Persistance API", "John Smith", d2,p1);
        b2.addBookCategory(bc1);
        b2.addBookCategory(bc2);
        Book b3 = new Book("3831713502", "Pyrenäen", "Michael Schuh", d2, p2);
        b3.addBookCategory(bc3);
        Book b4 = new Book("9783899533388", "Toskana", "Michael Müller", d1, p2);
        b4.addBookCategory(bc3);

        p1.getAddresses().add(new Address(1010, "Herrengasse 17", "Wien"));
        p1.getAddresses().add(new Address(3100, "Linzerstraße 37", "St.Pölten"));
        p2.getAddresses().add(new Address(2473, "Obere Hauptstraße 32", "Prellenkirchen"));
        p1.getAddresses().add(new Address(3200, "Hauptstraße 20", "Ober Grafendorf"));

        Book []books = {b1, b2, b3, b4};
        BookCategory []categories ={bc1, bc2, bc3};

        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p1);
            em.persist(p2);
            Arrays.stream(categories).forEach(c -> em.persist(c));
            Arrays.stream(books).forEach(b -> em.persist(b));
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void close() {
        JPAUtil.close();
    }
}