package src.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.EmployeeRecord;
import src.main.implementation.PayrollReport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PayrollReportTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    String getConsoleOutput() {
        return outputStream.toString().trim();
    }


    @Test
    public void totalEmployees() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "ONBOARD", "3000", "10-10-2022", ""),
                new EmployeeRecord(2, "emp102", "Bill", "Gates", "SE", "ONBOARD", "1000", "10-10-2022", ""),
                new EmployeeRecord(3, "emp103", "Steve", "Jobs", "Architect", "ONBOARD", "4000", "10-10-2022", "")
        );

        PayrollReport.totalEmployees(records);

        String consoleOutput = getConsoleOutput();

        assertTrue(consoleOutput.contains("1. Total number of employees: 3"));
    }

    @Test
    public void monthlyJoins() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "ONBOARD", "10-10-2022", "10-10-2022", ""),
                new EmployeeRecord(2, "emp102", "Steve", "Jobs", "Architect", "ONBOARD", "10-10-2022", "10-10-2022", "")
        );

        PayrollReport.monthlyJoins(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("Month: 10-2022-> Total Joins: 2"));
        assertTrue(consoleOutput.contains("emp101: Bill Gates, SE"));
        assertTrue(consoleOutput.contains("emp102: Steve Jobs, Architect"));
    }

    @Test
    public void monthlyExits() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "ONBOARD", "10-10-2022", "10-10-2022", ""),
                new EmployeeRecord(2, "emp102", "Steve", "Jobs", "Architect", "ONBOARD", "10-10-2022", "10-10-2022", ""),
                new EmployeeRecord(3, "emp103", "Steve", "Gates", "Architect", "ONBOARD", "10-10-2022", "10-10-2022", ""),
                new EmployeeRecord(4, "emp101", "Bill", "Gates", "SE", "EXIT", "10-10-2023", "10-09-2023", ""),
                new EmployeeRecord(5, "emp102", "Steve", "Jobs", "Architect", "EXIT", "10-10-2023", "10-09-2023", ""),
                new EmployeeRecord(6, "emp103", "Steve", "Gates", "Architect", "EXIT", "10-10-2023", "10-09-2023", "")
        );

        PayrollReport.monthlyExits(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("Month: 09-2023-> Total Exits: 3"));
        assertTrue(consoleOutput.contains("emp101: Bill Gates"));
        assertTrue(consoleOutput.contains("emp102: Steve Jobs"));
        assertTrue(consoleOutput.contains("emp103: Steve Gates"));
    }

    @Test
    public void monthlySalary() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "SALARY", "5000", "10-06-2022", ""),
                new EmployeeRecord(2, "emp102", "Steve", "Jobs", "Architect", "SALARY", "2000", "10-10-2023", ""),
                new EmployeeRecord(3, "emp103", "Steve", "Gates", "Architect", "SALARY", "1000", "10-10-2023", "")
        );

        PayrollReport.monthlySalary(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("3. Monthly Salary:"));
        assertTrue(consoleOutput.contains("Month: 10-2023, Total Salary: 3000.0, Total Employees: 2"));
        assertTrue(consoleOutput.contains("Month: 06-2022, Total Salary: 5000.0, Total Employees: 1"));
    }

    @Test
    public void employeeFinancialReport() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "ONBOARD", "3000", "10-10-2022", ""),
                new EmployeeRecord(2, "emp101", "Bill", "Gates", "SE", "SALARY", "3000", "10-10-2022", ""),
                new EmployeeRecord(3, "emp101", "Bill", "Gates", "SE", "BONUS", "1000", "10-10-2022", ""),
                new EmployeeRecord(4, "emp102", "Steve", "Jobs", "Architect", "ONBOARD", "4000", "10-10-2022", ""),
                new EmployeeRecord(5, "emp102", "Steve", "Jobs", "Architect", "SALARY", "4000", "10-10-2022", "")
        );

        PayrollReport.employeeFinancialReport(records);

        String consoleOutput = getConsoleOutput();

        assertTrue(consoleOutput.contains("Employee ID: emp101, Name: Bill Gates, Total Paid: 4000"));
        assertTrue(consoleOutput.contains("Employee ID: emp102, Name: Steve Jobs, Total Paid: 4000"));
    }

    @Test
    public void monthlyAmount() {

        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "SALARY", "3000", "10-10-2022", ""),
                new EmployeeRecord(2, "emp102", "Steve", "Jobs", "Architect", "BONUS", "2000", "10-10-2022", ""),
                new EmployeeRecord(3, "emp103", "Steve", "Gates", "Architect", "REIMBURSEMENT", "1000", "10-10-2022", "")
        );

        PayrollReport.monthlyAmount(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("Month: 10-2022, Total Amount Released: 6000.0, Total Employees: 3"));
    }

    @Test
    public void yearlyFinancialReport() {
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "SALARY", "3000", "10-10-2022", ""),
                new EmployeeRecord(2, "emp101", "Bill", "Gates", "SE", "BONUS", "1000", "10-10-2022", ""),
                new EmployeeRecord(3, "emp101", "Bill", "Gates", "SE", "REIMBURSEMENT", "500", "10-10-2022", "")
        );

        PayrollReport.yearlyFinancialReport(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("Event: SALARY, EmpId: emp101, EventDate: 10-10-2022, Value: 3000"));
        assertTrue(consoleOutput.contains("Event: BONUS, EmpId: emp101, EventDate: 10-10-2022, Value: 1000"));
        assertTrue(consoleOutput.contains("Event: REIMBURSEMENT, EmpId: emp101, EventDate: 10-10-2022, Value: 500"));
    }

    @Test
    public void yearlyFinancialReport_MultipleEmployees(){
        List<EmployeeRecord> records = Arrays.asList(
                new EmployeeRecord(1, "emp101", "Bill", "Gates", "SE", "SALARY", "3000", "10-10-2022", ""),
                new EmployeeRecord(2, "emp102", "Steve", "Jobs", "Architect", "BONUS", "2000", "11-10-2022", "")
        );

        PayrollReport.yearlyFinancialReport(records);

        String consoleOutput = getConsoleOutput();
        assertTrue(consoleOutput.contains("Event: SALARY, EmpId: emp101, EventDate: 10-10-2022, Value: 3000"));
        assertTrue(consoleOutput.contains("Event: BONUS, EmpId: emp102, EventDate: 11-10-2022, Value: 2000"));
    }
}