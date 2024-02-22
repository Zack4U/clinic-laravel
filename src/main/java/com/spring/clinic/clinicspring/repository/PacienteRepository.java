package com.spring.clinic.clinicspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.clinic.clinicspring.entity.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Integer> {

}
