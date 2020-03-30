package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michael König
 */
@Entity
@Table(name = "address")
@SequenceGenerator(name = "address_seq", sequenceName = "addr_id_seq")
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = "address_seq")
    @Column(name = "addr_id")
    private Integer id;

    @Column(name = "addr_zip", nullable = false)
    private Integer zip;

    @Column(name = "addr_street", length = 60)
    private String street;

    @Column(name = "addr_city", length = 60)
    private String city;

    @OneToOne(mappedBy = "address", cascade = CascadeType.PERSIST)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "addr_pub_id")
    private Publisher publisher;

    public Address() {}

    public Address(Integer zip, String street, String city) {
        this.zip = zip;
        this.street = street;
        this.city = city;
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

        Address address = (Address) o;

        return getId() != null ? getId().equals(address.getId()) : address.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}