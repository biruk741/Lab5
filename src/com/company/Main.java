package com.company;

import com.company.classes.Company;
import com.company.classes.Employee;
import com.company.classes.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static Company[] companies, companies1, companies2, companies3;

    public static Employee[] employees, employees1, employees2, employees3;

    public static int counter = 0;

    public static void main(String[] args) {

        printResults(companies, employees, 1);
        printResults(companies1, employees1, 2); // write-up example
        printResults(companies2, employees2, 3);
        printResults(companies3, employees3, 4);
    }

    private static void printPairs(List<Pair> pairs) {
        for (Pair pair : pairs) System.out.println(pair);
    }

    /**
     * Takes an array of Companies and their preferences, and an array of Employees
     * and their preferences.
     *
     * @return A list of sufficient pairs of companies and employees.
     */
    private static List<Pair> process(Company[] companies, Employee[] employees) {
        List<Pair> pairs = new ArrayList<>();
        List<Pair> result = new ArrayList<>();


        for (Company company : companies) {
            String[] preferences = company.getPreferences();
            for (int position = 0; position < preferences.length; position++) {
                String currentPreference = preferences[position];
                pairs.add(new Pair(
                        company,
                        Arrays.stream(employees)
                                .filter(employee -> employee.nameEquals(currentPreference))
                                .findFirst().orElse(null),
                        preferences.length - position
                ));
            }
        }

        // sort the pairings

        pairs.sort((pair1, pair2) -> {
            Integer happiness1 = calculateHappiness(pair1);
            Integer happiness2 = calculateHappiness(pair2);
            counter++;
            return happiness1.compareTo(happiness2) * -1;
        });

        // find first occurrences of each
        // add each occurrence to result array

        for (Pair p1 : pairs) {
            if (result.stream().noneMatch(p ->
                    p.getCompany().equals(p1.getCompany()) ||
                            p.getEmployee().equals(p1.getEmployee())
            )) {
                result.add(p1);
            }
        }

        return result;
    }

    private static int calculateHappiness(Pair pair) {
        int point1 = pair.getPosition();
        Employee employee = Arrays.stream(employees).filter(e ->
                e.getName().equals(pair.getEmployee().getName()))
                .findFirst().orElse(null);

        int point2 = 0;

        assert employee != null;
        String[] preferences = employee.getPreferences();
        for (int i = 0; i < preferences.length; i++) {
            counter++;
            if (preferences[i].equals(pair.getCompany().getName())) {
                point2 = preferences.length - i;
            }
        }

        return point1 + point2;
    }

    /**
     * Prints the results of process.
     *
     * @param companies  array of companies and their preferences
     * @param employees  array of employees and their preferences
     * @param testNumber the test number.
     */
    private static void printResults(Company[] companies, Employee[] employees, int testNumber) {
        long time1 = System.currentTimeMillis();
        List<Pair> pairs = process(companies, employees);
        long time2 = System.currentTimeMillis();

        printPairs(pairs);

        System.out.println("Results of test #" + testNumber + ":");
        System.out.println("Time taken: " + (time2 - time1));
        System.out.println("Made " + counter + " comparisons. \n");
        counter = 0;
    }

    private static boolean foundBetter(Pair[] result, Employee[] employees, Company[] companies){
        for (Pair pair: result){
            List<List<Pair>> companyPairs = Arrays.stream(companies).map(
                    company -> {
                        List<Pair> pairs = new ArrayList<>();
                        String[] prefs = company.getPreferences();
                        for (int i = 0; i < prefs.length; i++) {
                            String pref = prefs[i];
                            if (pair.getEmployee().nameEquals(pref)){
                                pairs.add(new Pair(
                                        company,
                                        new Employee(pref, pair.getEmployee().getPreferences()),
                                        companies.length - i
                                ));
                            }
                        }
                        return pairs;
                    }
            ).collect(Collectors.toList());

            List<List<Pair>> employeePairs = Arrays.stream(employees).map(
                    employee -> {
                        List<Pair> pairs = new ArrayList<>();
                        String[] prefs = employee.getPreferences();
                        for (int i = 0; i < prefs.length; i++) {
                            String pref = prefs[i];
                            if (pair.getCompany().nameEquals(pref)){
                                pairs.add(new Pair(
                                        new Company(pref, pair.getCompany().getPreferences()),
                                        employee,
                                        employees.length - i
                                ));
                            }
                        }
                        return pairs;
                    }
            ).collect(Collectors.toList());
        }

    }

    private static void populateArrays() {
        employees = new Employee[]{
                new Employee("1", new String[]{"A", "B", "C", "D", "E"}),
                new Employee("2", new String[]{"E", "A", "B", "C", "D"}),
                new Employee("3", new String[]{"E", "B", "A", "D", "C"}),
                new Employee("4", new String[]{"E", "C", "D", "A", "B"}),
                new Employee("5", new String[]{"B", "C", "D", "E", "A"}),
        };

        companies = new Company[]{
                new Company("A", new String[]{"1", "2", "3", "4", "5"}),
                new Company("B", new String[]{"1", "3", "2", "5", "4"}),
                new Company("C", new String[]{"4", "2", "5", "1", "3"}),
                new Company("D", new String[]{"5", "3", "1", "4", "2"}),
                new Company("E", new String[]{"2", "3", "5", "4", "1"}),
        };
        companies1 = new Company[]{
                new Company("A", new String[]{"2", "5", "1", "3", "4"}),
                new Company("B", new String[]{"1", "2", "3", "4", "5"}),
                new Company("C", new String[]{"5", "3", "2", "1", "4"}),
                new Company("D", new String[]{"1", "3", "2", "4", "5"}),
                new Company("E", new String[]{"2", "3", "5", "4", "1"}),
        };
        employees1 = new Employee[]{
                new Employee("1", new String[]{"E", "A", "D", "B", "C"}),
                new Employee("2", new String[]{"D", "E", "B", "A", "C"}),
                new Employee("3", new String[]{"D", "B", "C", "E", "A"}),
                new Employee("4", new String[]{"C", "B", "D", "A", "E"}),
                new Employee("5", new String[]{"A", "D", "B", "C", "E"}),
        };
        companies2 = new Company[]{
                new Company("A", new String[]{"5", "2", "1", "4", "3"}),
                new Company("B", new String[]{"1", "2", "4", "3", "5"}),
                new Company("C", new String[]{"3", "5", "2", "1", "4"}),
                new Company("D", new String[]{"1", "3", "2", "5", "4"}),
                new Company("E", new String[]{"1", "4", "5", "3", "2"}),
        };

        employees2 = new Employee[]{
                new Employee("1", new String[]{"E", "A", "D", "B", "C"}),
                new Employee("2", new String[]{"D", "E", "B", "A", "C"}),
                new Employee("3", new String[]{"B", "D", "C", "E", "A"}),
                new Employee("4", new String[]{"C", "B", "D", "A", "E"}),
                new Employee("5", new String[]{"A", "D", "B", "C", "E"}),
        };

        // Test 4
        companies3 = new Company[]{
                new Company("A", new String[]{"4", "1", "2", "5", "3"}),
                new Company("B", new String[]{"5", "1", "2", "3", "4"}),
                new Company("C", new String[]{"4", "5", "1", "2", "3"}),
                new Company("D", new String[]{"2", "1", "4", "3", "5"}),
                new Company("E", new String[]{"1", "5", "3", "4", "2"}),
        };

        employees3 = new Employee[]{
                new Employee("1", new String[]{"B", "E", "C", "D", "A"}),
                new Employee("2", new String[]{"E", "D", "A", "B", "C"}),
                new Employee("3", new String[]{"D", "B", "A", "C", "E"}),
                new Employee("4", new String[]{"C", "A", "B", "E", "D"}),
                new Employee("5", new String[]{"E", "C", "D", "A", "B"}),
        };
    }
}
