package com.xmen.challenge.service;

import com.xmen.challenge.exception.InvalidDnaException;
import com.xmen.challenge.exception.MutantAlreadyValidatedException;
import com.xmen.challenge.model.Mutant;
import com.xmen.challenge.repository.MutantRepository;
import com.xmen.challenge.util.DnaSequenceChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MutantDetectorServiceTest {

    private MutantDetectorService mutantDetectorService;
    private DnaSequenceChecker dnaSequenceChecker;
    private MutantRepository mutantRepository;

    @BeforeEach
    void setUp() {
        dnaSequenceChecker = mock(DnaSequenceChecker.class);
        mutantRepository = mock(MutantRepository.class);
        mutantDetectorService = new MutantDetectorService(dnaSequenceChecker, mutantRepository);
    }

    @Test
    void testIsMutantSequenceWithHorizontalMatch() {
        List<String> dnaList = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        );
        Mutant mutant = new Mutant(1L, "Mutant1", new ArrayList<>(dnaList));

        when(dnaSequenceChecker.isValidDna(anyList())).thenReturn(true);
        when(dnaSequenceChecker.hasHorizontalSequence(any(char[][].class), anyInt())).thenReturn(true);

        assertTrue(mutantDetectorService.isMutantSequence(mutant));
    }

    @Test
    void testIsMutantSequenceWithInvalidDna() {
        List<String> dnaList = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCXTA",
                "TCACTG"
        );
        Mutant mutant = new Mutant(1L, "Mutant1", new ArrayList<>(dnaList));

        when(dnaSequenceChecker.isValidDna(anyList())).thenReturn(false);

        assertThrows(InvalidDnaException.class, () -> mutantDetectorService.isMutantSequence(mutant));
    }

    @Test
    void testSaveMutantWithDuplicateKeyException() {
        List<String> dnaList = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        );
        Mutant mutant = new Mutant(1L, "Mutant1", new ArrayList<>(dnaList));

        doThrow(DbActionExecutionException.class).when(mutantRepository).save(any(Mutant.class));

        assertThrows(MutantAlreadyValidatedException.class, () -> mutantDetectorService.saveMutant(mutant));
    }

    @Test
    void testSaveMutantSuccessfully() {
        List<String> dnaList = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        );
        Mutant mutant = new Mutant(1L, "Mutant1", new ArrayList<>(dnaList));

        when(mutantRepository.save(any(Mutant.class))).thenReturn(mutant);

        assertDoesNotThrow(() -> mutantDetectorService.saveMutant(mutant));
    }
}
