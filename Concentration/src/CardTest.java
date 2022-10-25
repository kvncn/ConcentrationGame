import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {
	
	@Test
	public void testSetTwin1() {
		Card testSet = new Card("TestSet");
		Card testSetPair = new Card("TestSetPair");
		Card testSetRePair = new Card("NotPair");
		
		testSet.setTwin(testSetPair);
		assertEquals("Twin not set correctly", true, testSet.isTwin(testSetPair));
		
		testSet.setTwin(testSetRePair);
		assertEquals("Twin not updated correctly", true, testSet.isTwin(testSetRePair));
	}

	@Test
	public void testIsTwin1() {
		Card test1 = new Card("Test1");
		Card test1Pair = new Card("Test1Pair");
		// name shouldn't matter, what if the GUI is 
		// different and we do something else for image?
		test1.setTwin(test1Pair);
		assertEquals("Card should not be its own twin", false, test1.isTwin(test1));
		assertEquals("Both cards should be a pair.", true, test1.isTwin(test1Pair));
	}
	
	@Test
	public void testIsTwin2() {
		Card test2 = new Card("Test2");
		Card test2NotPair = new Card("Test2NotPair");
		assertEquals("Both cards should be a pair.", false, test2.isTwin(test2NotPair));
	}

	@Test
	public void testFlips() {
		Card flip = new Card("flip");
		
		flip.flip();
		flip.flip();
		flip.unflip();
		assertEquals("Card should not be flipped", false, flip.isFlipped());
		flip.unflip();
		flip.unflip();
		flip.flip();
		assertEquals("Card should be flipped", true, flip.isFlipped());
	}

}
