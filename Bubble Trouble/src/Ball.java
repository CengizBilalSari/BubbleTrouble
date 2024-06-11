/**
 * This ball class includes the features and behaviors of the balls.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */

public class Ball {

    /* Ball class has a lot  constant. We have three level of ball.
     * height and radius multiplier, minimum possible height and radius will be used to create different balls.
     *  The level of balls are zero, one and two and the radius is increasing as number increases.
     * The gravitation constant is for acceleration of ball in y-axis.
     */

    public static final double GRAVITATION_CONSTANT = 0.000001 * (Environment.SCALE_Y_MAX + 1);
    public static final double periodOfBall = 12000; //ms
    public static final double HEIGHT_MULTIPLIER = 1.53;
    public static final double RADIUS_MULTIPLIER = 2.0;
    public static final double minPossibleRadius = (Environment.SCALE_Y_MAX) * 0.0175;
    public static final double minPossibleHeight = 1.1 * 1.4;
    public int levelOfradius;
    public double radiusOfBall;
    public double velocityX = Environment.SCALE_X_MAX / periodOfBall;
    public double x;
    public double y;
    public double velocityY = 0;

    // the code in environment class has to enter the level of radius  and the coordinates of the ball to ball constructor
    public Ball(int levelOfradius, double x, double y) {
        this.levelOfradius = levelOfradius;
        this.x = x;
        if (levelOfradius == 1) {
            velocityX = -velocityX;
        }
        this.y = y;
    }

    // The formula  to determine radius
    public void assingingRadius() {
        radiusOfBall = (minPossibleRadius) * (Math.pow(RADIUS_MULTIPLIER, levelOfradius));
    }


    /* the balls are updated again and again in while loop. Each while loop the time is passing according to pause duration.
     * so we know the dt which ball has to update its velocity according to pause duration.
     * The magnitude of velocity in x-axis is constant ,and we find it from the period of ball.
     * The velocity of y-axis is changing according to gravitation and also ball size.
     * This is the main function for ball class to uptade the location of the ball.
     */
    public void updatingPositionOfTheBall(double dt) {
        assingingRadius();
        velocityY -= GRAVITATION_CONSTANT * dt;

        // uptade position based on velocityX and velocityY

        x += velocityX * dt;
        y += velocityY * dt;

        if (x - radiusOfBall < 0) {
            x = radiusOfBall;
            velocityX = -velocityX;
        }
        if (x + radiusOfBall > 16) {
            x = 16 - radiusOfBall;
            velocityX = -velocityX;
        }
        if (y - radiusOfBall < 0) {
            y = radiusOfBall;
            velocityY = Math.pow(2 * GRAVITATION_CONSTANT * minPossibleHeight * (Math.pow(HEIGHT_MULTIPLIER, levelOfradius)), 0.5);
        }
        if (y + radiusOfBall > 9.0) {
            y = 16 - radiusOfBall;
            velocityY = -velocityY;
        }

    }

    // while the code is updating the position of ball, it has to draw it again and again too.
    public void draw() {
        StdDraw.picture(x, y, "ball.png", radiusOfBall * 2, radiusOfBall * 2);
    }
}