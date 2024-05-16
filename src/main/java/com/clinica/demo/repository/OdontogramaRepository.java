package com.clinica.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.Odontograma;
import com.clinica.demo.model.Paciente;

@Repository
public interface OdontogramaRepository extends CrudRepository<Odontograma, Integer> {

    ArrayList<Odontograma> findByPaciente(Paciente paciente);

}
