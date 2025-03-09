package com.ensolvers.notes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

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
    @Column(name = "active")
    public String active;
    @Column(name = "flag_archived")
    public String flagArchived;

    @ManyToMany
    @JoinTable(
            name = "note_category",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference // Manages the serialization
    private Set<Category> categories;

    public Note(Long id, String title, String description, String active, String flagArchived) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
