package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.odontograma.NotaDiente;

@Repository
public interface NotaDienteRepository extends CrudRepository<NotaDiente, Integer> {

}
