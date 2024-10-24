package com.xmen.challenge.controller;


import com.xmen.challenge.exception.MutantNotFoundException;
import com.xmen.challenge.model.Mutant;
import com.xmen.challenge.service.MutantDetectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mutant")
public class MutantController {
    private final MutantDetectorService mutantDetectorService;

    public MutantController(MutantDetectorService mutantDetectorService) {
        this.mutantDetectorService = mutantDetectorService;
    }

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody Mutant mutant){
        boolean isMutant = mutantDetectorService.isMutantSequence(mutant);
        if (!isMutant){
            throw new MutantNotFoundException("The sequence to be a mutant was not found.");
        }
        return ResponseEntity.ok("Alert: A mutant has been found");
    }
}
