package com.ensolvers.notes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "name")
    public String name;
    @Column(name = "color")
    public String color;
    @Column(name = "active")
    public String active;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference // Prevents infinite recursion
    private Set<Note> notes;

    public Category(Long id, String name, String color, String active){
        this.id = id;
        this.name = name;
        this.color = color;
        this.active = active;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
