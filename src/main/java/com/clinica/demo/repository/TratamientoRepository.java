package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Tratamiento;

@Repository
public interface TratamientoRepository extends CrudRepository<Tratamiento, Integer> {

}