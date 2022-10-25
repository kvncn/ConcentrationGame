import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class GamePlayTest {

	@Test
	public void testSinglePlayer() {
		GameBoard cards = new GameBoard(1);
		
		Queue<Player> players = new LinkedList<Player>();
		
		players.add(new Player("Kevin"));
		
		GamePlay game = new GamePlay(1, players, cards);
		
		// We just make the player find a card hard-coded to test
		// if they can make a successful and unsuccessful play
		Card test = cards.getCard(0);
		int matchIdx = -1;
		int unmatchIdx = -1;
		
		List<Card> cardList = cards.getCardList();
		// find index of matching card
		for (int i = 0; i < cardList.size(); i++) {
			Card curr = cardList.get(i);
			if (curr.isTwin(test)) {
				matchIdx = i;
			} else {
				unmatchIdx = i;
			}
		}
		
		// this is how the game runs with the gui, so we 
		// always call select and check even if this 
		// was the first card selection in a turn
		game.selectCard(0);
		game.checkPair();
		
		game.selectCard(matchIdx);
		game.checkPair();
		
		assertEquals("Did not find move to be successful", players.peek().getScore(), 10);
		
		// Now we make a wrong selection to make sure it doesn't add anything to the player
		game.selectCard(0);
		game.checkPair();
		
		// matchIDx will never be zero, so we can make sure this will not hit a out of
		// bounds error, also, even if match idx is 1, it should still work as a card
		// is not its pair
		game.selectCard(matchIdx - 1);
		game.checkPair();
		
		assertEquals("Wrongly accounted unsuccessful move", players.peek().getScore(), 10);
		}
	
	@Test
	public void testMultiPlayerChange() {
		GameBoard cards = new GameBoard(1);
		
		Queue<Player> players = new LinkedList<Player>();
		
		Player kevin = new Player("Kevin");
		Player david = new Player("David");
		players.add(kevin);
		players.add(david);
		
		GamePlay game = new GamePlay(1, players, cards);
		
		// We just make the player find a card hard-coded to test
		// if they can make a successful and unsuccessful play
		Card test = cards.getCard(0);
		int matchIdx = -1;
		int unmatchIdx = -1;
		
		List<Card> cardList = cards.getCardList();
		// find index of matching card
		for (int i = 0; i < cardList.size(); i++) {
			Card curr = cardList.get(i);
			if (curr.isTwin(test)) {
				matchIdx = i;
			} else {
				unmatchIdx = i;
			}
		}
		
		// Now we make a wrong selection to make sure it doesn't add anything to the player
		game.selectCard(0);
		game.checkPair();
		
		// card should not match with unmatching card
		game.selectCard(unmatchIdx);
		game.checkPair();
		
		assertEquals("Wrongly accounted unsuccessful move", players.peek().getScore(), 0);
		
		// click under grid (GUI thing so we can actually reset after 
		// wrong move
		game.selectCard(100000); 
		
		// this is how the game runs with the gui, so we 
		// always call select and check even if this 
		// was the first card selection in a turn
		game.selectCard(0);
		game.checkPair();
			
		game.selectCard(matchIdx);
		game.checkPair();
		
		assertEquals("Player should have changed after unsuccessful move", 
				david.getScore(), 10);
		}
	
	@Test
	public void testEndGame() {
		GameBoard cards = new GameBoard(2);
		
		Queue<Player> players = new LinkedList<Player>();
		
		Player kevin = new Player("Kevin");
		Player david = new Player("David");
		players.add(kevin);
		players.add(david);
		
		GamePlay game = new GamePlay(2, players, cards);
		
		// Just make all the matches here so we can end the game fast
		HashMap<Integer, Integer> matches = new HashMap<Integer, Integer>();
		
		List<Card> cardList = cards.getCardList();
		// find index of matching card
		for (int i = 0; i < cardList.size(); i++) {
			Card curr = cardList.get(i);
			for (int j = 0; j < cardList.size(); j++) {
				Card other = cardList.get(j);
				if (curr.isTwin(other)) {
					matches.put(i, j);
				}
			}
		}
		
		
		// both players should end up with 30 as their score
		// because this is one flip mode
		for (int idx : matches.keySet()) {
			game.selectCard(idx);
			game.checkPair();
				
			game.selectCard(matches.get(idx));
			game.checkPair();
		}
		
		assertEquals("Wrong score", 30, kevin.getScore());
		assertEquals("Wrong score", 30, david.getScore());

	}

}
