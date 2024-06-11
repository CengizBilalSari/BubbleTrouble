/**
 * This bar class includes the features and behaviors of the bar.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */

public class Bar {
    /**
     * Bar has RGB value, and while time goes, the color of the bar will go from yellow to red.
     * When the time is ended, it means that player could not win the game.
     * Also, the code has bar background ,and it should create it too.
     * It has height scale and starting time variables.
     * For its x position the code uses the ScaleXMax from environment class.
     */


    public int intensityOfColorGreen = 225; // It is the only color whose intensity value will change
    public final int INTENSITY_OF_COLOR_RED = 225;
    public final int INTENSITY_OF_COLOR_BLUE = 0;

    private final double HEIGHT_SCALE = 0.5;
    public double length = Environment.SCALE_X_MAX / 2.0;

    public double y0 = -HEIGHT_SCALE;
    public final double STARTING_TIME;

    Bar() {
        STARTING_TIME = System.currentTimeMillis();
    }

    ; // the constructor need get the time to keep and calculate remaining time.

    // Basic method to creating bar background
    public void creatingBarBackground(double x0, double y0) {
        StdDraw.picture(x0, y0, "bar.png", 16, 1);
    }


    // It is for first screen
    public void creatingBar(double x0, double y0) {
        StdDraw.setPenColor(INTENSITY_OF_COLOR_RED, intensityOfColorGreen, INTENSITY_OF_COLOR_BLUE);
        StdDraw.filledRectangle(x0, y0, length, HEIGHT_SCALE / 2);
    }

    // The code will use it to create and update bar in the main while loop. Also, if the time is over, the boolean which controls the time returns false.
    public void creatingBar1(double y0) {
        StdDraw.setPenColor(INTENSITY_OF_COLOR_RED, intensityOfColorGreen, INTENSITY_OF_COLOR_BLUE);
        StdDraw.filledRectangle(length, y0, length, HEIGHT_SCALE / 2);
        changingTheLengthOfTheBar();
        changingDensityOfGreen();
        if (length < 0) {
            Environment.isTheGameOverAccordingToTime = false;
        }

    }


    // those two methods is created for  changing the length and color density of bar. It utilizes from the time differences between current and starting time.
    public void changingTheLengthOfTheBar() {
        length = 8.0 / Environment.TOTAL_GAME_DURATION * (Environment.TOTAL_GAME_DURATION - (System.currentTimeMillis() - STARTING_TIME));
    }

    public void changingDensityOfGreen() {
        intensityOfColorGreen = (int) (255.0 / Environment.TOTAL_GAME_DURATION * (Environment.TOTAL_GAME_DURATION - (System.currentTimeMillis() - STARTING_TIME)));
    }
}
