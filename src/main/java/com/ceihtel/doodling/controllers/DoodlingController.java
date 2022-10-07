package com.ceihtel.doodling.controllers;

import com.ceihtel.doodling.domain.repositories.DoodleRepository;
import com.ceihtel.doodling.dto.CreateDoodleDTO;
import com.ceihtel.doodling.dto.DoodleDTO;
import com.ceihtel.doodling.mappers.DoodleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doodles")
public class DoodlingController {
    private final DoodleRepository repository;
    private final DoodleMapper mapper;

    @GetMapping
    public List<DoodleDTO> getAll() {
        return mapper.map(repository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoodleDTO create(@RequestBody CreateDoodleDTO createDoodleDTO) {
        return mapper.map(repository.save(mapper.map(createDoodleDTO)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
