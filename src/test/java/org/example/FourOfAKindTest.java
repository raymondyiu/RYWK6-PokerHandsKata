package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FourOfAKindTest {
    public List<String[]> readCsv(String filename) {
        List<String[]> allData = new ArrayList<>();
        try {
            Path path = Paths.get(
                    ClassLoader.getSystemResource(filename).toURI());
            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            allData = csvReader.readAll();

        } catch (Exception e){
            e.printStackTrace();
        }
        return allData;
    }

    @Test
    void isGetScore(){
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertEquals(7000, fourOfAKind.getScore());
    }
    @Test
    void isMatch() {
    }
}