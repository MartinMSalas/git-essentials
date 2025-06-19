package com.example.personaladminservice.repository;

import com.example.personaladminservice.model.PersonalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalAdminRepository extends JpaRepository<PersonalAdmin, Long> {
    // JpaRepository provides CRUD methods like save, findById, findAll, deleteById, etc.
    // Custom query methods can be added here if needed.
}
