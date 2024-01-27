package com.ensolvers.notes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "title")
    public String title;
    @Column(name = "description")
    public String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;
    @Column(name = "active")
    public String active;
    @Column(name = "flag_archived")
    public String flagArchived;

    public Note(Long id, String title, String description, Category category, String active, String flagArchived) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.active = active;
        this.flagArchived = flagArchived;
    }

    public Note() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFlagArchived() {
        return flagArchived;
    }

    public void setFlagArchived(String flagArchived) {
        this.flagArchived = flagArchived;
    }
}
