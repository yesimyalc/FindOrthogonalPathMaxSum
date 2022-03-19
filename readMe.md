# Problem
You will have an orthogonal triangle input from a file and you need to find the maximum sum of the numbers according to given rules below;

1. You will start from the top and move downwards to an adjacent number as in below.
2. You are only allowed to walk downwards and diagonally.
3. You can only walk over NON PRIME NUMBERS.
4. You have to reach at the end of the pyramid as much as possible.
5. You have to treat your input as pyramid.

According to above rules the maximum sum of the numbers from top to bottom in below example is 24.

      *1
     *8 4
    2 *6 9
   8 5 *9 3

As you can see this has several paths that fits the rule of NOT PRIME NUMBERS; 1>8>6>9, 1>4>6>9, 1>4>9>9
1 + 8 + 6 + 9 = 24.  As you see 1, 8, 6, 9 are all NOT PRIME NUMBERS and walking over these yields the maximum sum.

# My Notes
-All of my methods have a summary mentioned on top of them as comments  
-The algorithm is not designed for negative numbers.  

# My Problem Solving Approach
I firstly take the numbers from the user. The numbers can be entered individually or as a whole list.  
I stop taking input after the user enters "."  
I organize the input in a 2D array format.  
I have decided to implement a dynamic programming algorithm for the actual solution part.  
In this algorithm, I calculate the maximum summation of each cell(the individual numbers) if they were to be visited.  
->If i is the row number,  
->If j is the column number,  
->If T(i,j)=The summation result for the cell in i'th row and j'th column,  
->It means T(i,j)=max(T(i-1,j) , T(i-1,j-1)) + cell(i,j)  
During this calculation process, the prime number cases and matrix out of bounds are handled properly.  
I store all these summations and find the result using this table.  
