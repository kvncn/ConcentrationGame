/*
* AUTHOR: Kevin Nisterenko
* FILE: Player.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class represents a single player and their state,
* it contains their name and their score. 
*
* There are no inputs for this specific file. 
*/

public class Player {
	private int score;
	private String name;
	
	/*
	 * Constructor for the Player class, it takes 
	 * a string, which is the player's name and sets 
	 * it to the appropriate field. It also sets the
	 * initial score. 
	 * 
	 * @param name, String representing the player's name
	 */
	public Player(String name) {
		this.name = name;
		score = 0;
	}
	
	/*
	 * This method adds 10 to the player score. 
	 */
	public void addScore() {
		score += 10;
	}
	
	/*
	 * This method returns the player score.
	 * 
	 * @return score, integer representing player 
	 * score
	 */
	public int getScore() {
		return score;
	}
	
	/*
	 * This method returns the player name. 
	 * 
	 * @return name, String representing player 
	 * name
	 */
	public String getName() {
		return name;
	}
	
}
