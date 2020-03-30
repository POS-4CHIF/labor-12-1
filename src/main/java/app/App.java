package app;

import db.DAO;
import model.Address;
import model.Customer;


/**
 * @author Michael König
 */
public class App {

    public static void main(String[] args) {
        try (DAO dao = DAO.getINSTANCE()) {
            Address addressBauer = new Address(3100, "St. Pölten", "Waldstraße 3");
            Address addressHoerndle = new Address(3500, "Krems/Donau", "Ringstraße 33");
            Customer cBauer = new Customer("Franz", "Bauer", "franz.bauer@gmx.at", addressBauer);
            Customer cHoerndle = new Customer("Eva", "Hörndle", "eva.hoerndle@gmx.at", addressHoerndle);
            System.out.println("persist bauer: " + dao.persist(cBauer));
            System.out.println("persist hoerndle: " + dao.persist(cHoerndle));
            dao.findAll().forEach(System.out::println);
        }
    }
}