/*
* AUTHOR: Kevin Nisterenko
* FILE: CanvasMouseListener.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This program defines CanvasMouseListener class by implementing
* the MouseListener class. It is here where the gameplay methods are called 
* when the user perform an action (in this case a click), the screen is 
* redrawn and the image list updated to reflect any gameplay changes. 
*
* There are no inputs for this specific file. 
*/

import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * mouse clicks in the canvas
 * 
 */
public class CanvasMouseListener implements MouseListener {
    private GamePlay game;
    
    private List<Image> imgList;
    private Shell shell;
    private Canvas canvas;
    private Display display;
	
    /*
	 * Constructor for the CanvasMouseListener, sets the attributes based
	 * on the image list of the cards, shell, canvas and lastly takes the 
	 * gameplay, which is the actual game, to make plays when a click happens.
	 * 
	 * @param sh, Shell object representing the open GUI shell
	 * @param canvas, Canvas object representing the GUI canvas
	 * @param display, Display object, to be used to set the images
	 * @param imList, List of Image objects containing the images of the 
	 * cards in the appropriate order
	 * @param gP, GamePlay object representing the current game
	 */
    public CanvasMouseListener(Shell sh, Canvas canvas, Display display,
    		List<Image> imList, GamePlay gP) {
    	shell = sh; 
    	this.canvas = canvas; 
    	this.display = display;
    	game = gP;
    	imgList = imList;
    } 
    
    @Override
	public void mouseDoubleClick(MouseEvent event){}
	
    /*
	 * This method is used to make a play in the game when the mouse is
	 * clicked in the window. It fist gets the index based on the mouse position
	 * which is passed to the selectCard method of the game. Then, after
	 * the card is selected the image list is updated and the canvas redrawn. 
	 * The actual gameplay logic is handled the GamePlay class. 
	 * 
	 * @param event, MousEvent object representing a mouse event, here a 
	 * click
	 */
	public void mouseDown(MouseEvent event) {
		Rectangle rect = shell.getClientArea();
		ImageData data = imgList.get(0).getImageData();
		int col = event.x/data.width;
		int row = event.y/data.height;
		int idx = col + row * rect.width/data.width;
		// When a user makes a click, we call the gameplay methods to
		// update game state and actually perform the gameplay 
		game.selectCard(idx);
		// Need to redraw/update the canvas in this class because
		// gameplay has no access to GUI interface
		updateImgList();
		// used canvas instead of shell because no updates 
		// were made using shell
		canvas.update();
		canvas.redraw();
		// Check pair after redraw so that the user can see the 
		// second card they select even if it's wrong
		game.checkPair();
	}
	
	@Override
	public void mouseUp(MouseEvent e){}
	
	/*
	 * This helper method is used to update the image list based on the
	 * state of the gameboard/game, so that the redraw correctly
	 * displays face up and face down cards. 
	 */
	private void updateImgList() {
		GameBoard board = game.getBoard();
		// iterate over the cards in the grid, update the image
		// depending if the card is flipped or not
		for (int i = 0; i < board.size(); i++) {
			Card curr = board.getCard(i);
			if (curr.isFlipped()) {
				imgList.set(i, new Image(display, curr.getImage()));
			} else {
				imgList.set(i, new Image(display, "blank.jpg"));
			}
		}
	}
}	
