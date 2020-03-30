package db;

import java.util.List;
import model.Customer;
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

    @Override
    public void close() {
        JPAUtil.close();
    }
}