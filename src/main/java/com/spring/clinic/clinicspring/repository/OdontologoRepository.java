package com.spring.clinic.clinicspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.clinic.clinicspring.entity.Odontologo;

public interface OdontologoRepository extends CrudRepository<Odontologo, Integer> {
    Iterable<Odontologo> findByEspecialidad(String Especialidad);
}
