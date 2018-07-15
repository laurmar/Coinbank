/**
 * JUnit test class.  Use these tests as models for your own.
 */
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class CoinbankTest {
	
	@Rule // a test will fail if it takes longer than 1/10 of a second to run
 	public Timeout timeout = Timeout.millis(100); 

	/**
	 * Sets up a bank with the given coins
	 * @param pennies number of pennies you want
	 * @param nickels number of nickels you want
	 * @param dimes number of dimes you want
	 * @param quarters number of quarters you want
	 * @return the Coinbank filled with the requested coins of each type
	 */
	private Coinbank makeBank(int pennies, int nickels, int dimes, int quarters) {
		Coinbank c = new Coinbank();
		int[] money = new int[]{pennies, nickels, dimes, quarters};
		int[] denom = new int[]{1,5,10,25};
		for (int index=0; index<money.length; index++) {
			int numCoins = money[index];
			for (int coin=0; coin<numCoins; coin++) {
				c.insert(denom[index]);
			}
		}
		return c;
	}

	@Test // bank should be empty upon construction
	public void testConstruct() {
		Coinbank emptyDefault = new Coinbank();
		assertEquals(0, emptyDefault.get(1));
		assertEquals(0, emptyDefault.get(5));
		assertEquals(0, emptyDefault.get(10));
		assertEquals(0, emptyDefault.get(25));
	}
	
	//---------------------------------------------------------------------
	// inserting tests
	
	@Test // inserting nickel should return true & one nickel should be in bank
	public void testInsertNickel_return()
	{
		Coinbank c = new Coinbank();
		assertTrue(c.insert(5));
		assertEquals(1,c.get(5));
	}
	//----------------------------
	// my inserting tests
	
	@Test // testing inserting something invalid
	public void testInsertInvalid() {
		Coinbank c = new Coinbank();
		assertFalse(c.insert(7));
	}
	
	@Test // testing inserting something twice
	public void testInsertTwice() {
		Coinbank c = new Coinbank();
		assertTrue(c.insert(5));
		assertTrue(c.insert(5));
		assertEquals(2,c.get(5));
		
	}
	
	
	
	@Test // testing inserting something negative and invalid
	public void testInsertNegativeInvalid() {
		Coinbank c = new Coinbank();
		assertFalse(c.insert(-5));
	}
	
	@Test // testing inserting one valid and one invalid
	public void testInsertValidAndInvalid() {
		Coinbank c = new Coinbank();
		assertFalse(c.insert(-5));
		assertTrue(c.insert(5));
		assertEquals(1,c.get(5));
	}
	
	

	//----------------------------------------------------------------------------------
	// getter tests
	@Test // getter should return correct values
	public void testGet()
	{
		Coinbank c = makeBank(0,2,15,1);
		assertEquals(0,c.get(1));
		assertEquals(2,c.get(5));
		assertEquals(15,c.get(10));
		assertEquals(1,c.get(25));
	}
	
	@Test // getter should not alter the bank
	public void testGet_contents()
	{
		Coinbank c = makeBank(0,2,15,1);
		c.get(1);
		c.get(5);
		c.get(10);
		c.get(25);
		String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
		assertEquals(expected,c.toString());
	}
	
	//-----------------
	// my getter tests
	
	@Test // getter should return correct values even with a negative entry for dimes and Nickels
	public void testGetNegatives()
	{
		Coinbank c = makeBank(0,-2,-15,1);
		assertEquals(0,c.get(1));
		assertEquals(0,c.get(5));
		assertEquals(0,c.get(10));
		assertEquals(1,c.get(25));
	}
	
	//--------------------------------------------------------------------
	// removing tests
	
	@Test // test of remove
	public void testRemove_justEnough()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(5,c.remove(25,5));
		String expected = "The bank currently holds $0.39 consisting of \n4 pennies\n1 nickels\n3 dimes\n0 quarters\n";
		assertEquals(expected,c.toString());
	}
	
	@Test // remove should not do anything if a 3-cent coin is requested
	public void testRemove_invalidCoin()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(0,c.remove(3,1));
	}
	//-------------------
	// my removing tests
	@Test // test of removing more quarters than are in the bank looking at final total
	public void testRemove_tooMuchTotal()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(5,c.remove(25,7));
		String expected = "The bank currently holds $0.39 consisting of \n4 pennies\n1 nickels\n3 dimes\n0 quarters\n";
		assertEquals(0,c.get(25));
		assertEquals(expected,c.toString());
	}
	
	
	@Test // test of removing more quarters than are in the bank specifically counting quarters
	public void testRemove_tooMuchCount()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(5,c.remove(25,7));
		assertEquals(0,c.get(25));
	}
	
	@Test // test of removing more quarters than are in the bank over two pulls
	public void testRemove_tooMuchTwice()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(2,c.remove(25,2));
		assertEquals(3,c.remove(25,7));
		assertEquals(0,c.get(25));
	}
	
	
	@Test // test of removing one valid and one invalid
	public void testRemove_ValidAndInvalid()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(0,c.remove(25,-2));
		assertEquals(3,c.remove(25,3));
		assertEquals(2,c.get(25));
	}
	
	
	@Test // test of not removing all the quarters
	public void testRemove_less()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(4,c.remove(25,4));
		String expected = "The bank currently holds $0.64 consisting of \n4 pennies\n1 nickels\n3 dimes\n1 quarters\n";
		assertEquals(expected,c.toString());
	}
	
	@Test // test of not removing negative quarters
	public void testRemove_negative()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(0,c.remove(25,-4));
		String expected = "The bank currently holds $1.64 consisting of \n4 pennies\n1 nickels\n3 dimes\n5 quarters\n";
		assertEquals(expected,c.toString());
	}
	// TESTS STILL TO WRITE
		// toString test
}
