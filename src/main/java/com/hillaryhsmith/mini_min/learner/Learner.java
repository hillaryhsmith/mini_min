package com.hillaryhsmith.mini_min.learner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hillaryhsmith.mini_min.mineral.Mineral;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Learner {
    @Id
    @SequenceGenerator(
            name= "learner_sequence",
            sequenceName = "learner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "learner_sequence"
    )
    private Integer id;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name="mineral_learner",
        joinColumns = @JoinColumn(name = "learner_id"),
        inverseJoinColumns = @JoinColumn(name = "mineral_id"))
    Set<Mineral> learnedMinerals;

    private String username;
    private String password;
    private String email;

//    Constructor

    protected Learner() {}

    public Learner(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Mineral> getLearnedMinerals() {
        return learnedMinerals;
    }

    //    To String

    @Override
    public String toString() {
        return "Learner{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
