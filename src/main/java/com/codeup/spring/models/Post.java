package com.codeup.spring.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @OneToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> postImages;

//    @Column()
//    @Value("${file-upload-path}")
//    private String uploadPath;

    //Create
    public Post(String title, String body, User owner, List<PostImage> postImages) {
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.postImages = postImages;
//        this.uploadPath = uploadPath;
    }


    // READ
    public Post(long id, String title, String body, User owner, List<PostImage> postImages) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.postImages = postImages;
//        this.uploadPath = uploadPath;
    }

    public Post() {

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

//    public String getUploadPath() {
//        return uploadPath;
//    }
//
//    public void setUploadPath(String uploadPath) {
//        this.uploadPath = uploadPath;
//    }
}
