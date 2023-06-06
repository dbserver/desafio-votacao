package com.example.votacaodesafio.controller;

import com.example.votacaodesafio.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Slf4j
public abstract class GenericRestController<T, K> {

    private GenericService<T, K> service;


    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<T> findById(@PathVariable("id") K id) {
        T entity = this.service.findById(id);

        if (isNull(entity)) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<T> save(@RequestBody T entityRequest) {
        T entity = this.service.save(entityRequest);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<T> delete(@PathVariable("id") K id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}