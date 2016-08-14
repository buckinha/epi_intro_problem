# Java Algorithms with Python Analysis of Time Complexity

This was a fun "java-refresher" project I coded up to begin blowing the rust off my java coding. It's based off an introductory problem from Elements of Programming Interviews (Aziz, Lee, and Prakash 2015). I wrote the algorithm three ways: the brute force method, a divide-and-conquer recursion approach, and the preferred linear-time method.

I finished by analyzing the running time of each algorithm (in Java) and then applying a custom curve-fitting technique using SciPy to not only show that the time complexities are in the expected form (i.e. O(n^2), O(nlogn), O(n)), but to get estimates of the actual time functions themselves.

The algorithms themselves are in /src/hkb/practice/epi.
* IntroProblem.java: This class contains the three algoritm methods
* IntroProblemProfiler.java: This class runs the speed tests on each algorithm implementation
* IntroProblemTest.java: Testing functions.

The complexity analysis was done with Python using SciPy.optimze.curve_fit. Those files are in the root directory
* ComplexityFunctions.py: Contains the function definitions for the three styles of time complexity:
  * O(n**2)
  * O(nlog(n))
  * O(n)
* ComplexityFunctionFitting.py: Contains the code for fitting the generic complexity functions to the running time data output from the Java code.
* IntroProblemAnalysis.py: A script for running the curve-fit, outputting the results, and printing the visualizations (using matplotlib)
