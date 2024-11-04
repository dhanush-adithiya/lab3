
public class EmployeePayroll {

    public static abstract class Employee {
        // This defines an abstract class that all the other classes that derive this can use, It has a few predefined methods such as getDetails, getName, getId etc that has genetic print statements, and soem abstract functions that each derived class has to override.

        int empId;
        String empName;
        String empDesignation;

        public Employee(int empId, String empName, String empDesignation) {
            this.empId = empId;
            this.empName = empName;
            this.empDesignation = empDesignation;
        }

        public abstract double calculateBonus(double bonus);

        public abstract double calculateAnnualEarning();

        public void getDetails() {
            System.out.println("Name: " + this.empName + "ID: " + this.empId + "Designation: " + this.empDesignation);
        }

        public void getName() {
            System.out.print("Name: " + this.empName);
        }

        public void getId() {
            System.out.println("ID: " + this.empId);
        }

        public void getDesignation() {
            System.out.println("Designation: " + this.empDesignation);
        }
    }

    public static class HourlyEmployee extends Employee {

        // This is a class for the hourly emplyee it has two new fields hourlyRate and hoursWorked, it has a differnet way to calculate salary with hoursWorked * hourlyRate
        double hourlyRate;
        int hoursWorked;

        public HourlyEmployee(int empId, String empName, String empDesignation, double hourlyRate, int hoursWorked) {
            super(empId, empName, empDesignation);
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        @Override
        public void getDetails() {
            super.getDetails();
            System.out.println("Hourly Rate: " + this.hourlyRate + "Hours Worked: " + this.hoursWorked);
        }

        public void getHourlyRate() {
            System.out.println("Hourly Rate: " + this.hourlyRate);
        }

        public void getHoursWorked() {
            System.out.println("Hours Worked: " + this.hoursWorked);
        }

        public double calculateSalary() {
            return this.hoursWorked * this.hourlyRate;
        }

        @Override
        public double calculateBonus(double bonus) {
            return calculateSalary() + bonus;
        }

        @Override
        public double calculateAnnualEarning() {
            return calculateSalary() * 56;
        }
    }

    public static class SalariedEmployees extends Employee {
        // This is a class for SalariedEmployeed that is an extension of employee which has a montly salary field

        double monthlySalary;

        public SalariedEmployees(int empId, String empName, String empDesignation, double salary) {
            super(empId, empName, empDesignation);
            this.monthlySalary = salary;
        }

        @Override
        public void getDetails() {
            super.getDetails();
            System.out.println("Monthly Salary: " + this.monthlySalary);
        }

        public void getSalary() {
            System.out.println("Monthly Salary: " + this.monthlySalary);
        }

        public double calculateSalary() {
            return monthlySalary / 4;
        }

        @Override
        public double calculateBonus(double bonus) {
            return calculateSalary() + bonus;
        }

        @Override
        public double calculateAnnualEarning() {
            return (calculateSalary() * 4) * 12;
        }
    }

    public static class ExecutiveEmployee extends SalariedEmployees {
        // This class is an extension of SalariedEmployees but this class has a bonus percentage.

        double bonusPercentage;

        public ExecutiveEmployee(int empId, String empName,
                String empDesignation, double salary,
                double bonusPercentage) {
            super(empId, empName, empDesignation, salary);
            this.bonusPercentage = bonusPercentage;
        }

        @Override
        public double calculateBonus(double bonus) {
            return super.calculateBonus(this.bonusPercentage);
        }
    }

    public static void displayPayroll(Employee[] employees) {
        // This function takes an array of objects of the class Employee and then returns the total salaries paid along with individual salaries paid to each emp.
        double totalEarning = 0;

        for (Employee emp : employees) {
            System.out.print("Employee ");
            emp.getName();

            double earning = emp.calculateAnnualEarning();
            System.out.print(" Salary: " + earning + "\n");
            totalEarning += earning;
        }

        System.out.println("Total Salaries Paid: " + totalEarning);
    }

    public static void main(String[] args) {
        // Instantiate an HourlyEmployee
        EmployeePayroll.HourlyEmployee hourlyEmployee = new EmployeePayroll.HourlyEmployee(
                1, "John Doe", "Lab Assistant", 25.0, 40);

        // Instantiate a SalariedEmployee
        EmployeePayroll.SalariedEmployees salariedEmployee = new EmployeePayroll.SalariedEmployees(
                2, "Jane Smith", "Office Manager", 4000.0);

        // Instantiate an ExecutiveEmployee
        EmployeePayroll.ExecutiveEmployee executiveEmployee = new EmployeePayroll.ExecutiveEmployee(
                3, "Alice Johnson", "Department Head", 8000.0, 10.0);  // 10% bonus percentage

        // Display information and calculations for HourlyEmployee
        System.out.println("Hourly Employee Details:");
        hourlyEmployee.getDetails();
        System.out.println("Weekly Salary: " + hourlyEmployee.calculateSalary());
        System.out.println("Bonus: " + hourlyEmployee.calculateBonus(200));  // Example bonus
        System.out.println("Annual Earnings: " + hourlyEmployee.calculateAnnualEarning());
        System.out.println();

        // Display information and calculations for SalariedEmployee
        System.out.println("Salaried Employee Details:");
        salariedEmployee.getDetails();
        System.out.println("Weekly Salary: " + salariedEmployee.calculateSalary());
        System.out.println("Bonus: " + salariedEmployee.calculateBonus(500));  // Example bonus
        System.out.println("Annual Earnings: " + salariedEmployee.calculateAnnualEarning());
        System.out.println();

        // Display information and calculations for ExecutiveEmployee
        System.out.println("Executive Employee Details:");
        executiveEmployee.getDetails();
        System.out.println("Weekly Salary: " + executiveEmployee.calculateSalary());
        System.out.println("Bonus: " + executiveEmployee.calculateBonus(1000));  // Example bonus
        System.out.println("Annual Earnings: " + executiveEmployee.calculateAnnualEarning());

        Employee[] employees = {hourlyEmployee, salariedEmployee,
            executiveEmployee};
        displayPayroll(employees);
    }
}
