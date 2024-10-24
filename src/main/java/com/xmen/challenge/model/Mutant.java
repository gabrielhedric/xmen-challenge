package com.xmen.challenge.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("MUTANTS")
public class Mutant {

    @Id
    private Long id;
    private String name;
    private Dna dna;
}
