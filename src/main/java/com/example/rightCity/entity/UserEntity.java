package com.example.rightCity.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "FIO")
    private String FIO = "FIO";
    @Column(name = "mail")
    private String mail = "mail";
    @Column(name = "password")
    private String password = "password";


    public UserEntity() {
    }

    public void setID (Long ID){
        this.ID = ID;
    }

    public void setFIO(String fio){
        this.FIO = fio;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
