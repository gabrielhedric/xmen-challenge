package com.xmen.challenge.service;

import com.xmen.challenge.exception.InvalidDnaException;
import com.xmen.challenge.exception.MutantAlreadyValidatedException;
import com.xmen.challenge.model.Mutant;
import com.xmen.challenge.repository.MutantRepository;
import com.xmen.challenge.util.DnaSequenceChecker;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class MutantDetectorService {

    private final DnaSequenceChecker dnaSequenceChecker;
    private final MutantRepository mutantRepository;

    public MutantDetectorService(DnaSequenceChecker dnaSequenceChecker, MutantRepository mutantRepository) {
        this.dnaSequenceChecker = dnaSequenceChecker;
        this.mutantRepository = mutantRepository;
    }


    public boolean isMutantSequence(Mutant mutant){
        List<String> dnaList = mutant.getSeq();
        int dnaSize = dnaList.size();

        if(!dnaSequenceChecker.isValidDna(dnaList)){
            throw new InvalidDnaException("Invalid DNA sequence, characters not allowed.");
        }

        char[][] dnaMutant = new char[dnaSize][dnaSize];

        IntStream.range(0, dnaSize).forEach(i -> dnaMutant[i] = dnaList.get(i).toCharArray());

        return dnaSequenceChecker.hasHorizontalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasVerticalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasDescendingDiagonalSequence(dnaMutant, dnaSize) ||
                dnaSequenceChecker.hasAscendingDiagonalSequence(dnaMutant, dnaSize);

    }

    public void saveMutant(Mutant mutant){
        try {
            mutantRepository.save(mutant);
        } catch (DbActionExecutionException e) {
            throw new MutantAlreadyValidatedException("This mutant has already been validated and is part of our army");
        }
    }

    public List<Mutant> list(){
        return mutantRepository.findAll();
    }

}
