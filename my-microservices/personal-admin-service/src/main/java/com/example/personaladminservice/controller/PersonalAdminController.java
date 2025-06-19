package com.example.personaladminservice.controller;

import com.example.personaladminservice.model.PersonalAdmin;
import com.example.personaladminservice.repository.PersonalAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class PersonalAdminController {

    @Autowired
    private PersonalAdminRepository personalAdminRepository;

    @PostMapping
    public ResponseEntity<PersonalAdmin> createAdmin(@RequestBody PersonalAdmin admin) {
        try {
            PersonalAdmin savedAdmin = personalAdminRepository.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonalAdmin>> getAllAdmins() {
        try {
            List<PersonalAdmin> admins = personalAdminRepository.findAll();
            if (admins.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalAdmin> getAdminById(@PathVariable("id") Long id) {
        Optional<PersonalAdmin> adminData = personalAdminRepository.findById(id);
        return adminData.map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalAdmin> updateAdmin(@PathVariable("id") Long id, @RequestBody PersonalAdmin admin) {
        Optional<PersonalAdmin> adminData = personalAdminRepository.findById(id);

        if (adminData.isPresent()) {
            PersonalAdmin existingAdmin = adminData.get();
            existingAdmin.setName(admin.getName());
            existingAdmin.setEmail(admin.getEmail());
            return new ResponseEntity<>(personalAdminRepository.save(existingAdmin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") Long id) {
        try {
            personalAdminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Could be more specific with EmptyResultDataAccessException if id not found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllAdmins() {
        try {
            personalAdminRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
