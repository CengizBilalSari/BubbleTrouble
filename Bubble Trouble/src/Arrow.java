/**
 * This arrow class includes the features and behaviors of the arrows.
 * An arrow can be alive only if there is no arrow. And the creating process of the arrow has to consider this condition.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */

import java.awt.event.KeyEvent;

public class Arrow {

    public boolean didArrowdoItsJob = false;  // the arrow has to disappear when it does its job, so the code has to control it whether did arrow do its job or not.
    public static double startingTimeForArrow;
    public static final int PERIOD_OF_ARROW = 1500; //ms
    public static double startingPointXForArrow;  // I will be equivalent to the x position of the player

    public static boolean isThereAnyArrow = false; // If there is an arrow, player cannot send another arrow
    public static double scaleYForArrow = 0.0;  // The scale Y of the Arrow has to increase

    Arrow() {
    }

    // the code will use this method to change the scale of the arrow inside of other methods
    public void changingYForArrow() {
        scaleYForArrow = 9.0 / PERIOD_OF_ARROW * (System.currentTimeMillis() - Arrow.startingTimeForArrow);
    }

    // this method will be used for drawing arrow whose scaleY is altering with changingYArrow method
    public void drawingArrow() {
        StdDraw.picture(Arrow.startingPointXForArrow, scaleYForArrow / 2, "arrow.png", 0.15, scaleYForArrow);
        changingYForArrow();
    }

    /**  This is the main arrow method which the code will use. The arrow has some constraints.
     *Firstly after it hits the ceiling it should disappear.  It can be only created when the player press space button.
     * When it hits the ball and split the ball, it has to disappear too.
     * This method basically formed to determine whether arrow should be created or not and if the answer is yes, it creates it. **/
    public void arrowDrawing() {
        if (scaleYForArrow > 9.0) {
            isThereAnyArrow = false;
            scaleYForArrow = 0;
        }
        if (didArrowdoItsJob) {
            isThereAnyArrow = false;
            didArrowdoItsJob = false;
            scaleYForArrow = 0;
        }
        if (!isThereAnyArrow) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                Arrow.startingTimeForArrow = System.currentTimeMillis();
                Arrow.startingPointXForArrow = Player.x;
                isThereAnyArrow = true;
            }
        }
        if (isThereAnyArrow) {
            drawingArrow();
        }
    }
}