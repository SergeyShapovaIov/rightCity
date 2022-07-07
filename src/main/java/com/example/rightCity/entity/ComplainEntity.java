package com.example.rightCity.entity;

import javax.persistence.*;

@Entity
@Table(name = "complain")
public class ComplainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;


}
