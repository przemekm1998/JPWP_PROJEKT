package sample.scenes.gameview;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import sample.InfoBox;
import sample.scenes.GameView;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.gameview.elements.GameMap;
import sample.scenes.gameview.elements.Instructions;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;
import sample.scenes.gameview.objects.Player;

import java.util.List;

public class GameFlow {
    public static synchronized void runFlow() {
        Action myAction = new Action();

        Thread t1 = new Thread(myAction, "T1");
        t1.setDaemon(true);
        t1.start();
    }

    public static class Action implements Runnable {
        private static boolean exit;

        private static int score;
        private static int steps;

        private static boolean gameWon;

        public void run() {
            exit = false;
            gameWon = false;

            setScore(0);
            setSteps(0);

            while (!isExit()) {
                performSteps();

                // player has encountered obstacle or finish
                if (isExit()) {
                    if (isGameWon()) {
                        showResults("Win!", "You've won!"); // player has won
                    } else if (!isGameWon()) {
                        showResults("Loose", "You've lost!"); // player has lost because of the obstacle
                    }
                }
            }
            // when all of the moves are performed and no win is detected
            if (!isExit()) {
                showResults("Loose", "You've lost!");
            }
        }

        private static void showResults(String title, String message) {
            Platform.runLater(() -> {
                InfoBox.display(title, message);
            });
        }

        private static void performSteps() {

            Player player = GameMap.getPlayerObject();
            GridPane gameMap = GameView.getGameMap();
            List<Directions> commands = Instructions.getCommands();
            MapObject[][] takenCoordinates = GameMap.getTakenCoordinates();

            for (Directions dir : commands) {
                try {
                    Thread.sleep(500); // Wait for 1 sec before updating the color
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                try {
                    move(dir, player, gameMap); // player moves
                } catch (IllegalArgumentException e) {
                    setExit(true);
                    Action.gameWon = false;
                    break;
                }

                boolean exit = checkBoxObject(takenCoordinates, player);
                setExit(exit);

                if (isExit()) {
                    break;
                }
            }
        }

        private static void move(Directions direction, MapObject object, GridPane gameMap) throws IllegalArgumentException{

            int newCoordinate = 0;

            switch (direction) {
                case UP:
                    newCoordinate = object.getPosY();
                    newCoordinate -= 1;
                    object.setPosY(newCoordinate);
                    break;

                case LEFT:
                    newCoordinate = object.getPosX();
                    newCoordinate -= 1;
                    object.setPosX(newCoordinate);
                    break;

                case RIGHT:
                    newCoordinate = object.getPosX();
                    newCoordinate += 1;
                    object.setPosX(newCoordinate);
                    break;

                case DOWN:
                    newCoordinate = object.getPosY();
                    newCoordinate += 1;
                    object.setPosY(newCoordinate);
                    break;
            }

            cordsCheck(newCoordinate);

            int steps = getSteps();
            steps += 1;
            setSteps(steps);

            gameMap.setRowIndex(object.getImgView(), object.getPosY());
            gameMap.setColumnIndex(object.getImgView(), object.getPosX());
        }

        private static void cordsCheck(int cordToCheck) throws IllegalArgumentException{
            final int upperBorder = GameMap.getMaxHeight();
            final int lowerBorder = 0;

            if (cordToCheck > upperBorder || cordToCheck < lowerBorder) {
                throw new IllegalArgumentException();
            }
        }

        private static boolean checkBoxObject(MapObject[][] takenCoordinates, Player player) {

            boolean exit = false;

            final int playerPosX = player.getPosX();
            final int playerPosY = player.getPosY();

            if (takenCoordinates[playerPosY][playerPosX] != null) {
                ActiveObject object = (ActiveObject) takenCoordinates[playerPosY][playerPosX];
                exit = object.performAction();
            }

            return exit;
        }

        public static int getScore() {
            return score;
        }

        public static void setScore(int score) {
            Action.score = score;
        }

        public static int getSteps() {
            return steps;
        }

        public static void setSteps(int steps) {
            Action.steps = steps;
        }

        public static boolean isGameWon() {
            return gameWon;
        }

        public static void setGameWon(boolean gameWon) {
            Action.gameWon = gameWon;
        }

        public static boolean isExit() {
            return exit;
        }

        public static void setExit(boolean exit) {
            Action.exit = exit;
        }
    }


}
