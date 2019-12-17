/*
Name: CashRegister
Purpose: Reads in a list of prices, calculates the tax and the total, reads
  in how much is being paid and prints out the required change.
Author: Summer Kearney
Date:
*/

import java.io.*;

public class CashRegister
{
    public static void main (String[] args) throws IOException
    {

	BufferedReader kbInp = new BufferedReader (new InputStreamReader (System.in));


	// Read in the prices
	double[] prices = readPriceList ();

	// Total the prices
	double subtotal = sum (prices);
	System.out.println ("SUBTOTAL: $" + subtotal);

	// Calculate the sales tax (13%)
	final double SALES_TAX_RATE = 0.13;
	double tax = calculateTax (subtotal, SALES_TAX_RATE);
	System.out.println ("Tax: $" + tax);

	// Calculate the total
	double total = subtotal + tax;
	System.out.println ("TOTAL: $" + total);

	// Round to the nearest nickel
	double cashTotal = roundToNickel (total);
	System.out.println ("CASH OWED: $" + cashTotal);

	// Read in how much cash has been paid
	double cashPaid = readCashAmount ();
	if (cashPaid < cashTotal)
	{
	    System.err.println ("Not enough cash was paid!");
	    return;
	}

	// Determine the change that is needed
	int[] change = makeChange (cashTotal, cashPaid);

	// Print out the change needed
	System.out.println ("CHANGE:");
	printChange (change);
    }


    // These variable can be accessed in any method.  Your are encouraged to
    // make use of them.
    final static int[] DENOMINATION_CENTS = {
	10000, 5000, 2000, 1000, 500, 200, 100, 25, 10, 5};
    final static String[] DENOMINATION_NAMES = {
	"$100 Bills", "$50 Bills", "$20 Bills", "$10 Bills", "$5 Bills",
	"Toonies", "Loonies", "Quarters", "Dimes", "Nickels"};



    /**
	*Reads in the list of prices. It then returns a double
	    array in the size of the list, in order to read in
	    the list of prices
    */
    public static double[] readPriceList () throws IOException
    {
	BufferedReader kbInp = new BufferedReader (new InputStreamReader (System.in));

	// Read in a character
	System.err.print ("Enter the number of prices: ");
	int listSize = Integer.parseInt (kbInp.readLine ());

	System.err.println ("Enter the prices:");

	double[] pricesArray = new double [listSize];

	for (int index1 = 0 ; index1 < listSize ; index1++)
	{
	    pricesArray [index1] = Double.parseDouble (kbInp.readLine ());
	}
	return (pricesArray);
    }


    /**
	    * Adds up all of the total prices previusly entered by the user.
	     Then returns the total as a double
	*/
    public static double sum (double[] prices)
    {
	double sum = 0;
	for (int index1 = 0 ; index1 < prices.length  ; index1++)
	{
	    sum = sum + prices [index1];
	}
	return sum;
    }


    /**
	* Calculates the tax of the subtotal by multiplying the subtotal
	  by 0.13. Then returns it as a double
    */
    public static double calculateTax (double subtotal, double SALES_TAX_RATE)
    {
	return (subtotal * SALES_TAX_RATE);

    }

    /**
	*Rounds the amount up to the nearest fith cent, as in Canada
	the penny is no longer made, making the amount round
    */
    public static double roundToNickel (double total)
    {
	// Splits space separated list.
	//System.out.println (Math.round(total *100)/100.0);
	double cashRounded = Math.round (total * 20) / 20.0;
	return cashRounded;

    }




   /**
	    *Asks the user how much cash was paid. Then returned.
    */
    public static double readCashAmount () throws IOException
    {
	BufferedReader kbInp = new BufferedReader (new InputStreamReader (System.in));
	System.err.print ("How much cash was paid? ");
	return (Integer.parseInt (kbInp.readLine ()));
    }


    /**
	*akes in two amounts: the total cost of the
	items and the amount paid. The method returns 
	an array of 10 integers representing how many 
	of each money denomination is needed for change.  
    */
    public static int[] makeChange (double totalCost, double cashPaid)
    {
	
	int centsPaid = (int) cashPaid * 100;
    
	double centsTotal =  totalCost * 100;
	int change = (int) centsPaid - (int) centsTotal;

	    
	int[] changeArray = new int [10];

	for (int index = 0 ; index < DENOMINATION_CENTS.length ; index++)
	{
	    
	    changeArray [index] = change / DENOMINATION_CENTS [index];


	    change = change - (changeArray [index] * DENOMINATION_CENTS [index]);

	}

	return (changeArray);


    }


    /**
	    *takes in an array of 10 integers as is 
	    output from the above method and prints the change as in the example below. 
	    Only denominations that are needed should be printed.  No value is returned.
	*/
    public static void printChange (int [] change)
    {
	for (int index = 0 ; index < DENOMINATION_CENTS.length ; index++)
	{
	    if (change [index] != 0)
	    {
	    System.out.println (" - " + DENOMINATION_NAMES [index] + ": " + change [index]);
	    }
	}
    }
}


