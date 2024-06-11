/**
 * The main class to making everything for main app we will write.
 *
 * @author Cengiz Bilal Sarı, Student ID: 2021400201
 * @since Date: 08.04.2023
 */

/**
 * This main is designed based on environment class.
 * Firstly the code puts environment constructor, and it sets the canvas.
 * The main while loop includes bubble Trouble Game method of environment class.
 * If this method ends, the code controls whether player wins the game or not.
 * In these conditions, if player wants to quit the game, system exits. If player wants to replay the game,
 * the game starts again.
 */

public class BubbleTrouble {
    public static void main(String[] args) {
        Environment environment = new Environment();
        environment.settingCanvas();

        //main while loop
        while (true) {
            Environment.bubbleTroubleGame();

            // the control of whether player wins or not.
            if (Environment.didPlayerWinTheGame) {
                Environment.doesGameReplayAgainIfPlayerWin();

                // the control of whether player wants to replay or quit the game
                if (Environment.willBeGamePlayedAgainWhenPlayerWin == 2) {
                    System.exit(0);
                    Environment.willBeGamePlayedAgainWhenPlayerWin = 0;  // making this integer  zero for new games.
                    break;
                } else if (Environment.willBeGamePlayedAgainWhenPlayerWin == 1) {
                    Environment.willBeGamePlayedAgainWhenPlayerWin = 0; // making this integer  zero for new games.
                    continue;
                }
            } else {
                Environment.isTheGameOverAccordingToTime = true;
                Environment.doesGameReplayAgainIfGameOver();
                if (Environment.willBeGamePlayedAgainWhenGameOver == 2) {
                    System.exit(0);
                    Environment.willBeGamePlayedAgainWhenPlayerWin = 0; // making this integer  zero for new games.
                    break;
                } else if (Environment.willBeGamePlayedAgainWhenGameOver == 1) {
                    Environment.willBeGamePlayedAgainWhenGameOver = 0; // making this integer  zero for new games.
                    continue;
                }
            }
        }
    }
}