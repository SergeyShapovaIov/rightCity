package com.example.rightCity.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
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
}
