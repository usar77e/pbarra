package com.daniele.pbarra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniele.pbarra.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{

}
