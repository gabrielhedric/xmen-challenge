package com.xmen.challenge.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DnaSequenceChecker {

    public boolean hasHorizontalSequence(char[][] dnaMutant, int size){
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size - 3; col++){
                boolean result = true;
                for(int k = 0; k < 4; k++) {
                    if(dnaMutant[row][col + k] != dnaMutant[row][col]){
                        result = false;
                        break;
                    }
                }
                if(result){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasVerticalSequence(char[][] dnaMutant, int size){
        for(int col = 0; col < size; col++){
            for(int row = 0; row < size -3; row++){
                boolean result = true;
                for(int k = 0; k < 4; k++) {
                    if(dnaMutant[row + k][col] != dnaMutant[row][col]){
                        result = false;
                        break;
                    }
                }
                if(result){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasDescendingDiagonalSequence(char[][] dnaMutant, int size){
        for (int row = 0; row < size - 3; row++){
            for(int col = 0; col < size - 3; col++){
                boolean result = true;
                for(int k = 0; k < 4; k++){
                    if(dnaMutant[row + k][col + k] != dnaMutant[row][col]){
                        result = false;
                        break;
                    }
                }
                if(result){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasAscendingDiagonalSequence(char[][] dnaMutant, int size){
        for (int row = 3; row < size; row++){
            for(int col = 0; col < size - 3; col++){
                boolean result = true;
                for(int k = 0; k < 4; k++){
                    if(dnaMutant[row - k][col + k] != dnaMutant[row][col]){
                        result = false;
                        break;
                    }
                }
                if(result){
                    return true;
                }
            }
        }
        return false;
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
