
package com.energycoffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String externalId;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 255)
    private String address;

    public Site() {
    }

    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public List<Meter> getMeters() {
        return meters;
    }

    @OneToMany(mappedBy = "site")
    private List<Meter> meters;

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
