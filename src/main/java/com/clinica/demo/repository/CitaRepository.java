package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Cita;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Integer> {

}
