package com.example.rightCity.entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "complain")
@Data
public class ComplainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "date")
    private String date;

    @Column(name = "photo")
    private String photo;

    @Column(name = "hashtag")
    private String hashtag;

    @Column(name = "description")
    private String description;

    @Column(name = "coordinates")
    private String coordinates;

    @ManyToOne
    @JoinColumn(name = "ID_user")
    private UserEntity user;


    public ComplainEntity (){
    }
}
