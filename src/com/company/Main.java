package com.company;

import com.company.classes.Company;
import com.company.classes.Employee;
import com.company.classes.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final Company[] companies = {
            new Company("A", new String[]{"1", "2", "3", "4"}),
            new Company("B", new String[]{"1", "3", "2", "4"}),
            new Company("C", new String[]{"4", "2", "3", "1"}),
            new Company("D", new String[]{"2", "3", "1", "4"}),
    };

    public static final Employee[] employees = {
            new Employee("1", new String[]{"A", "B", "C", "D"}),
            new Employee("2", new String[]{"D", "A", "B", "C"}),
            new Employee("3", new String[]{"C", "B", "A", "D"}),
            new Employee("4", new String[]{"B", "C", "D", "A"}),
    };


    public static void main(String[] args) {

        long time1 = System.currentTimeMillis();
        List<Pair> pairs = process();
        long time2 = System.currentTimeMillis();

        printPairs(pairs);

        System.out.println("Time taken: " + (time2 - time1));
    }

    private static void printPairs(List<Pair> pairs) {
        for (Pair pair : pairs) System.out.println(pair);
    }

    private static List<Pair> process() {
        List<Pair> pairs = new ArrayList<>();
        List<Pair> result = new ArrayList<>();


        for (Company company : companies) {
            String[] preferences = company.getPreferences();
            for (int position = 0; position < preferences.length; position++) {
                String currentPreference = preferences[position];
                pairs.add(new Pair(
                        company,
                        Arrays.stream(employees)
                                .filter(employee -> employee.getName().equals(currentPreference))
                                .findFirst().orElse(null),
                        position
                ));
            }
        }

        // sort the pairings

        pairs.sort((pair1, pair2) -> {
            Integer happiness1 = calculateHappiness(pair1);
            Integer happiness2 = calculateHappiness(pair2);
            return happiness1.compareTo(happiness2);
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
        String[] preferences = employee.getPreferences();
        for (int i = 0; i < preferences.length; i++) {
            if (preferences[i].equals(pair.getCompany().getName())){
                point2 = i;
            }
        }

        return point1 + point2;
    }
}
