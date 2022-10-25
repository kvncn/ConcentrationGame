/*
* AUTHOR: Kevin Nisterenko
* FILE: GamePlay.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This program defines the GamePlay class and its methods. 
* This class is responsible for the game and its state, the actual logic
* of finding pairs, checking how many cards are currently selected by the 
* player, whose turn is it, and then who won the game. 
*
* There are no inputs for this specific file. 
*/

import java.util.Queue;

public class GamePlay {
	private static Queue<Player> players;
	private int clicks;
	private int mode;
	private GameBoard gameCards;
	private Card prevCard;
	private Card currCard;
	private Player player;
	
	/*
	 * Constructor for the GamePlay class, it takes the game mode, player 
	 * queue and the game board to initialize the game state. The constructor 
	 * also sets the current player, current card, previous card (for pair
	 * checking), the current clicks in a play (so each player has two clicks
	 * per turn). 
	 * 
	 * @param gMode, integer representing which game mode to run the 
	 * game in 
	 * @param plQueue, Queue of Player objects representing the order 
	 * of players by turn
	 * @param cards, GameBoard object representing the cards on the board
	 */
	public GamePlay(int gMode, Queue<Player> plQueue, GameBoard cards) {
		gameCards = cards;
		mode = gMode;
    	players = plQueue; 
    	clicks = 0;
    	currCard = null;
    	prevCard = null;
    	player = players.peek();
    	System.out.println("Please pick a pair " + player.getName());
	}
	
	/*
	 * This method returns the game board.
	 *
	 * @return cards, GameBoard object representing the 
	 * cards on the board
	 */
	public GameBoard getBoard() {
		return gameCards;
	}
	
	/*
	 * This function checks if the current and previous selected
	 * cards are a pair. If a pair is not yet selected, we 
	 * just skip this function by returning. Otherwise, we 
	 * if two cards are selected, we check if they make up an
	 * actual pair. If they are, we print a found message and 
	 * then we check for end of game and player swaps. 
	 * If the pair is not an actual pair, we unflip them back to
	 * be face down and swap players regardless of gamemode. 
	 */
	public void checkPair() {
		// If we aren't in a player's second card, we can
		// skip the checking
		if (clicks != 2) {
			prevCard = currCard;
			return;
		}
		
		// Reset clicks 
		clicks = 0;
		
		// If a pair is found we just need to reset prevCard, add
		// to player score and then possibly swap player (depending
		// on game mode)
		if (currCard.isTwin(prevCard)) {
			prevCard = null;
			player.addScore();
			gameCards.makePlay();
			System.out.println(player.getName().toUpperCase()
					+ " SCORES!!");
			// If there are no more cards, no need to swap players, just 
			// get the winner
			if (gameCards.cardsAtPlay() == 0) {
				System.out.println("\nGAME OVER");
				printScores();
				return;
			}
			// this skips player swap if pair found for regular game
			if (mode == 1) return; 
			// No skipping if one-flip game mode, add more cases for
			// further game modes
			swapPlayers();
			return;
		}
		System.out.println(player.getName().toUpperCase() + 
				" did not find a pair! Please click under"
				+ " the grid to reset it");
		// Since the already flipped card and the just flipped card 
		// are not a pair, unflip them
		currCard.unflip();
		prevCard.unflip();
		swapPlayers();
		currCard = null;
		prevCard = null;
	}
	
	/*
	 * This function makes a card selection based on the given index,
	 * it gets the appropriate card from the game board. If the index
	 * is not in the board, we simply return, this is used to 
	 * reset the board after an incorrect pair as well. Otherwise, 
	 * we simply select a card.
	 * 
	 * @param idx, integer representing card index
	 */
	public void selectCard(int idx) {
		// Fix for game to reset flipped cards if no pair was found
		// if the player clicks under grid, it corrects image
		if (idx >=  gameCards.size() || idx < 0) {
			return;
		}
		currCard = gameCards.getCard(idx);
		
		// If the current card is not flipped, flip it and update 
		// the clicks flag
		if (!currCard.isFlipped()) {
			currCard.flip();
			clicks++;
			return;
		} 
		return;
	}
	
	/*
	 * This function iterates over the player queue to print
	 * all players' scores. 
	 */
	private void printScores() {
		int size = players.size();
		
		// Simple loop to just find highest score player
		for (int i = 0; i < size; i++) {
			Player curr = players.poll();
			System.out.println(curr.getName() + " finished with " + curr.getScore() + " points!");
		}
	}
	
	/*
	 * This function moves the current player to the back of the
	 * queue and prints the turn message for the new current 
	 * player. 
	 */
	private void swapPlayers() {
		// Swap Players
		players.add(player);
		Player prev = players.poll();
		player = players.peek();
		
		// To not repeat turn message
		if (!prev.equals(player)) 
			System.out.println("\nPlease pick a pair " + player.getName());
	}
}
