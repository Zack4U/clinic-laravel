package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Historial;

@Repository
public interface HistorialRepository extends CrudRepository<Historial, Integer> {

}
