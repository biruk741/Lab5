# Lab5: Matching Problem
__How to Run:__
The function that does the actual matching is process()
and it takes an array of companies and their preferences
and an array of employees and their preferences (See bottom 
of main class for examples).

It is best to run the process method using printResults, as it will print useful information about the
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
solution that our algorithm found was satisfactory. As shown when running the program:
```
Pair{company=A, employee=1}
Pair{company=E, employee=2}
Pair{company=C, employee=4}
Pair{company=B, employee=3}
Pair{company=D, employee=5}
Results of test #1:
Time taken: 4
Made 1034 comparisons.
Solution is satisfactory.
// more test cases that are also satisfactory...
```
# How it works

The algorithm works by first taking out all the company->employee pairs in the companies array, assigning them a score based on their position in the preferences and then putting them into a list. After doing this
, it then sorts the list according to a "happiness score". The happiness score is calculated by going through the employees list, checking the position of the pair in the employees list, assigning it a score, and then adding up the two scores to come up with a cumulative score that we call the happiness score. The happiness score represents how happy both parties are collectively. After sorting the pairs according to their happiness score from highest to lowest, it then finds the first occurence of each company/employee and then adds them to a result array. Since the array has been sorted according to the overall happiness score, the first occurences of each company/employee are the most satisfactory pairings. 
The algorithm then stops when it goes through the array of all sorted pairings and adds each first occurence to the result array.
# Efficiency:
Due to our algorithm having several nested iterations, we
calculated our algorithm to have an efficiency ϴ(n^3). Here is how we calculated this result:

__See comments next to code for explanations__

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
inside of the nested for loop, the efficiency is n^3. Moving on to the rest of the algorithm, we have:

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

```java
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }
        // continue to mergesort...
```
In the worst case, since the insertion sort algorithm runs our `calculateHappiness()` methods within the `compare()` function, it runs the `calculateHapiness()` functions n times.


Calculate happiness has two iterations that run n times. This means that the insertion sorting efficiency is `n x n^2` = `n^3`

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
It then goes on to do the rest of the sorting at `nlog(n)` efficiency.
At this point, the efficiency is `n^3 + n^3 + nlogn`.

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
This loops over all pairs (n^2 of them) and finds the first containing 
a unique company.  In the worst case, it goes through the entire companies array, meaning n times.
The efficiency for this loop is n^3.

So the sum of all the efficiencies is:
```
n^3
+
n^3
+
nlogn 
+
n^3

= ϴ(n^3)
```

