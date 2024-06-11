/**
 * This environment class includes most of the thing for main application.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * In this class there are lots of method for main bubbleTroubleGame.
 * Most of the constants are created for canvas and  background of the game.
 * The most crucial and main  method of this class is bubbleTrouble method.
 * In this method  all things are happened ,and  it basically does its job in while loop whose condition is  whether time is over or not.
 */

public class Environment {


    // general integers and doubles for creating graph
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 500;
    public static final double SCALE_X_MIN = 0.0;
    public static final double SCALE_X_MAX = 16.0;
    public static final double SCALE_Y_MIN = -1.0;
    public static final double SCALE_Y_MAX = 9.0;
    public static final double SCALE_Y_FOR_TIME_BAR_MIN = -1.0;
    public static final double SCALE_Y_FOR_TIME_BAR_MAX = 0.0;
    public static final double SCALE_Y_FOR_GAME_MIN = 0.0;
    public static final double SCALE_Y_FOR_GAME_MAX = 9.0;
    public static final int TOTAL_GAME_DURATION = 40000;   //ms
    public static final int PAUSE_DURATION = 15; //ms  // This the pause duration for while loop, each while loop this time will pass.

    public int counterForChildBall = 0;  // this will be used to count child balls to index balls for ball array.

    public static boolean isTheGameOverAccordingToTime = true; // boolean to control the time
    public boolean theControlOfIntersectionOfBallAndPlayer = false;  // boolean to control the intersection of ball and player

    // These three  static int and boolean is created for end of the game
    public static int willBeGamePlayedAgainWhenGameOver;
    public static int willBeGamePlayedAgainWhenPlayerWin;
    public static boolean didPlayerWinTheGame;

    public int counterForDrawingAnimationOneMoreTimeForCancelingBalls = 0;

    // those two method is for basically creating canvas and putting the background image
    public void creatingBackground() {
        StdDraw.picture(SCALE_X_MAX / 2, (SCALE_Y_FOR_GAME_MAX + SCALE_Y_FOR_GAME_MIN) / 2, "background.png", 16, 12);
    }

    public void settingCanvas() {
        StdDraw.setCanvasSize(Environment.CANVAS_WIDTH, Environment.CANVAS_HEIGHT);
        StdDraw.setXscale(Environment.SCALE_X_MIN, Environment.SCALE_X_MAX);
        StdDraw.setYscale(Environment.SCALE_Y_MIN, Environment.SCALE_Y_MAX);
    }

    /*the boolean method to control intersection of arrow  and ball It utilizes from basically the equation of circle
     * e.g. x^2+y^2= radius^2 , due to the fact that there is two possibility for finding y( because of the absolute value)
     * the code forms y1 ,y2 and chooses the bigger one.
     */
    public static boolean doesArrowIntersectWithTheBall(double x, double y, double x0, double y0, double radius) {
        double y1;
        double y2;
        y1 = Math.pow(Math.pow(radius, 2) - Math.pow(x - x0, 2), 0.5) + y0;
        y2 = y0 - Math.pow(Math.pow(radius, 2) - Math.pow(x - x0, 2), 0.5);
        if (y1 > y2 && y1 <= y) {
            return true;
        } else return y1 < y2 && y2 <= y;
    }

    /* these two methods are designed to control  intersection of ball and player. Its logic is same as arrow and ball intersection.
     Main difference between them is this time the code will control this intersection from left-right and above because of the shape of player(rectangle).
     */
    public boolean doesPlayerIntersectWithTheBallFromLeftAndRight(double x, double y, double x0, double y0, double radius) {
        double y1;
        double y2;
        y1 = Math.pow(Math.pow(radius, 2) - Math.pow(x - x0, 2), 0.5) + y0;
        y2 = y0 - Math.pow(Math.pow(radius, 2) - Math.pow(x - x0, 2), 0.5);
        if (y1 > y2 && y1 <= y) {
            return true;
        } else return y1 < y2 && y2 <= y;
    }

    public boolean doesPlayerIntersectWithTheBallFromAbove(double x, double y, double x0, double y0, double radius) {
        double x1;
        double x2;
        x1 = Math.pow(Math.pow(radius, 2) - Math.pow(y - y0, 2), 0.5) + x0;
        x2 = x0 - Math.pow(Math.pow(radius, 2) - Math.pow(y - y0, 2), 0.5);
        if ((x1 > x2 && x - 0.3 <= x1) && x1 <= x + 0.3) {
            return true;
        } else return (x1 < x2 && x - 0.3 <= x2) && x2 <= x + 0.3;
    }


