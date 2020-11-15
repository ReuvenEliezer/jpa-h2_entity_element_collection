package com.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "author")
public class Author {

    /**
     * Explaining strategies: https://thoughts-on-java.org/jpa-generate-primary-keys/
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    //https://techrocking.com/collection-mapping-in-jpa/
    //https://stackoverflow.com/questions/19341838/why-do-we-use-embeddable-in-hibernate
    //https://www.baeldung.com/jpa-embedded-embeddable
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "author_phone_number", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "phone_number")
    private Set<Integer> phoneNumber = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "author_address", joinColumns = @JoinColumn(name = "author_id"))
    @MapKeyColumn(name = "address_type")
    private Map<String, Address> addressMap = new HashMap<>();


    protected Author() {
        // for JPA
    }

    public Map<String, Address> getAddressMap() {
        return addressMap;
    }

    public void addAddress(String key,Address address) {
        this.addressMap.put(key, address);
    }

    public Set<Integer> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Set<Integer> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
