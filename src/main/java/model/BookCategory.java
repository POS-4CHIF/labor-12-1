package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael König
 */
@Entity
@Table(name = "book_category")
@SequenceGenerator(name = "category_seq", sequenceName = "category_id_seq", allocationSize = 5)

public class BookCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "category_seq")
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_description")
    private String description;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
    private Set<Book> books = new HashSet<>(0);

    public BookCategory() {
    }

    public BookCategory(String description) {
        this.description = description;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (this.id != null) {
            throw new IllegalArgumentException("id already set");
        }
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BookCategory that = (BookCategory) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookCategory{" + "id=" + id + ", description='" + description + '\'' + ", books=" + books + '}';
    }
}