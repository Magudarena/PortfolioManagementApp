package com.CASH.CASH.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, length = 50)
    private String socialNumber;
    @Column(nullable = false, length = 50)
    private String email;



    @OneToMany(mappedBy = "owner")
    private List<Portfolio> portfolios;

}