    public static void doesGameReplayAgainIfGameOver() {
        Environment.writingGameOver(Environment.SCALE_X_MAX, Environment.SCALE_Y_FOR_GAME_MAX);
        while (Environment.willBeGamePlayedAgainWhenGameOver == 0) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                Environment.willBeGamePlayedAgainWhenGameOver = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                Environment.willBeGamePlayedAgainWhenGameOver = 2;
            }
        }

    }

    public static void doesGameReplayAgainIfPlayerWin() {
        Environment.writingYouWon(Environment.SCALE_X_MAX, Environment.SCALE_Y_MAX);
        while (Environment.willBeGamePlayedAgainWhenPlayerWin == 0) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                Environment.willBeGamePlayedAgainWhenPlayerWin = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                Environment.willBeGamePlayedAgainWhenPlayerWin = 2;
            }
        }
        Environment.didPlayerWinTheGame = false;

    }

    public static void writingGameOver(double scaleX, double scaleY) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(scaleX / 2, scaleY / 2.18, "game_screen.png", scaleX / 3.8, scaleY / 2.6);
        StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
        StdDraw.text(scaleX / 2, scaleY / 2, "Game Over!");
        StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
        StdDraw.text(scaleX / 2, scaleY / 2.3, "To Replay Click\"Y\"");
        StdDraw.text(scaleX / 2, scaleY / 2.6, "To Quit Click\"N\"");
        StdDraw.show();
    }

    public static void writingYouWon(double scaleX, double scaleY) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(scaleX / 2, scaleY / 2.18, "game_screen.png", scaleX / 3.8, scaleY / 2.6);
        StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
        StdDraw.text(scaleX / 2, scaleY / 2, "You Won!");
        StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
        StdDraw.text(scaleX / 2, scaleY / 2.3, "To Replay Click\"Y\"");
        StdDraw.text(scaleX / 2, scaleY / 2.6, "To Quit Click\"N\"");
        StdDraw.show();

    }


    // This method control the balls. If there is no ball, it is considered as player wins the game and the method returns true.
    public static boolean areAllElementsNull(Object[] array) {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }


    /* Bubble trouble game is the main method for the game. Firstly  the code creates constructors. Ball are created as a ball array with initially 3 ball.
     * After creating background bar etc. the method enters the while loop. In this while loop, the code clear and create the image again and again.
     * The first for loop is designed for ball movement and their child balls. If the arrow  intersect with any ball it splits and create new child ball and
     * the parent one disappears.
     * Each loop, code controls the conditions:
     *  Whether player and ball intersect or not
     * Whether the time ends or not
     * Whether there are no ball or not.
     * These three conditions are  crucial to end the game and break the loop.
     */
    public static void bubbleTroubleGame() {
        // the constructors of the method

        Environment environment = new Environment();
        Player player = new Player(3000, 23.0 / 37, 1.25);
        Bar bar = new Bar();
        Arrow arrow = new Arrow();
        Ball[] ballArray = new Ball[11];
        ballArray[0] = new Ball(0, 5, 5);
        ballArray[1] = new Ball(1, 4, 5);
        ballArray[2] = new Ball(2, 3.5, 5);

        //creating first picture of the canvas
        environment.creatingBackground();
        bar.creatingBarBackground((Environment.SCALE_X_MIN + Environment.SCALE_X_MAX) / 2, (Environment.SCALE_Y_FOR_TIME_BAR_MIN + Environment.SCALE_Y_FOR_TIME_BAR_MAX) / 2 - 0.1);
        player.creatingPlayer();
        bar.creatingBar(bar.length, bar.y0);
        StdDraw.show();
        StdDraw.enableDoubleBuffering();


        while (Environment.isTheGameOverAccordingToTime) {
            // we have to clear everything and create  everything again
            StdDraw.clear();
            player.creatingPlayer1();
            environment.creatingBackground();
            bar.creatingBarBackground((Environment.SCALE_X_MIN + Environment.SCALE_X_MAX) / 2, (Environment.SCALE_Y_FOR_TIME_BAR_MIN + Environment.SCALE_Y_FOR_TIME_BAR_MAX) / 2);
            bar.creatingBar1(bar.y0);
            arrow.arrowDrawing();
            player.creatingPlayer();
            StdDraw.pause(Environment.PAUSE_DURATION);


            // main for loop for ball movements and their issues
            for (int i = 0; i <= ballArray.length - 1; i++) {

                if ((ballArray[i] != null && Environment.doesArrowIntersectWithTheBall(Arrow.startingPointXForArrow, Arrow.scaleYForArrow, ballArray[i].x, ballArray[i].y, ballArray[i].radiusOfBall)) && (ballArray[i].levelOfradius == 0) && !arrow.didArrowdoItsJob) {
                    ballArray[i] = null;
                    arrow.didArrowdoItsJob = true;
                } else if ((ballArray[i] != null && Environment.doesArrowIntersectWithTheBall(Arrow.startingPointXForArrow, Arrow.scaleYForArrow, ballArray[i].x, ballArray[i].y, ballArray[i].radiusOfBall)) && !arrow.didArrowdoItsJob) {

                    ballArray[ballArray.length - 1 - environment.counterForChildBall] = new Ball(ballArray[i].levelOfradius - 1, ballArray[i].x, ballArray[i].y);
                    ballArray[ballArray.length - 1 - environment.counterForChildBall].velocityX *= -1;
                    ballArray[ballArray.length - 2 - environment.counterForChildBall] = new Ball(ballArray[i].levelOfradius - 1, ballArray[i].x, ballArray[i].y);
                    ballArray[ballArray.length - 1 - environment.counterForChildBall].velocityY = Math.pow(2 * Ball.GRAVITATION_CONSTANT * (Ball.minPossibleHeight * (Math.pow(Ball.HEIGHT_MULTIPLIER, ballArray[ballArray.length - 1 - environment.counterForChildBall].levelOfradius))), 0.5);
                    ballArray[ballArray.length - 2 - environment.counterForChildBall].velocityY = Math.pow(2 * Ball.GRAVITATION_CONSTANT * (Ball.minPossibleHeight * (Math.pow(Ball.HEIGHT_MULTIPLIER, ballArray[ballArray.length - 2 - environment.counterForChildBall].levelOfradius))), 0.5);
                    ballArray[i] = null;
                    arrow.didArrowdoItsJob = true;
                    environment.counterForChildBall += 2;
                }
                if (ballArray[i] != null) {
                    ballArray[i].draw();
                    ballArray[i].updatingPositionOfTheBall(Environment.PAUSE_DURATION);
                }
            }
            StdDraw.show();

            // to find whether ball and player intersect or not
            for (Ball ball : ballArray) {
                if (ball != null) {
                    if ((environment.doesPlayerIntersectWithTheBallFromLeftAndRight(Player.x - 0.3, player.Y * 2, ball.x, ball.y, ball.radiusOfBall) || environment.doesPlayerIntersectWithTheBallFromLeftAndRight(Player.x + 0.3, player.Y * 2, ball.x, ball.y, ball.radiusOfBall)) || environment.doesPlayerIntersectWithTheBallFromAbove(Player.x, player.Y * 2 - 0.05, ball.x, ball.y, ball.radiusOfBall)) {
                        environment.theControlOfIntersectionOfBallAndPlayer = true;
                    }
                }
            }

            //to control the intersection of ball and player
            if (environment.theControlOfIntersectionOfBallAndPlayer) {
                StdDraw.clear();
                player.creatingPlayer1();
                environment.creatingBackground();
                bar.creatingBarBackground((Environment.SCALE_X_MIN + Environment.SCALE_X_MAX) / 2, (Environment.SCALE_Y_FOR_TIME_BAR_MIN + Environment.SCALE_Y_FOR_TIME_BAR_MAX) / 2);
                bar.creatingBar1(bar.y0);
                player.creatingPlayer();
                break;
            }

            // to control whether there are no balls or there are balls
            if (Environment.areAllElementsNull(ballArray)) {
                Environment.didPlayerWinTheGame = true;
                break;
            }

            // to control the time
            if (!isTheGameOverAccordingToTime) {
                StdDraw.clear();
                player.creatingPlayer1();
                environment.creatingBackground();
                bar.creatingBarBackground((Environment.SCALE_X_MIN + Environment.SCALE_X_MAX) / 2, (Environment.SCALE_Y_FOR_TIME_BAR_MIN + Environment.SCALE_Y_FOR_TIME_BAR_MAX) / 2);
                player.creatingPlayer();

            }
        }
    }
}