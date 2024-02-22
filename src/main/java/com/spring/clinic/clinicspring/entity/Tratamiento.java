package com.spring.clinic.clinicspring.entity;

import javax.persistence.*;

@Entity
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
