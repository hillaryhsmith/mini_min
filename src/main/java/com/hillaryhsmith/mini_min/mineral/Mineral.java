package com.hillaryhsmith.mini_min.mineral;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hillaryhsmith.mini_min.learner.Learner;
import com.hillaryhsmith.mini_min.photo.Photo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Mineral {

    @Id
    @SequenceGenerator(
            name= "mineral_sequence",
            sequenceName = "mineral_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mineral_sequence"
    )
    private Integer id;

    @JsonIgnore
    @ManyToMany(mappedBy = "learnedMinerals")
    Set<Learner> learnedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "mineral", orphanRemoval=true)
    Set<Photo> photos;

    private Integer mindatId;

    private String name;

    private String formula;

    private String significance;

    private Float hardness;

    private String specificGravity;

    private String color;

    private String streak;

    private String lustre;

    private String polymorphs;

    private String crystalSystem;

    private String description;

    protected Mineral() {}

//    Constructor
    public Mineral(Integer id, Integer mindatId, String name,
                   String formula, String significance, Float hardness,
                   String specificGravity, String color, String lustre,
                   String polymorphs, String crystalSystem, String description) {
        this.id = id;
        this.mindatId = mindatId;
        this.name = name;
        this.formula = formula;
        this.significance = significance;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.color = color;
        this.lustre = lustre;
        this.polymorphs = polymorphs;
        this.crystalSystem = crystalSystem;
        this.description = description;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMindatId() {
        return mindatId;
    }

    public void setMindatId(Integer mindatId) {
        this.mindatId = mindatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public Float getHardness() {
        return hardness;
    }

    public void setHardness(Float hardness) {
        this.hardness = hardness;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getLustre() {
        return lustre;
    }

    public void setLustre(String lustre) {
        this.lustre = lustre;
    }

    public String getPolymorphs() {
        return polymorphs;
    }

    public void setPolymorphs(String polymorphs) {
        this.polymorphs = polymorphs;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public void setCrystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Learner> getLearnedBy() {
        return learnedBy;
    }

    public void setLearnedBy(Set<Learner> learnedBy) {
        this.learnedBy = learnedBy;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

//    To String

    @Override
    public String toString() {
        return "Mineral{" +
                "id=" + id +
                ", learnedBy=" + learnedBy +
                ", photos=" + photos +
                ", mindatId=" + mindatId +
                ", name='" + name + '\'' +
                ", formula='" + formula + '\'' +
                ", significance='" + significance + '\'' +
                ", hardness=" + hardness +
                ", specificGravity='" + specificGravity + '\'' +
                ", color='" + color + '\'' +
                ", streak='" + streak + '\'' +
                ", lustre='" + lustre + '\'' +
                ", polymorphs='" + polymorphs + '\'' +
                ", crystalSystem='" + crystalSystem + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}