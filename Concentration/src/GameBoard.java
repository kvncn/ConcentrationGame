/*
* AUTHOR: Kevin Nisterenko
* FILE: GameBoard.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This program defines the GameBoard class and its methods. 
* This class is responsible for initializing the list of cards that will
* be played in the game, and keeping track of how many cards are still 
* at play. There are two static fields, those are the different types of
* cards (fruits and animals). 
*
* There are no inputs for this specific file. 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
	private static String[] FRUITS = new String[] {"apple.jpg", "pear.jpg", "peach.jpg", "pineapple.jpg", "greenapple.jpg", "avocado.jpg"};
	private static String[] ANIMALS = new String[] {"duck.jpg", "lion.jpg", "cat.jpg", "fish.jpg", "dolphin.jpg", "zebra.jpg"};
	
	private List<Card> cardList;
	
	private int cardsAtPlay;
	
	/*
	 * Constructor for the GameBoard, takes the collection type that will be used, 
	 * and calls the card initialization method to be able to build the list 
	 * of cards. It also sets the number of cards at play. 
	 * 
	 * @param type, integer representing which card collection to use when creating
	 * the card list
	 */
	public GameBoard(int type) {
		// Setup the card list depending on what collection the user chose
		// for more collections, just add more cases!
		switch (type) {
		case 1: 
			cardList = initializeCards(FRUITS);
			break;
		case 2: 
			cardList = initializeCards(ANIMALS);
			break;
		}
		cardsAtPlay = cardList.size();
	}
	
	/*
	 * This method is used to get the card object that is located
	 * at a certain index in the game board. 
	 * 
	 * @param idx, integer representing the index to get the card from
	 * @return Card object representing the card at the given index
	 */
	public Card getCard(int idx) {
		return cardList.get(idx);
	}
	
	/*
	 * This method is used to get the total number of cards (found or not)
	 * that there are in the board. 
	 * 
	 * @return integer representing the size of the board, how many cards
	 * the board has in total regardless of how many have been found
	 */
	public int size() {
		return cardList.size();
	}
	
	/*
	 * This method is used to get the number of cards currently at 
	 * play, that is, that have not been found. 
	 * 
	 * @return cardsAtPlay, integer representing how many cards 
	 * are still in the game
	 */
	public int cardsAtPlay() {
		return cardsAtPlay;
	}
	
	/*
	 * This method is used to decrement the number of cards at the board
	 * when a pair is found. It decreases by 2 because we make a play at 
	 * a pair of cards. 
	 */
	public void makePlay() {
		cardsAtPlay -= 2;
	}
	
	/*
	 * This method is used to get the card object that is located
	 * at a certain index in the game board. 
	 * 
	 * @return List of Card objects that has the cards on the board
	 */
	public List<Card> getCardList() {
		return cardList;
	}
	
	/*
	 * This helper method is used to initialize the card list from the given
	 * collection. It builds the card list with the appropriate pairs, 
	 * then shuffles it so that the board is randomized.
	 * 
	 * @param collection, String array that represents a card collection, here
	 * each string is a filename (so the GUI can use that later on)
	 * @return cardList, List of Card objects representing the board formed
	 * from cards of the given collection
	 */
	private List<Card> initializeCards(String[] collection) {
		List<Card> cardList = new ArrayList<Card>();
				
		for (String str : collection) {
			cardList.add(new Card(str));
		}
				
		int size = cardList.size();
		for (int i = 0; i < size; i++) {
			// Every time we create a new card based on an image,
			// we actually also create its twin to ensure we
			// will always have pairs
			String imgFile = cardList.get(i).getImage();
			Card twin = new Card(imgFile);
			cardList.get(i).setTwin(twin);
			cardList.add(twin);
		}
		
		// Shuffle the cards so that we get a random grid every time
		// for interesting game play and also to make sure that 
		// it isn't the case that every pair will be right next
		// to each other (it can still happen, but it is not
		// guaranteed due to the shuffling)
		Collections.shuffle(cardList);
		
		return cardList;
	}
}