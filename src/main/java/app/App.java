package app;

import db.*;
import model.Address;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Arrays;


/**
 * @author Michael König
 */
public class App {

    public static void main(String[] args) {
        try (DAO dao = DAO.getINSTANCE()) {
            System.out.println(dao.insertTestData());
        }
    }


}