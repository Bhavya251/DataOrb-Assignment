package src.main;

import src.main.implementation.PayrollReport;
import src.main.implementation.ReadFile;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Payroll Processing System: ");


        //Enter File Path
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path:");
        String fileName = scanner.nextLine();
        //String fileName = "Employee_data.txt";  C:\Users\patel\Downloads\Employee_details.txt


        //Read File and Parse Data
        try {
            List<EmployeeRecord> records = ReadFile.parseFile(fileName);

            //Print Records
            PayrollReport.generateReports(records);

        } catch (IOException e) {
            System.err.println("File error: " + e);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            scanner.close();
            System.out.println("End of Payroll Processing System.");
        }
    }
}
