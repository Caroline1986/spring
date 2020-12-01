package com.codeup.spring.models;

import javax.persistence.*;

@Entity
@Table
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    public Ad(long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Ad() {

    }

    public Ad(String title, String description) {
        this.description = description;
        this.title = title;
    }

    public long getId() {
        return this.id;
    }
}
