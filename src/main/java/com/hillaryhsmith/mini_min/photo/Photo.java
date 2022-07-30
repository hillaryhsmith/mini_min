package com.hillaryhsmith.mini_min.photo;

import com.hillaryhsmith.mini_min.mineral.Mineral;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "mineral_id")
    Mineral mineral;

    private String location;
}
