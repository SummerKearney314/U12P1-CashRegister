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
	10000, 5000, 2000, 1000, 500, 200, 100, 25, 10, 5
	};
    final static String[] DENOMINATION_NAMES = {
	"$100 Bills", "$50 Bills", "$20 Bills", "$10 Bills", "$5 Bills",
	"Toonies", "Loonies", "Quarters", "Dimes", "Nickels"
	};



    // TODO: IMPLEMENT YOUR METHODS HERE
    
    /**
	*Reads in the list of prices. It then returns a double
	    array in the size of the list, in order to read in
	    the list of prices
    */
    public static void readPriceList ()
    {
    BufferedReader kbInp = new BufferedReader (new InputStreamReader (System.in));

	System.err.println ("How many prices are there?");
	int size = (kbInp.readLine ());
	
    }
}
