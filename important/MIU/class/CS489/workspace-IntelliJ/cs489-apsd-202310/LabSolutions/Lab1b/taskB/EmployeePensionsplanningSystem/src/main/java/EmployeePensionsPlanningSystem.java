import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import model.Employee;
import model.PensionPlan;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeePensionsPlanningSystem {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setDateFormat(new StdDateFormat().withColonInTimeZone(true));

    private List<Employee> employees = new ArrayList<>();
    private List<PensionPlan> pensionPlans = new ArrayList<>();

    public static void main(String[] args) {
        EmployeePensionsPlanningSystem app = new EmployeePensionsPlanningSystem();
        app.loadData(); // Load sample data
        app.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Print all employee information (including pension plans)");
            System.out.println("2. Generate the Monthly Upcoming Enrollees report");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listEmployees();
                    break;
                case 2:
                    generateUpcomingEnrolleesReport();
                    break;
                case 3:
                    saveData(); // Save data before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please enter a valid option.");
            }
        }
    }

    private void listEmployees() {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName)
                        .thenComparing(Employee::getYearlySalary,Comparator.reverseOrder()))
                .collect(Collectors.toList());

        try {
            String employeeJson = objectMapper.writeValueAsString(sortedEmployees);
            System.out.println("All employee information (sorted by last name ascending and yearly salary descending):\n" + employeeJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateUpcomingEnrolleesReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(employee -> isQualifiedForEnrollment(employee, calendar))
                .filter(employee -> !isEnrolledInPensionPlan(employee))
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        try {
            String enrolleesJson = objectMapper.writeValueAsString(upcomingEnrollees);
            System.out.println("Monthly Upcoming Enrollees report:\n" + enrolleesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isQualifiedForEnrollment(Employee employee, Calendar nextMonth) {
        Calendar employmentDate = Calendar.getInstance();
        employmentDate.setTime(employee.getEmploymentDate());
        employmentDate.add(Calendar.YEAR, 5);

        return employmentDate.compareTo(nextMonth) <= 0;
    }

    private boolean isEnrolledInPensionPlan(Employee employee) {
        return pensionPlans.stream()
                .anyMatch(p -> p.getEmployeeId() == employee.getEmployeeId());
    }

    private void loadData() {
        try {
            // Load employee and pension plan data from JSON files (or any other data source)
            employees.add(new Employee(1, "Daniel", "Agar", parseDate("2018-01-17"), 105945.50));
            employees.add(new Employee(2, "Benard", "Shaw", parseDate("2018-10-03"), 197750.00));
            employees.add(new Employee(3, "Carly", "Agar", parseDate("2014-05-16"), 842000.75));
            employees.add(new Employee(4, "Wesley", "Schneider", parseDate("2018-11-02"), 74500.00));
            employees.get(0).setPensionPlan(new PensionPlan("EX1089", parseDate("2023-01-17"), 100.00, 1));
            employees.get(2).setPensionPlan(new PensionPlan("SM2307", parseDate("2019-11-04"), 1555.50, 3));

            pensionPlans.add(new PensionPlan("EX1089", parseDate("2023-01-17"), 100.00, 1));
            pensionPlans.add(new PensionPlan("SM2307", parseDate("2019-11-04"), 1555.50, 3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateStr) throws ParseException {
        return DATE_FORMAT.parse(dateStr);
    }

    private void saveData() {
        // Save data to JSON files or another data source for future use
    }
}
