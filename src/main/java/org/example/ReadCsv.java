package org.example;

import com.opencsv.CSVReaderBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {
    private String filename;
    ReadCsv (String filename){
        this.filename = filename;
    }
    public List<String[]> readCsv() {
        List<String[]> allData = new ArrayList<>();
        try {
            Path path = Paths.get(
                    ClassLoader.getSystemResource(filename).toURI());
            Reader reader = Files.newBufferedReader(path);
            com.opencsv.CSVReader csvReader = new CSVReaderBuilder(reader).build();
            allData = csvReader.readAll();

        } catch (Exception e){
            e.printStackTrace();
        }
        return allData;
    }
}
