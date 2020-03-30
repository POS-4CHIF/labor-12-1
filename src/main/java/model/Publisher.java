package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael König
 */
@Entity
@Table(name = "publisher")
public class Publisher implements Serializable {

    @Id
    @Column(name = "pub_name")
    private String name;

    @Column(name = "pub_description")
    private String description;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();

    public Publisher() {
    }

    public Publisher(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.setPublisher(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name != null) {
            throw new IllegalArgumentException("name already set");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return getName() != null ? getName().equals(publisher.getName()) : publisher.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", books=" + books +
                ", addresses=" + addresses +
                '}';
    }
}