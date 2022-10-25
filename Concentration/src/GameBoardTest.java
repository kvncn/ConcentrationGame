import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

// Since most things are randomized and should not be hardcoded,
// the test only tests cards at play
public class GameBoardTest {

	@Test
	public void testCardsAtPlay() {
		
		GameBoard gbPlays = new GameBoard(1);
		
		int size = gbPlays.size();
		
		gbPlays.makePlay();
		size -=2;
		assertEquals("Play not made", size, gbPlays.cardsAtPlay());
		gbPlays.makePlay();
		size -=2;
		assertEquals("Play not made", size, gbPlays.cardsAtPlay());
		size -=2;
		gbPlays.makePlay();
		assertEquals("Play not made", size, gbPlays.cardsAtPlay());
		gbPlays.makePlay();
		size -=2;
		assertEquals("Play not made", size, gbPlays.cardsAtPlay());
	}
	
	@Test
	public void testGrid1() {
		GameBoard gb = new GameBoard(1);
		
		int size = gb.size();
		
		List<Card> cL = gb.getCardList();
		
		for (int i = 0; i < size; i++) {
			Card curr = cL.get(i);
			Card comp = gb.getCard(i);
			assertEquals("List and Board don't match", comp, curr);
		}
		
	}
	
	@Test
	public void testGrid2() {
		GameBoard gb = new GameBoard(1);
		
		int size = gb.size();
		
		List<Card> cL = gb.getCardList();
		
		for (int i = 0; i < size; i++) {
			
		}
		
	}
}
