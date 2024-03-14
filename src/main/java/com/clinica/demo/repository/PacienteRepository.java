package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {

}
