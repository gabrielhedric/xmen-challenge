package com.xmen.challenge.service;

import com.xmen.challenge.exception.InvalidDnaException;
import com.xmen.challenge.model.Mutant;
import com.xmen.challenge.util.DnaSequenceChecker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantDetectorService {

    private final DnaSequenceChecker dnaSequenceChecker;

    public MutantDetectorService(DnaSequenceChecker dnaSequenceChecker) {
        this.dnaSequenceChecker = dnaSequenceChecker;
    }


    public boolean isMutantSequence(Mutant mutant){
        List<String> dnaList = mutant.getDna().getSequence();
        int dnaSize = dnaList.size();

        if(!dnaSequenceChecker.isValidDna(dnaList)){
            throw new InvalidDnaException("Invalid DNA sequence, characters not allowed.");
        }

        char[][] dnaMutant = new char[dnaSize][dnaSize];

        for(int i = 0; i < dnaSize; i++){
            dnaMutant[i] = dnaList.get(i).toCharArray();
        }

        return dnaSequenceChecker.hasHorizontalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasVerticalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasDescendingDiagonalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasAscendingDiagonalSequence(dnaMutant, dnaSize);

    }



}
