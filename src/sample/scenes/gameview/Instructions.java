package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Objects.Grass;
import sample.Objects.Player;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Instructions implements SceneElement {
    private List<Directions> commands = new ArrayList<>();

    @Override
    public Pane createWindow() {
        Button top = new Button("UP");
        top.setMinWidth(100);
        top.setOnAction(e -> {
            commands.add(Directions.UP);
            System.out.println("Added top");
        });

        Button left = new Button("LEFT");
        left.setMinWidth(100);
        left.setOnAction(e -> {
            commands.add(Directions.LEFT);
            System.out.println("Added left");
        });

        Button right = new Button("RIGHT");
        right.setMinWidth(100);
        right.setOnAction(e -> {
            commands.add(Directions.RIGHT);
            System.out.println("Added right");
        });

        Button down = new Button("DOWN");
        down.setMinWidth(100);
        down.setOnAction(e -> {
            commands.add(Directions.DOWN);
            System.out.println("Added down");
        });

        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            Player player = GameMap.playerObject;
            GridPane gameMap = GameView.gameMap;


            try {
                gameMap.getChildren().remove(player.getImgView());
                TimeUnit.SECONDS.sleep(1);
//                gameMap.add(player.getImgView(), player.getPosX(), player.getPosY());

                // delay 0.5 second
                //TimeUnit.MICROSECONDS.sleep(500);

                // delay 1 minute
                //TimeUnit.MINUTES.sleep(1);

            } catch (InterruptedException ex) {
                System.err.format("IOException: %s%n", ex);
            }


//            GridPane score = GameView.score;
            //                gameMap.getChildren().remove(player.getImgView());


//            for (Directions dir : commands) {
//                System.out.println("X: " + player.getPosX());
//                System.out.println("Y: " + player.getPosY());
//
//                switch (dir) {
//                    case UP:
//                        int y = player.getPosY();
//                        y -= 1;
//                        player.setPosY(y);
//                        break;
//                    case LEFT:
//                        int x = player.getPosX();
//                        x -= 1;
//                        player.setPosX(x);
//                        break;
//                    case RIGHT:
//                        int x2 = player.getPosX();
//                        x2 += 1;
//                        player.setPosX(x2);
//                        break;
//                    case DOWN:
//                        int y2 = player.getPosY();
//                        y2 += 1;
//                        player.setPosY(y2);
//                        break;
//                }
//                for(Grass object : GameMap.list) {
//                    if (object.getPosX() == player.getPosX() && object.getPosY() == player.getPosY()) {
//                        System.out.println("I'm on the grass");
//                    }
//                }

//                try {
//                    Thread.sleep(1000);
//                    System.out.println("I slept");//1000 milliseconds is one second.
//
//                } catch(InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }
//
//                Score.score++;
//                Score.label.setText("Score: " + Score.score);
//
//                gameMap.getChildren().remove(player.getImgView());
//                gameMap.add(player.getImgView(), player.getPosX(), player.getPosY());
//
//
//            }
        });

        Label label = new Label("Available instructions");

        GridPane instructions = new GridPane();
        instructions.setVgap(10);
        instructions.setAlignment(Pos.CENTER);
        instructions.setPrefWidth(getWidth());
        instructions.add(label, 0, 0);
        instructions.add(top, 0, 1);
        instructions.add(left, 0, 2);
        instructions.add(right, 0, 3);
        instructions.add(down, 0, 4);
        instructions.add(start, 0, 5);

        return instructions;
    }

    @Override
    public int getWidth() {
        return 200;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}
