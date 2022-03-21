package com.example.companyrestfullapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.websocket.OnOpen;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String corpName;
    private String directorName;

    @OneToOne
    private Address address;
}
