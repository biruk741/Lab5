# Lab5
How to Run:
The function that does the actual matching is process()
and it takes an array of companies and their preferences
and an array of employees and their preferences (See bottom 
of main class for examples).

Best to run the printResults method with the same  inputs as
process, as it will print useful information about the
matching.


Testing:
We tested our function on several examples (test #2 is the 
example from the lab write-up).

```java
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
```

In all four test cases the
solution that our algorithm found was satisfactory.

Efficiency:
Due to our algorithm having several nested iterations, we
calculated our algorithm to have an efficiency ϴ(n^3).

__See notes next to code for explanations__

```java
for (Company company : companies) 
        {
            String[] preferences = company.getPreferences();
            for (int position = 0; position < preferences.length; position++)  // Nested for loop (n^2)
            {
                String currentPreference = preferences[position];
                pairs.add(new Pair(
                        company,
                        Arrays.stream(employees)
                                .filter(employee -> employee.nameEquals(currentPreference)) // filter over n elements (n)
                                .findFirst().orElse(null),
                        preferences.length - position
                ));
            }
        }
```
At this point, due to a nested for loop, and a filter 
over n elements (in its worst case)
inside of the nested for loop, the efficiency is n^3.

```java
 pairs.sort((pair1, pair2) -> {
            Integer happiness1 = calculateHappiness(pair1);
            Integer happiness2 = calculateHappiness(pair2);
            counter++;
            return happiness1.compareTo(happiness2) * -1;
        });
```
``sort()`` uses mergesort in addition to insertion sort, the
implementation looks as follows:

So worst case efficiency is n^3.

```java
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
```
`calculateHappiness()` loops over the company preferences and
employee preferences to calculate a happiness score for
each pair.  Since there are n companies, and n employees,
the efficiency is n^2 for both iterations.

```java
for (Pair p1 : pairs) {
            if (result.stream().noneMatch(p ->
                    p.getCompany().equals(p1.getCompany()) ||
                            p.getEmployee().equals(p1.getEmployee())
            )) {
                result.add(p1);
            }
        }
```
This loops over all pairs and finds the first containing 
a unique company.  The worst case efficiency is n^2.