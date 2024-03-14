package com.clinica.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Odontologo;

@Repository
public interface OdontologoRepository extends CrudRepository<Odontologo, Integer> {

}
