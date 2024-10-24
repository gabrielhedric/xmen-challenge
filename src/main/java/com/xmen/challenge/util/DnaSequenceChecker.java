package com.xmen.challenge.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class DnaSequenceChecker {

    public boolean hasHorizontalSequence(char[][] dnaMutant, int size){
        return IntStream.range(0, size).anyMatch(row ->
                IntStream.range(0, size - 3).anyMatch(col ->
                        IntStream.range(0, 4).allMatch(k -> dnaMutant[row][col + k] == dnaMutant[row][col])
                )
        );
    }

    public boolean hasVerticalSequence(char[][] dnaMutant, int size){
        return IntStream.range(0, size).anyMatch(row ->
                IntStream.range(0, size - 3).anyMatch(col ->
                        IntStream.range(0, 4).allMatch(k -> dnaMutant[row][col + k] == dnaMutant[row][col])
                )
        );
    }

    public boolean hasDescendingDiagonalSequence(char[][] dnaMutant, int size){
        return IntStream.range(0, size - 3).anyMatch(row ->
                IntStream.range(0, size - 3).anyMatch(col ->
                        IntStream.range(0, 4).allMatch(k -> dnaMutant[row + k][col + k] == dnaMutant[row][col])
                )
        );
    }

    public boolean hasAscendingDiagonalSequence(char[][] dnaMutant, int size){
        return IntStream.range(3, size).anyMatch(row ->
                IntStream.range(0, size - 3).anyMatch(col ->
                        IntStream.range(0, 4).allMatch(k -> dnaMutant[row - k][col + k] == dnaMutant[row][col])
                )
        );
    }

    public boolean isValidDna(List<String> dnaList){
        for (String row : dnaList){
            if(!row.matches("[ATCG]+")){
                return false;
            }
        }
        return true;
    }
}
