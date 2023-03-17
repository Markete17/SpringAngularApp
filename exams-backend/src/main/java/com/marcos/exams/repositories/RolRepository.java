package com.marcos.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.exams.entities.Role;

public interface RolRepository extends JpaRepository<Role, Long> {

}
