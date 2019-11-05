package sample.scenes.gameview;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.ConfirmBox;
import sample.InfoBox;
import sample.Main;
import sample.scenes.GameView;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.gameview.elements.GameMap;
import sample.scenes.gameview.elements.Instructions;
import sample.scenes.gameview.elements.ListOfInstructions;
import sample.scenes.gameview.objects.ActiveObject;
import sample.scenes.gameview.objects.Finish;
import sample.scenes.gameview.objects.MapObject;
import sample.scenes.gameview.objects.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameFlow {
    public static synchronized void runFlow() {
        Action myAction = new Action();

        Thread t1 = new Thread(myAction, "T1");
        t1.setDaemon(true);
        t1.start();
    }

    public static class Action implements Runnable {
        private volatile boolean exit;

        private static int score;
        private static int steps;

        Player player = GameMap.getPlayerObject();
        GridPane gameMap = GameView.getGameMap();
        List<Directions> commands = Instructions.getCommands();
        MapObject[][] takenCoordinates = GameMap.getTakenCoordinates();

        public static boolean gameWon;

        public void run() {
            exit = false;
            gameWon = false;

            setScore(0);
            setSteps(0);

            while (!exit) {
                for (Directions dir : commands) {
                    try {
                        Thread.sleep(500); // Wait for 1 sec before updating the color
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    move(dir, player, gameMap); // player moves
                    System.out.println(getSteps());
                    exit = checkBoxObject(takenCoordinates, player, gameWon);
                    System.out.println(getScore());
                    if (exit) {
                        break;
                    }
                }
                System.out.println("Final score: " + getScore());
                System.out.println("Final steps: " + getSteps());
                System.out.println("Final results: " + (getScore() - getSteps()));
                // when all of the moves are performed and no win is detected
                if (!exit) {
                    Platform.runLater(() -> {
                        InfoBox.display("Loose", "You've lost!");
                    });
                    break;
                }
                if (exit) {
                    if(gameWon) {
                        Platform.runLater(() -> {
                            InfoBox.display("Eluwa", "You've won!");
                        });
                    }
                    else if(!gameWon) {
                        Platform.runLater(() -> {
                            InfoBox.display("Loose", "Przegrana!");
                        });
                    }

                }
            }

        }

        private static void move(Directions direction, MapObject object, GridPane gameMap) {

            switch (direction) {
                case UP:
                    int y = object.getPosY();
                    y -= 1;
                    object.setPosY(y);
                    break;

                case LEFT:
                    int x = object.getPosX();
                    x -= 1;
                    object.setPosX(x);
                    break;

                case RIGHT:
                    int x2 = object.getPosX();
                    x2 += 1;
                    object.setPosX(x2);
                    break;

                case DOWN:
                    int y2 = object.getPosY();
                    y2 += 1;
                    object.setPosY(y2);
                    break;
            }

            int steps = getSteps();
            steps += 1;
            setSteps(steps);

            gameMap.setRowIndex(object.getImgView(), object.getPosY());
            gameMap.setColumnIndex(object.getImgView(), object.getPosX());
        }

        private static boolean checkBoxObject(MapObject[][] takenCoordinates, Player player, boolean gameWon) {

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
    }
}
