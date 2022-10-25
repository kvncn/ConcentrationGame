/*
* AUTHOR: Kevin Nisterenko
* FILE: CanvasPaintListener.java
* ASSIGNMENT: Concentration/Memory Game
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class is an implementation of the PaintListener 
* class, it is slightly modified from the one given by the instructor.
* The use of this class is to draw the window, that is, what the user sees 
* on the GUI. For this specific program, it draws the cards on the board.
*
* There are no inputs for this specific file. 
*/

import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

/**
 * repaints the canvas
 * 
 */
public class CanvasPaintListener implements PaintListener 
{
    private Shell shell;
    private List<Image> imgList;
    
    /*
	 * Constructor for the CanvasPaintListener, sets the attributes based
	 * on the image list of the cards and the shell to then redraw the board
	 * when the appropriate method is called.
	 * 
	 * @param sh, Shell object representing the open GUI shell
	 * @param imList, List of Image objects containing the images of the 
	 * cards in the appropriate order
	 */
    public CanvasPaintListener(Shell sh, List<Image> imList)
    {
    	shell = sh; imgList = imList;
    }
    
    /*
	 * This method takes a paint event (redraw and update) to redraw the 
	 * board on the screen. It iterated through the image list and 
	 * correctly puts each card image in its correct grid spot. 
	 * 
	 * @param event, PaintEvent object representing an event from the system
	 */
	public void paintControl(PaintEvent event) 
	{
		Rectangle rect = shell.getClientArea();
		ImageData data = imgList.get(0).getImageData();
		int stride = rect.width/data.width;
        for (int i = 0, j = 0; i < imgList.size(); i++) {
        	event.gc.drawImage(imgList.get(i), (i%stride)*data.width, (i/stride)*data.height);
        }
	}
}  