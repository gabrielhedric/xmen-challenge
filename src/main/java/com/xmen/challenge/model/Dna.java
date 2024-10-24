package com.xmen.challenge.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dna {
    private List<String> sequence = new ArrayList<>();
}
