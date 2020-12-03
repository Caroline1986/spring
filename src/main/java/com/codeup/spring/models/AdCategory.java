package com.codeup.spring.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class AdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Ad> ads;

    public AdCategory() {

    }

    public AdCategory(long id, String name, List<Ad> ads) {
        this.ads = ads;
        this.id = id;
        this.name = name;
    }

    public AdCategory(String name, List<Ad> ads) {
        this.ads = ads;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
