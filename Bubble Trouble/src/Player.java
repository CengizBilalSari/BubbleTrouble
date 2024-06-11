/**
 * This player class includes the features and behaviors of the balls.
 * Player has some constant numbers ,and it has to move with left and right commands.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */


import java.awt.event.KeyEvent;

public class Player {

    // Period of player, player height-width rate, player height, scaleY rate are main final variable for the player, and it has x,y coordinates
    public final double PERIOD_OF_PLAYER;
    public final double PLAYER_HEIGHT_WIDTH_RATE;
    public final double PLAYER_HEIGHT_SCALEY_RATE;
    public static double x = 8.00;
    public final double Y = 0.60;

    public Player(double PERIOD_OF_PLAYER, double PLAYER_HEIGHT_WIDTH_RATE, double Player_HEIGHT_SCALEY_RATE) {
        this.PERIOD_OF_PLAYER = PERIOD_OF_PLAYER;
        this.PLAYER_HEIGHT_SCALEY_RATE = Player_HEIGHT_SCALEY_RATE;
        this.PLAYER_HEIGHT_WIDTH_RATE = PLAYER_HEIGHT_WIDTH_RATE;
    }


    // It is for first screen
    public void creatingPlayer() {
        StdDraw.picture(x, Y, "player_back.png", PLAYER_HEIGHT_SCALEY_RATE * PLAYER_HEIGHT_WIDTH_RATE, PLAYER_HEIGHT_SCALEY_RATE);
    }

    // The code will use it to create and update player in the main while loop.
    public void creatingPlayer1() {
        StdDraw.picture(x, Y, "player_back.png", PLAYER_HEIGHT_SCALEY_RATE * PLAYER_HEIGHT_WIDTH_RATE, PLAYER_HEIGHT_SCALEY_RATE);
        updatingXPosition();
    }

    // This is the most important method for player. To update x position, the code take information from key as pressing left or right button.
    //So the player x is changing when the player presses left or right.
    public void updatingXPosition() {
        if (x > 0.5 && x < 15.5) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                x -= Environment.SCALE_X_MAX / PERIOD_OF_PLAYER * Environment.PAUSE_DURATION * 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                x += Environment.SCALE_X_MAX / PERIOD_OF_PLAYER * Environment.PAUSE_DURATION * 2;
            }
        } else if (x > 0.46 && x < 15.5) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                x += Environment.SCALE_X_MAX / PERIOD_OF_PLAYER * Environment.PAUSE_DURATION * 2;
            }
        } else if (x > 0.5 && x < 15.54) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                x -= Environment.SCALE_X_MAX / PERIOD_OF_PLAYER * Environment.PAUSE_DURATION * 2;
            }
        }
    }
}
