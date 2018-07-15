
import java.util.Arrays;
/**
 * @author Laura Marlin
 * Data Structures
 * @version 9/16/17
 * This class represents a coin bank for pennies, nickels, dimes and quarters. 
 * Users can insert and remove coins, seeing the total amount within the bank and the amount they remove.
 */


public class Coinbank {


	// Denominations
	public static final int PENNY_VALUE = 1;
	public static final int NICKEL_VALUE = 5;
	public static final int DIME_VALUE = 10;
	public static final int QUARTER_VALUE = 25;


	private final int PENNY= 0;
	private final int NICKEL = 1;
	private final int DIME= 2;
	private final int QUARTER = 3;

	private final int COINTYPES = 4;

	
	private int[] holder;


	public Coinbank() {
		holder= new int[] {0,0,0,0};


	}




	/**
	 * getter 
	 * @param coinType denomination of coin to get. Valid denominations are 1,5,10,25
	 * @return number of coins that bank is holding of that type, or -1 if denomination not valid
	 */
	public int get(int coinType){
		if (coinType==PENNY_VALUE) {
			return holder[PENNY];
		}
		else if (coinType== NICKEL_VALUE) {
			return holder[NICKEL];
		}
		else if (coinType== DIME_VALUE) {
			return holder[DIME];
		}
		else if (coinType== QUARTER_VALUE) {
			return holder[QUARTER];
		}
		else {
			return -1;
		}
	}



	/** 
	 * setter
	 * @param coinType denomination of coin to set
	 * @param numCoins number of coins
	 */
	private void set(int coinType, int numCoins) {
		if (coinType==PENNY_VALUE) {
			holder[PENNY]= numCoins;
		}
		else if (coinType== NICKEL_VALUE) {
			holder[NICKEL]= numCoins;
		}
		else if (coinType== DIME_VALUE) {
			holder[DIME]= numCoins;
		}
		else if (coinType== QUARTER_VALUE) {
			holder[QUARTER]= numCoins;
		}
		else {
			return;
		}

	}


	/**
	 * Return true if given coin can be held by this bank.  Else false.
	 * @param coin penny, nickel, dime, or quarter is bankable.  All others are not.
	 * @return true if bank can hold this coin, else false
	 */
	private boolean isBankable(int coin){
		switch (coin) {
			case PENNY_VALUE: case NICKEL_VALUE:
			case DIME_VALUE: case QUARTER_VALUE:
				return true;
			default:
				return false;
		}
	}


	/**
	 * insert valid coin into bank.  Returns true if deposit
	 * successful (i.e. coin was penny, nickel, dime, or quarter).
	 * Returns false if coin not recognized
	 *
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @return true if deposit successful, else false
	 */
	// how reusable is this?
	public boolean insert(int coinType){
		if (!isBankable(coinType)) {
			return false;
		}
		else {
			set(coinType, get(coinType)+1);
			return true;
		}
	}

	
	/**
	 * Checks to see whether a valid number of coins is being requested
	 * @param requestedCoins int representing the number of coins requested
	 * @return True if it is a positive number, else false
	 */
	
	private boolean isValidRequestedCoin(int requestedCoins) {
		if (requestedCoins>= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Determines the number of coins removed from the bank
	 * @param coinType 5,10,1,25 for each of the coin values
	 * @param requestedCoins the number of coins requested in an int
	 * @return the number of coins that is removed from the bank
	 */
	private int getNumRemoved(int coinType, int requestedCoins) {
		int numRemoved=0;
		if (get(coinType)>= requestedCoins) { 
			numRemoved= requestedCoins;
			}
		else if (get(coinType)< requestedCoins) { 
			numRemoved= get(coinType);
			}
		return numRemoved;		
	}
	
	
	/**
	 * returns the requested number of the requested coin type, if possible.
	 * Does nothing if the coin type is invalid.  If bank holds
	 * fewer coins than is requested, then all of the coins of that
	 * type will be returned.
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @param requestedCoins number of coins to be removed
	 * @return number of coins that are actually removed
	 */
	public int remove(int coinType, int requestedCoins) {
		if (isBankable(coinType) & isValidRequestedCoin(requestedCoins)) {
			int numRemoved= getNumRemoved(coinType, requestedCoins);
			set(coinType, numLeft(requestedCoins, get(coinType)));
			return numRemoved; 
		}		
		else {
			return 0; 
			}

	}


	/**
	 * returns number of coins remaining after removing the
	 * requested amount.  Returns zero if requested amount > what we have
	 * @param numWant number of coins to be removed
	 * @param numHave number of coins you have
	 * @return number of coins left after removal
	 */
	private int numLeft(int numWant, int numHave){
		return Math.max(0, numHave-numWant);
	}

	/**
	 * Returns bank as a printable string
	 */
	public String toString() {
		double total = (get(PENNY_VALUE) * PENNY_VALUE +
				get(NICKEL_VALUE) * NICKEL_VALUE +
				get(DIME_VALUE) * DIME_VALUE +
				get(QUARTER_VALUE) * QUARTER_VALUE) / 100.0;

		String toReturn = "The bank currently holds $" + total + " consisting of \n";
		toReturn+=get(PENNY_VALUE) + " pennies\n";
		toReturn+=get(NICKEL_VALUE) + " nickels\n";
		toReturn+=get(DIME_VALUE) + " dimes\n";
		toReturn+=get(QUARTER_VALUE) + " quarters\n";
		return toReturn;
	}
	
	

}