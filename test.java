import java.util.Scanner;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		
		System.out.println("Please enter the numbers you want to place into the pyramid.\nYour input will be taken until you press \".\".");
		
		Scanner sc=new Scanner(System.in);
		int rowCounter=-1;
		int counter=0;
		ArrayList<ArrayList<Integer>> numbersList=new ArrayList<ArrayList<Integer>>();
		
		//Retrieving the input and organizing them in a 2D array format for easy processing
		while(true)
		{
			String input=sc.next();
			
			if(input.equals("."))
			{
				rowCounter++;
				break;
			}
			else
			{
				if(rowCounter+1==counter)
				{
					numbersList.add(new ArrayList<Integer>());
					counter=0;
					
					rowCounter++;
				}
				
				numbersList.get(rowCounter).add(Integer.parseInt(input));
				counter++;
			}
		}
		
		//The maximum finding process
		System.out.println("The maximum sum is: "+findMaxSum(numbersList));
	}
	
	//A method to calculate the maximum summation of a orthagonal number series ignoring prime numbers
	//This method utilizes dynamic programming, calculates the possible max summation for each cell if they were to be visited. These are stored in sumTable
	//Tracks down if there is no paths available to be visited after a certain row and marks it as endRow
	//The entries belong to the endRow in sumTable are sorted and the biggest one is returned as the result.
	public static Integer findMaxSum(ArrayList<ArrayList<Integer>> list)
	{
		if(list.size()==0)
			return 0;
		
		ArrayList<ArrayList<Integer>> sumTable=new ArrayList<ArrayList<Integer>>();
		int primeCounter=0;
		int endRow=list.size()-1;
		int j;
		
		sumTable.add(new ArrayList<Integer>());
		sumTable.get(0).add(list.get(0).get(0));
		
		for(int i=1; i<list.size(); ++i)
		{
			sumTable.add(new ArrayList<Integer>());
			for(j=0; j<i+1; ++j)
			{
				int prevSum1;
				int prevSum2;
				
				if(j==i)
					prevSum1=-2;
				else
					prevSum1=sumTable.get(i-1).get(j);
				
				if(j==0)
					prevSum2=-2;
				else
					prevSum2=sumTable.get(i-1).get(j-1);
				
				int sum=calculateSum(prevSum1, prevSum2, list.get(i).get(j));
				sumTable.get(i).add(sum);
				if(sum==-1)
					primeCounter++;
			}
			
			if(primeCounter==j)
			{
				endRow=i;
				break;
			}
			primeCounter=0;
		}
		
		sumTable.get(endRow).sort(null);
		int result=sumTable.get(endRow).get(sumTable.get(endRow).size()-1);
	
		return result;
	}
	
	//A method to calculate the maximum sum of a cell that is being visited
	//If the cell has a prime number the sum becomes -1 because a prime number cell cannot be visited
	//If the predecessors of the cell are both prime, the sum becomes -1 because the cell does not have a path that leads to it
	//If the cell can indeed be visited, the sum becomes cell number + max of it's predecessor number
	public static Integer calculateSum(Integer choice1, Integer choice2, Integer currentNumber)
	{
		if((isPrime(currentNumber)) || (choice1==-1 && choice2==-1))
			return -1;
		else if(Math.max(choice1, choice2)==choice2 && choice2!=-1 && choice2!=-2)
			return choice2+currentNumber;
		else if(Math.max(choice1, choice2)==choice1 && choice1!=-1 && choice1!=-2)
			return choice1+currentNumber;
		
		return -1;
	}
	
	//A method to find if a number is a prime number or not.
	//Returns true if prime, false if not prime.
	public static boolean isPrime(Integer number)
	{
		if(number<=1)
			return false;
		else if(number==2)
			return true;
		for(int i=2; i<number; ++i)
			if(number%i==0)
				return false;
		
		return true;
	}
}
