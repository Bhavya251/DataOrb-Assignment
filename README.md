
# Payroll Processing and Reporting System

This project is a Java-based payroll processing and reporting application that reads employee records from a file and generates various reports based on the data.

## Features

1. **Total Number of Employees**:
   - Displays the total number of employees in the organization.

2. **Month-wise Employee Details**:
   - **Joins**: Displays the total number of employees who joined, including their details.
   - **Exits**: Displays the total number of employees who exited, including their names and surnames.

3. **Monthly Salary Report**:
   - Displays the total salary paid and the number of employees for each month.

4. **Employee-wise Financial Report**:
   - Displays details such as Employee ID, Name, Surname, and the total amount paid to each employee.

5. **Monthly Amount Released**:
   - Displays the total amount released (including Salary, Bonus, and Reimbursements) and the number of employees for each month.

6. **Yearly Financial Report**:
   - Displays events such as SALARY, BONUS, REIMBURSEMENT, along with details like Employee ID, Event Date, and Event Value.

## Technologies Used

- **Java**: Core programming language.
- **JUnit**: For writing test cases.
- **Streams and Collections**: Used for processing data efficiently.
- **File Handling**: To read employee records from a file.

## Project Structure

### `Main.java`
The entry point of the application. It reads the employee data file and orchestrates the execution of various reports.

### `PayrollReports.java`
Contains methods for generating reports. Each report is implemented as a separate method.

### `EmployeeRecord.java`
A POJO class representing an employee record. It includes fields like Employee ID, Name, Surname, Designation, Event Type, Event Value, and Event Date.

### `ReadFile.java`
Handles reading employee data from a file and converting it into a list of `EmployeeRecord` objects.

### `Test Cases`
JUnit test cases verify the correctness of the reports generated.

## File Format for Employee Records

The application expects an input file named `Employee_details.txt` with records in the following format:

```
1, emp101, Bill, Gates, Software Engineer, ONBOARD, 1-11-2022, 10-10-2022, "Bill Gates is going to join DataOrb on 1st November as a SE."
2, emp102, REIMBURSEMENT, 1-10-2022, 10-10-2022, "Steve Jobs joined DataOrb on 1st October as an Architect."
3, emp103, EXIT, 15-10-2022, 10-10-2022, "Martin has joined DataOrb as a SE."
4, emp102, SALARY, 3000, 10-10-2022, "Oct Salary of Steve."
5, emp102, BONUS, 3000, 10-10-2022, "Oct Salary of Steve."
```

## How to Run

1. Clone the repository.
2. Compile the project using:
   ```
   javac -d bin src/*.java
   ```
3. Run the application using:
   ```
   java -cp bin Main
   ```
4. Rename file to `Employee_details.txt` and enter the file path.
5. Check the console for results.
