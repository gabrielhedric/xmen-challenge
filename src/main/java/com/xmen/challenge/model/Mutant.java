package com.xmen.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("MUTANTS")
public class Mutant {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("DNA")
    private ArrayList<String> seq = new ArrayList<>();
}
