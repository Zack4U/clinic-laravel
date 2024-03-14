package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.tipoTratamiento;

@Repository
public interface TipoTratamientoRepository extends CrudRepository<tipoTratamiento, Integer> {

}