package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Diente;

@Repository
public interface DienteRepository extends CrudRepository<Diente, Integer> {

}
