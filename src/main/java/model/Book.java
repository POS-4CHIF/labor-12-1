package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael König
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_isbn")
    private String isbn;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_date", length = 100)
    private LocalDate date;

    @Column(name = "book_title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "book_pub_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "book_cust_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "book_category_join",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<BookCategory> categories = new HashSet<>(0);

    public Book() {
    }

    public Book(String isbn, String title, String author, LocalDate date, Publisher publisher) {
        this.isbn = isbn;
        this.author = author;
        this.date = date;
        this.title = title;
        this.publisher = publisher;
    }

    public void addBookCategory(BookCategory bookCategory) {
        bookCategory.getBooks().add(this);
        this.categories.add(bookCategory);
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<BookCategory> categories) {
        this.categories = categories;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return getId() != null ? getId().equals(book.getId()) : book.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", categories=" + categories +
                '}';
    }
}