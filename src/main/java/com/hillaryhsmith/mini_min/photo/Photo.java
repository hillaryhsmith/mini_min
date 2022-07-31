package com.hillaryhsmith.mini_min.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hillaryhsmith.mini_min.mineral.Mineral;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Photo {
    @Id
    @SequenceGenerator(
            name= "photo_sequence",
            sequenceName = "photo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "photo_sequence"
    )
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "mineral_id")
    Mineral mineral;

    @NotNull
    private String location;

    protected Photo() {}

    // Constructor
    public Photo(Integer id, Mineral mineral, String location) {
        this.id = id;
        this.mineral = mineral;
        this.location = location;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // To String
    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", mineral=" + mineral +
                ", location='" + location + '\'' +
                '}';
    }
}
