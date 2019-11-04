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
import sample.scenes.gameview.objects.Finish;
import sample.scenes.gameview.objects.MapObject;
import sample.scenes.gameview.objects.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameFlow {
    public static synchronized void runFlow() {

        Player player = GameMap.getPlayerObject();
        GridPane gameMap = GameView.gameMap; // TODO -- change to getter
        List<Directions> commands = Instructions.commands; // TODO -- change to getter
        MapObject[][] takenCoordinates = GameMap.getTakenCoordinates();

        Action myAction = new Action();

        Thread t1 = new Thread(myAction, "T1");
        t1.setDaemon(true);
        t1.start();
    }

    static class Action implements Runnable{
        private volatile boolean exit = false;

        Player player = GameMap.getPlayerObject();
        GridPane gameMap = GameView.gameMap; // TODO -- change to getter
        List<Directions> commands = Instructions.commands; // TODO -- change to getter
        MapObject[][] takenCoordinates = GameMap.getTakenCoordinates();

        public void run() {
            Finish finish = GameMap.getFinishObject();

                for (Directions dir : commands) {
                    try {
                        Thread.sleep(500); // Wait for 1 sec before updating the color
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    // TODO -- naprawic ta kaszane
                    move(dir, player, gameMap);
                }




        }

//        private void restartFlow(){
//            AtomicBoolean answer = new AtomicBoolean(false);
//            Platform.runLater(() -> {
//                answer.set(ConfirmBox.display("Exit Confirmation",
//                        "Are you sure you want to exit?"));
//                if(answer.get()) {
//                    ListOfInstructions.label.setText("");
//                    Instructions.commands.clear();
//
//                    Main.game = new GameView().createWindow();
//                    Main.mainWindow.setScene(Main.menu);
//                }
//            });
//        }

//        public void checkWin(){
//            Finish finish = GameMap.getFinishObject();
//            Platform.runLater(() -> {
//                ConfirmBox.display("Loose", "You've lost!");
//                });
//        }

//        public void stop(){
//            exit = true;
//        }
//    }

//    private static Thread threadControl(AtomicBoolean shuttingDown) {
//        Player player = GameMap.getPlayerObject();
//        GridPane gameMap = GameView.gameMap; // TODO -- change to getter
//        List<Directions> commands = Instructions.commands; // TODO -- change to getter
//        MapObject[][] takenCoordinates = GameMap.getTakenCoordinates();
//
//        Thread thread = new Thread(() -> {
//            Finish finish = GameMap.getFinishObject();
//
//            for (Directions dir : commands) {
//                try {
//                    Thread.sleep(500); // Wait for 1 sec before updating the color
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//
//                Platform.runLater(() -> {
//                    move(dir, player, gameMap);
//                    if (player.getPosX() == finish.getPosX() && player.getPosY() == finish.getPosY()) {
//                        shuttingDown.set(true);
//                        System.out.println("wygrana : " + shuttingDown.get());
//                        InfoBox.display("Win", "You've won!");
//                    }
//                });
//            }
//
//            System.out.println("Shutting down po wszystkich ruchach: " + shuttingDown.get());
//            if (!shuttingDown.get()) {
//                Platform.runLater(() -> {
//                    InfoBox.display("Loose", "You've lost!");
//                });
//            }
//        });
//        return thread;
//    }


//    private static synchronized boolean runGame(Directions dir, Player player, GridPane gameMap) {
//
//        Finish finish = GameMap.getFinishObject();
//        AtomicBoolean shuttingDown = new AtomicBoolean(false);

//        try {
//            Thread.sleep(500); // Wait for 1 sec before updating the color
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//
//        Platform.runLater(() -> {
//            move(dir, player, gameMap);
//            if (player.getPosX() == finish.getPosX() && player.getPosY() == finish.getPosY()) {
//                shuttingDown.set(true);
//                System.out.println("wygrana : " + shuttingDown.get());
//                info(shuttingDown);
//            }
//        });
//
//        return shuttingDown.get();
//    }

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

        gameMap.setRowIndex(object.getImgView(), object.getPosY());
        gameMap.setColumnIndex(object.getImgView(), object.getPosX());
    }

    private static int calculateScore(int addedPoints, int points) {
        int point = points;
        point += addedPoints;
        return point;
    }

    private static int checkBonusPoints(List<MapObject> bonusPoints, Player player) {
        int points = 0;

        for (MapObject object : bonusPoints) {
            if (object.getPosX() == player.getPosX() && object.getPosY() == player.getPosY()) {
                System.out.println(" +1 point ");
                points += 5;
            }
        }

        return points;
    }
}
}
