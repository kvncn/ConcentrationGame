/*
* AUTHOR: Kevin Nisterenko
* FILE: ConcetrationGame.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This program defines the ConcentrationGame, it works as a main
* program. It gets user input to setup the game, game board, and the GUI
* widgets. It then packs everything together and initializes the main 
* shell loop so the game can be played.
*
* There are no inputs for this specific file. 
*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ConcentrationGame {
	
	/*
	 * This method uses a while loop to continuously take input 
	 * to build a player queue. The loop ends if an empty line 
	 * is given. The queue is then returned, there is also a 
	 * guarantee that at least one player will be in the queue 
	 * even if the player types nothing by setting a default player. 
	 * 
	 * @param scan, Scanner object to get input
	 * @return players, Queue of Player objects representing
	 * the players in the game
	 */
	public static Queue<Player> getPlayers(Scanner scan) {
		System.out.println("Please type the player name(s), type enter to stop:");
		
		// Use a queue because we can have the correct order of players
		// so we have multiple payer setup at runtime
		Queue<Player> players = new LinkedList<Player>();
		
		// Keep getting input until user types empty line
		while (true) {
			String input = scan.nextLine();
			if (input.equals("")) break;
			players.add(new Player(input));
		}
		
		if (players.isEmpty()) {
			System.out.println("Since no players were given, one player game "
					+ "will be set\n");
			players.add(new Player("PLAYER"));
		}
		
		return players;
	}
	
	/*
	 * This method takes an integer input to set the game mode the
	 * gameplay will be based on. It uses a switch block to also 
	 * set a default game mode in case of invalid input. 
	 * 
	 * @param scan, Scanner object to get input
	 * @return gameMode, integer representing what mode the 
	 * game should be played in
	 */
	public static int selectGameMode(Scanner scan) {
		System.out.println("Please selece the game mode:");
		System.out.println("Regular (1)\nOne-Flip (2)");
		int gameMode = scan.nextInt();
		
		switch (gameMode) {
			case 1: 
				return gameMode;
			case 2:
				return gameMode;
			default: 
				System.out.println("\nSorry that is not one of the game modes, "
						+ "defaulting to regular");
				return 1;
		}
	}
	
	/*
	 * This method takes an integer input to set the game mode the
	 * gameplay will be based on. It uses a switch block to also 
	 * set a default game mode in case of invalid input. 
	 * 
	 * @param scan, Scanner object to get input
	 * @return gameCollection, integer representing what card ]
	 * collection the game should use
	 */
	public static int getCollection(Scanner scan) {
		System.out.println("\nPlease select which cards you want to use: ");
		System.out.println("Fruits (1)");
		System.out.println("Animals (2)");
		
		int gameCollection = scan.nextInt();
		
		switch (gameCollection) {
		case 1: System.out.println(); return gameCollection;
		case 2: System.out.println(); return gameCollection;
		default: System.out.println("\nSorry that collection does not exist, "
				+ "using fruits"); return 1;
		}
		
	}
	
	/*
	 * This method takes in the display and the game cards to initialize the
	 * image grid with the blank/face down cards using a for loop to iterate
	 * over the number of cards.
	 * 
	 * @param display, Display object, to be used to set the images
	 * @param gameCards, GameBoard object representing the cards on the board
	 * @return imList, List of Image objects containing the images of the 
	 * cards in the appropriate order
	 */
	public static List<Image> initializeGrid(Display display, 
			GameBoard gameCards) {
		List<Image> imList = new ArrayList<Image>();
						
		for (int i = 0; i < gameCards.size(); i++) {
			imList.add(new Image(display, "blank.jpg"));
		}
		return imList;
	}
	
	/*
	 * This method sets the upper composition and the quit button
	 * widget. 
	 * 
	 * @param shell, Shell object representing the open GUI shell
	 */
	public static void createWidgets(Shell shell) {
		//---- our Widgets start here
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);

		Button quitButton = new Button(upperComp, SWT.PUSH);
		quitButton.setText("Quit");
		quitButton.setSize(100, 50);
		quitButton.addSelectionListener(new ButtonSelectionListener());	
		quitButton.setLocation(300, 0);
		//---- our Widgets end here
	}
	
	/*
	 * This method sets the game interaction widgets, that is the mouse 
	 * and paint listeners. 
	 * 
	 * @param shell, Shell object representing the open GUI shell
	 * @param display, Display object, to be used to set the images
	 * @param game, GamePlay object representing the current game
	 */
	public static void setGameInteraction(Shell shell, Display display,
			GamePlay game) {
		List<Image> imgList = initializeGrid(display, game.getBoard());

		Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
		Canvas canvas = new Canvas(lowerComp, SWT.NONE);
		canvas.setSize(400,500);
		
		canvas.addPaintListener(new CanvasPaintListener(shell, imgList));	
		
		CanvasMouseListener mL = new CanvasMouseListener(shell, canvas, 
				display, imgList, game);
		canvas.addMouseListener(mL);
	}
	
	public static void main(String args[]) {
		System.out.println("*** Welcome to Concentration! ***");
		
		// Get input for game qualifiers
		Scanner scan = new Scanner(System.in);
		
		Queue<Player> players = getPlayers(scan);
		
		int gameMode = selectGameMode(scan);
		
		int collection = getCollection(scan);
		
		scan.close(); // stop taking input
		
		// Setup gameplay logic and cards
		GameBoard gameCards = new GameBoard(collection);
		
		GamePlay game = new GamePlay(gameMode, players, gameCards);
		
		// Setup GUI window
		Display display = new Display();
		
		Shell shell = new Shell(display);
		shell.setSize(500,300);
		shell.setLayout(new GridLayout());
		
		createWidgets(shell);
		setGameInteraction(shell, display, game);
		
		shell.pack(); // fills the whole window
		shell.open();
		
		// Canonical shell loop
		while(!shell.isDisposed())	{
			if(!display.readAndDispatch()) display.sleep();
			} 
		display.dispose();
	} 
}

/*
 * Quit button, provided by the instructor, exits the program when 
 * the widget is pressed. 
 */
class ButtonSelectionListener implements SelectionListener 
{
	/*
	 * This method takes the selection event to signal that 
	 * the button was pressed and exits the program. 
	 * 
	 * @param event, SelectionEvent representing button press
	 */
	public void widgetSelected(SelectionEvent event) {
		System.exit(0);
		}
	@Override
	public void widgetDefaultSelected(SelectionEvent event){}    
}	