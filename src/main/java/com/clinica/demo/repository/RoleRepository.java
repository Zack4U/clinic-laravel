package com.clinica.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.model.usuarios.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}