package com.example.rightCity.entity;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany( mappedBy = "user")
    private List<ComplainEntity> complains;

    public UserEntity() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
