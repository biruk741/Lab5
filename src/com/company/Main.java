package com.company;

import com.company.classes.Company;
import com.company.classes.Employee;
import com.company.classes.Pair;

import java.util.List;

public class Main {

    public static final Company[] companies = {
            new Company("A", new String[]{"1","2","3","4"}),
            new Company("B", new String[]{"1","3","2","4"}),
            new Company("C", new String[]{"4","2","3","1"}),
            new Company("D", new String[]{"2","3","1","4"}),
    };

    public static final Employee[] employees = {
            new Employee("1", new String[]{"A","B","C","D"}),
            new Employee("2", new String[]{"D","A","B","C"}),
            new Employee("3", new String[]{"C","B","A","D"}),
            new Employee("4", new String[]{"B","C","D","A"}),
    };


    public static void main(String[] args) {

        long time1 = System.currentTimeMillis();
        List<Pair> pairs = process();
        long time2 = System.currentTimeMillis();

        printPairs(pairs);

        System.out.println("Time taken: " + (time2 - time1));
    }

    private static void printPairs(List<Pair> pairs){
        for (Pair pair : pairs) System.out.println(pair);
    }

    private static List<Pair> process(){

    }

    private static int calculateHappiness(Pair pair){

    }
}
