/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdmanager.controllers;

import com.mycompany.dvdmanager.data.DvDDao;
import com.mycompany.dvdmanager.models.DvD;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author nacer
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("")
public class DvDController {
    private final DvDDao dao;
    
    public DvDController(DvDDao dao){
        this.dao = dao;
    }
 
    // get DVD by ID
    @GetMapping("/dvd/{id}")
    public ResponseEntity<DvD> findById(@PathVariable int id) {
        DvD result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    // Get all DVD
    @GetMapping("/dvds")
    public List<DvD> all() {
        return dao.getAll();
    }
    
    // Get dvd by title
    @GetMapping("/dvds/title/{title}")
    public ResponseEntity<List<DvD>> findByTitle(@PathVariable String title) {
        List<DvD> result = dao.findByTitle(title);
        if (result == null || result.size() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    // get dvd by release year
    @GetMapping("/dvds/year/{year}")
    public ResponseEntity<List<DvD>> findByReleaseYear(@PathVariable int year) {
        List<DvD> result = dao.findByReleaseYear(year);
        if (result == null || result.size() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    // get dvd by director name
    @GetMapping("/dvds/director/{dn}")
    public ResponseEntity<List<DvD>> findByDirectorName(@PathVariable String dn) {
        List<DvD> result = dao.findByDirectorName(dn);
        if (result == null || result.size() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    // get dvd by rating
    @GetMapping("/dvds/rating/{rating}")
    public ResponseEntity<List<DvD>> findByRating(@PathVariable String rating) {
        List<DvD> result = dao.findByRating(rating);
        if (result == null || result.size() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    // create a dvd
    @PostMapping("/dvd")
    @ResponseStatus(HttpStatus.CREATED)
    public DvD create(@RequestBody DvD dvd) {
        return dao.add(dvd);
    }
    
    // update a dvd
    @PutMapping("/dvd/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody DvD dvd) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != dvd.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(dvd)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    // delete a dvd by id
    @DeleteMapping("/dvd/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
}
