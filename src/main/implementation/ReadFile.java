package src.main.implementation;

import src.main.EmployeeRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<EmployeeRecord> parseFile(String fileName) throws IOException {
        List<EmployeeRecord> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = "";

        //Read File and Parse Data
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 9) {
                records.add(new EmployeeRecord(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        parts[5].trim(),
                        parts[6].trim(),
                        parts[7].trim(),
                        parts[8].trim()
                ));
            } else if (parts.length == 6) { // For events other than ONBOARD
                records.add(new EmployeeRecord(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        null,
                        null,
                        null,
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        parts[5].trim()
                ));
            }
        }
        reader.close();
        return records;
    }
}
