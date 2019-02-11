## Environment:
Java8+

## How to Run:
+ Run main, and it will print the result of one example:  
```
./gradlew clean run
```

+ Run all unit tests:  
```
./gradlew clean test 
```

+ Run a specific test case:  
```
./gradlew clean test --tests "com.lcc.TimeRangeCollectionTest.should_return_empty_if_they_are_same"
```

## Project Dependency:

```
./gradlew dependencies
```

Result:

```
runtimeOnly - Runtime only dependencies for source set 'main'. (n)
No dependencies

testRuntime - Runtime dependencies for source set 'test' (deprecated, use 'testRuntimeOnly ' instead).
+--- org.assertj:assertj-core:3.11.1
\--- junit:junit:4.12
     \--- org.hamcrest:hamcrest-core:1.3
```

## Code Structure:
```
└── src
    ├── main
    │   └── java
    │       ├── App.java //main function, show the result of one example
    │       └── com
    │           └── lcc
    │               ├── TimeRange.java //encapsulate a time range eg. 9:00-9:30
    │               ├── TimeRangeCollection.java //encapsulate a time range list eg.(9:00-9:30, 9:31-9:50)
    │               ├── TimeRangeSetAlgorithm.java //plugable algorithm interface
    │               └── algo
    │                   └── position
    │                       ├── PositionCompareAlgorithm.java //a concrete algorithm which is a default
    │                       └── TimeRangePosition.java
    └── test
        └── java
            └── com
                └── lcc
                    ├── TimeRangeTest.java
                    ├── TimeRangeCollectionTest.java
                    └── algo
                        └── position
                            └── TimeRangePositionTest.java
```


## Class Diagram:

```                                                                                                           
                                                                                                                                               
                                +-----------+                                                    +--------------------------------------------+
  +--------------+   implements |           |            depends                                 |              TimeRangePosition             |
  |  Comparable  <--------------- TimeRange <-----------------------------------+                +--------------------------------------------+
  +--------------+              |           |                                   |                |   abAB, // ab sits to the left side of AB  |
                                +-----------+                                   |                |   aAbB, // ab intersects with AB           |
                                |- start    |                                   |                |   aABb, // ab includes AB                  |
                                |- end      |                                   |                |   AabB, // ab is included by AB            |
                                |- disabled |                                   |                |   AaBb, // ab intersects with AB           |
                                +-----------+                                   |                |   ABab, // ab sits to the right side of AB |
                                      |0..*                                     |                |                                            |
                                      |                                         |                |   ab_AB, //_means equal position           |
                                      |                                         |                |   a_AbB, //a is coinside with A            |
                                      |                                         |                |   a_ABb, //a is coinside with A            |
                                      |aggragates                               |                |   aAb_B, //b is coinside with B            |
                                      |                                         |                |   a_Ab_B, //ab and AB is exactly the same  |
                                      |                                         |                |   AB_ab, //B is coinside with a            |
                                      |                                         |                |   Aab_B; //b is coinside with B            |
                           +----------|----------+                   +----------|----------+     +----------------------^---------------------+
               depends     |                     |    composites     |                     |                            |                      
          +----------------> TimeRangeCollection -------------------->TimeRangeSetAlgorithm|                            |                      
          |                |                     |                   |                     |                            |                      
          |                +---------------------+                   +----------^----------+                            |                      
          |                |     +substract()    |                              |                                       |                      
          |                +---------------------+                              |                                       |                      
          |                                                          implements |                                       |                      
  +-------|-------+                                                             |                                       |                      
  |               |                                                             |                                       |                      
  |      App      |                                               +-------------|-------------+        depends          |                      
  |               |                                               |  PositionCompareAlgorithm |-------------------------+                      
  +---------------+                                               +---------------------------+                                                
                                                                  |      + substract()        |                                                
                                                                  |      - merge()            |                                                
                                                                  |      - shuffle()          |                                                
                                                                  |      - sort()             |                                                
                                                                  +---------------------------+                                                
```

 
## Algorithm Explain:

### Overview:
The Position Compare Algorithm is an ***in-place*** set substracting algroithm. By merging(shuffle, sort, merge) the two TimeRangeCollections, it can do substraction in linear time because it won't have to take care of the handled TimeRange which for sure have no intersection with the upcoming one.

### Position:
It could be proved that there're only 13 combinations for the relatoinal postions of two TimeRanges let's say AB and ab.

```
   ----------+-----|-------+-------|-------+-------                                                     
                   A               B                                                                    
```

Let's make AB not move first so there're only 5 spots(refer above graph, three plus signs, A and B) left for a/b to anchor:  

>When a is fixed at the first spot(to the left of A), then b has 5 combinations.   
>When a is on A, then b has 3.  
>When a is between A and B, then b has 3.   
>When a is on B, b has 1.  
>When a is to the left of B, b has 1. 

Therefore, there're ***13 combinations*** in total. Below is the Enum contains all the cases:

```java
public enum TimeRangePosition {
  abAB, // ab sits to the left side of AB
  aAbB, // ab intersects with AB, and the intersection is Ab
  aABb, // ab includes AB
  AabB, // ab is included by AB
  AaBb, // ab intersects with AB, and the intersection is aB
  ABab, // ab sits to the right side of AB

  ab_AB, //_means equal position
  a_AbB, //a is coinside with A
  a_ABb, //a is coinside with A
  aAb_B, //b is coinside with B
  a_Ab_B, //ab and AB is exactly the same
  AB_ab, //B is coinside with a
  Aab_B; //b is coinside with B
}
```

### Partition:
The partition occurs when ab is inside AB, so when AB-ab, it will   become Aa and bB which creates another new TimeRange.

### Time Complexity Analysis: 
Let's suppose include TimeRangeCollection has m TimeRanges, and exclude TimeRangeCollection has n TimeRanges. Then the time complexity would be O( mlogm + nlogn + m + n )

### Space Complexity Analysis: 
Because it's an in-place algorithm, it shouldn't take extra space unless partition happened. Worst case is every ab is inside AB, so it will cost a additional O(m) space.