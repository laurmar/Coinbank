package proj1;  // Don't change the package name.  Gradescope expects this.

/**
 *
 * This class represents a mid 20th century coinbank. Users will be able to create coinbanks along with input
 * and remove coins essentially mimicking a functioning coinbank.
 * @author Laura Marlin
 * @version September 16, 2018
 *
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 *
 */
public class Coinbank {

	// Denominations
	public static final int PENNY_VALUE = 1;
	public static final int NICKEL_VALUE = 5;
	public static final int DIME_VALUE = 10;
	public static final int QUARTER_VALUE = 25;


	// give meaningful names to holder array indices
	private final int PENNY = 0;
	private final int NICKEL = 1;
	private final int DIME = 2;
	private final int QUARTER = 3;


	// how many types of coins does the bank hold?
	private final int COINTYPES = 4;


	private int[] holder;


	//CONSTRUCTOR SECTION
	//------------------------------------------------------------------------------------------------------------------


	/**
	 * Default constructor
	 */
	public Coinbank() {
		holder = new int[COINTYPES];
	}



	// GETTERS AND SETTERS SECTION
	//------------------------------------------------------------------------------------------------------------------

	/**
	 * Gets the holder
	 * @return the instance variable holder
	 */
	private int[] getHolder(){
		return holder;
	}

	/**
	 * Set the holder to a new value
	 * @param newHolder a new holder to replace the current holder with
	 */
	private void setHolder(int[] newHolder){
		holder= newHolder;

	}



	/**
	 * This function uses coin values to discover the quantity of coins that are contained of
	 * a certain coin type.
	 *
	 *NOTE: This function relies on the holder being in the form of an array of length 4.Also -1 is used
	 * to indicate when an invalid denomination has been inputted
	 * @param coinType denomination of coin to get. Valid denominations are 1,5,10,25
	 * @return number of coins that bank is holding of that type, or -1 if denomination not valid
	 */
	public int get(int coinType) {
		int location= getCoinbankSpot(coinType);
		if(location == -1) {
			return -1;

		}
		return holder[location];
	}


	/**
	 * This function navigates to the correct location given a coin value
	 *
	 * @param coinType: this is denominations of coins that can be inputted. Valid denominations are 1,5,10,25
	 * @return the spot that the coins of that type are contained within or -1 if the inputted type is invalid
	 */
	private int getCoinbankSpot(int coinType) {
		if(isBankable(coinType)) {
			if (coinType == PENNY_VALUE) {
				return PENNY;
			} else if (coinType == NICKEL_VALUE) {
				return NICKEL;
			} else if (coinType == DIME_VALUE) {
				return DIME;
			} else {
				return QUARTER;
			}
		}
		else{
			return -1;
		}

	}





	/**
	 * This function sets the quantity of coins of a selected cointype to a new value of the users
	 * choosing.
	 *
	 * NOTE: This function relies on the holder being in the form of an array of length 4. Also -1 is used
	 * to indicate when an invalid denomination has been inputted
	 * @param coinType denomination of coin to set
	 * @param numCoins number of coins
	 */
	private void set(int coinType, int numCoins) {
		if (numCoins >= 0) {
			int location= getCoinbankSpot(coinType);
			if(location!=-1) {
				holder[location] = numCoins;
			}
		}
	}

	// GENERAL METHODS
	//------------------------------------------------------------------------------------------------------------------


	/**
	 * Return true if given coin can be held by this bank.  Else false.
	 *
	 * @param coin penny, nickel, dime, or quarter is bankable.  All others are not.
	 * @return true if bank can hold this coin, else false
	 */
	private boolean isBankable(int coin) {
		switch (coin) {
			case PENNY_VALUE:
			case NICKEL_VALUE:
			case DIME_VALUE:
			case QUARTER_VALUE:
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
	public boolean insert(int coinType) {
		if (!isBankable(coinType)) {
			return false;
		} else {
			set(coinType, get(coinType) + 1);
			return true;
		}
	}



	/**
	 * returns the requested number of the requested coin type, if possible.
	 * Does nothing if the coin type is invalid.  If bank holds
	 * fewer coins than is requested, then all of the coins of that
	 * type will be returned.
	 *
	 * Note: coinsWeHave returns -1 when the coinType is invalid
	 * @param coinType       either 1, 5, 10, or 25 to be valid
	 * @param requestedCoins number of coins to be removed
	 * @return number of coins that are actually removed
	 */
	public int remove(int coinType, int requestedCoins) {
		int removedCoins = 0;
		int coinsWeHave;
		int coinsLeft;
		coinsWeHave = get(coinType);
		if (coinsWeHave>= 0 && requestedCoins >= 0) {
			coinsLeft = numLeft(requestedCoins, coinsWeHave);
			set(coinType, coinsLeft);
			removedCoins = coinsWeHave - coinsLeft;
		}
		return removedCoins;
	}




	/**
	 * returns number of coins remaining after removing the
	 * requested amount.  Returns zero if requested amount > what we have
	 *
	 * @param numWant number of coins to be removed
	 * @param numHave number of coins you have
	 * @return number of coins left after removal
	 */
	private int numLeft(int numWant, int numHave) {

		return Math.max(0, numHave - numWant);
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
		toReturn += get(PENNY_VALUE) + " pennies\n";
		toReturn += get(NICKEL_VALUE) + " nickels\n";
		toReturn += get(DIME_VALUE) + " dimes\n";
		toReturn += get(QUARTER_VALUE) + " quarters\n";
		return toReturn;
	}


}