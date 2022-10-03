package com.daniele.pbarra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniele.pbarra.model.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer>{

}
