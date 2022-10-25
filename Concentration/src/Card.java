/*
* AUTHOR: Kevin Nisterenko
* FILE: Card.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class represents a single card and its state,
* it contains information about its identifier (string of the image
* filename in this program), its flip status and what card is its 
* twin/pair. 
*
* There are no inputs for this specific file. 
*/

public class Card {
	private boolean flipped;
	private Card twin;
	private String image;
	
	/*
	 * Card class constructor, it takes in the file name String to 
	 * initialize a card. 
	 * 
	 * @param imageFile, String representing the identifier for the
	 * card, in this case its filename
	 */
	public Card(String imageFile) {
		flipped = false;
		image = imageFile;
	}
	
	/*
	 * This method returns whether or not the given card
	 * is the corresponding pair/twin of this card. 
	 * 
	 * @param other, Card object representing a
	 * card that needs to be checked for parity
	 * @return boolean representing whether or not
	 * the given card is this card's twin
	 */
	public boolean isTwin(Card other) {
		return (other.equals(twin));
	}
	
	/*
	 * This method sets the card's twin from 
	 * a given card. 
	 * 
	 * @param other, Card object representing a
	 * card to be set as the twin
	 */
	public void setTwin(Card other) {
		twin = other;
		other.twin = this;
	}
	
	/*
	 * This method returns the image name 
	 * of the card.
	 * 
	 * @return image, String representing
	 * the image file of the card
	 */
	public String getImage() {
		return image;
	}
	
	/*
	 * This method sets the card 
	 * status to flipped.
	 */
	public void flip() {
		flipped = true;
	}
	
	/*
	 * This method resets the flipped
	 * status of the card. 
	 */
	public void unflip() {
		flipped = false;
	}
	
	/*
	 * This method checks whether or not the
	 * car is flipped (face up). 
	 * 
	 * @return flipped, boolean representing
	 * flip status of card (true if face up, false
	 * otherwise)
	 */
	public boolean isFlipped() {
		return flipped;
	}
}
