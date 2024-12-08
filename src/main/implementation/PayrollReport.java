package src.main.implementation;

import src.main.EmployeeRecord;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollReport {
    public static void generateReports(List<EmployeeRecord> records){
        System.out.println("Payroll Reports: ");

        //Task-1
        totalEmployees(records);

        //Task-2 - A
        monthlyJoins(records);

        //Task-2 - B
        monthlyExits(records);

        //Task-3
        monthlySalary(records);

        //Task-4
        employeeFinancialReport(records);

        //Task-5
        monthlyAmount(records);

        //Task-6
        yearlyFinancialReport(records);
    }

    public static void totalEmployees(List<EmployeeRecord> records) {
        int totalEmp = (int) records.stream().map(EmployeeRecord::getEmpID).distinct().count();
        System.out.println("1. Total number of employees: " + totalEmp);
    }

    public static void monthlyJoins(List<EmployeeRecord> records){
        System.out.println("\n\n2-A. Monthly Joins: ");
        Map<String, List<EmployeeRecord>> joins = records.stream()
                .filter(record -> record.getEvent().equals("ONBOARD"))
                .collect(Collectors.groupingBy(record -> record.getEventDate().substring(3)));

        for(String month : joins.keySet()){
            System.out.println("Month: " + month + "-> Total Joins: " + joins.get(month).size());
            for(EmployeeRecord record : joins.get(month)) {
                System.out.println(record.getEmpID() + ": " + record.getEmpFName() + " " + record.getEmpLName() +
                        ", " + record.getDesignation());
            }
        }
    }

    public static void monthlyExits(List<EmployeeRecord> records){
        System.out.println("\n\n2-B. Monthly Exits: ");
        Map<String, List<EmployeeRecord>> exits = records.stream()
                .filter(record -> record.getEvent().equals("EXIT"))
                .collect(Collectors.groupingBy(record -> record.getEventDate().substring(3)));

        Map<String, String> empNames = records.stream()
                .filter(record -> record.getEvent().equals("ONBOARD"))
                .collect(Collectors.toMap(
                        EmployeeRecord::getEmpID,
                        record -> record.getEmpFName() + " " + record.getEmpLName()
                ));

        for(String month : exits.keySet()){
            System.out.println("Month: " + month + "-> Total Exits: " + exits.get(month).size());
            for(EmployeeRecord record : exits.get(month)) {
                System.out.println(record.getEmpID() + ": " + empNames.get(record.getEmpID()));
            }
        }
    }

    public static void monthlySalary(List<EmployeeRecord> records){
        System.out.println("\n\n3. Monthly Salary: ");
        Map<String, Double> totalSalaries = records.stream()
                .filter(record -> record.getEvent().equals("SALARY"))
                .collect(Collectors.groupingBy(
                        record -> record.getEventDate().substring(3),
                        Collectors.summingDouble(record -> Double.parseDouble(record.getValue()))
                ));

        Map<String, Long> employeeCount = records.stream()
                .filter(record -> record.getEvent().equals("SALARY"))
                .collect(Collectors.groupingBy(
                        record -> record.getEventDate().substring(3),
                        Collectors.counting()
                ));

        for (String month : totalSalaries.keySet()) {
            System.out.println("Month: " + month + ", Total Salary: " + totalSalaries.get(month) +
                    ", Total Employees: " + employeeCount.get(month));
        }
    }

    public static void employeeFinancialReport(List<EmployeeRecord> records){
        System.out.println("\n\n4. Employee Financial Report: ");
        Map<String, Integer> totalPayments = records.stream()
                .filter(record -> !record.getEvent().equals("ONBOARD") && !record.getEvent().equals("EXIT"))
                .collect(Collectors.groupingBy(
                        EmployeeRecord::getEmpID,
                        Collectors.summingInt(record -> Integer.parseInt(record.getValue()))
                ));

        Map<String, String> empNames = records.stream()
                .filter(record -> record.getEvent().equals("ONBOARD"))
                .collect(Collectors.toMap(
                        EmployeeRecord::getEmpID,
                        record -> record.getEmpFName() + " " + record.getEmpLName()
                ));

        for (String empId : totalPayments.keySet()) {
            System.out.println("Employee ID: " + empId + ", Name: " + empNames.get(empId) +
                    ", Total Paid: " + totalPayments.get(empId));
        }
    }

    public static void monthlyAmount(List<EmployeeRecord> records){
        System.out.println("\n\n5. Monthly Amount: ");
        Map<String, Double> totalAmount = records.stream()
                .filter(record -> !record.getEvent().equals("ONBOARD") && !record.getEvent().equals("EXIT"))
                .collect(Collectors.groupingBy(
                        record -> record.getEventDate().substring(3),
                        Collectors.summingDouble(record -> Double.parseDouble(record.getValue()))
                ));

        Map<String, Long> empCount = records.stream()
                .filter(record -> !record.getEvent().equals("ONBOARD") && !record.getEvent().equals("EXIT"))
                .collect(Collectors.groupingBy(
                        record -> record.getEventDate().substring(3),
                        Collectors.counting()
                ));

        for (String month : totalAmount.keySet()) {
            System.out.println("Month: " + month + ", Total Amount Released: " + totalAmount.get(month) +
                    ", Total Employees: " + empCount.get(month));
        }
    }

    public static void yearlyFinancialReport(List<EmployeeRecord> records){
        System.out.println("\n\n6. Yearly Financial Report: ");
        Map<Integer, List<EmployeeRecord>> yearlyReports = records.stream()
                .collect(Collectors.groupingBy(
                        record -> Integer.parseInt(record.getEventDate().substring(6))
                ));

        for (Integer year : yearlyReports.keySet()) {
            System.out.println("Year: " + year);
            for (EmployeeRecord record : yearlyReports.get(year)) {
                System.out.println("  Event: " + record.getEvent() + ", EmpId: " + record.getEmpID() +
                        ", EventDate: " + record.getEventDate() + ", Value: " + record.getValue());
            }
        }
    }
}
