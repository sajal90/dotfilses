package asteroids;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class AsteroidsApplication extends Application {
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    public static boolean replay = false;


    public void start (Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        pane.setStyle("-fx-background-color:BLACK");


        Button rep = new Button("Replay?");
        rep.setTranslateX(285);
        rep.setTranslateY(185);

        Text text = new Text(10, 20, "Points:0");
        text.setFill(Color.WHITE);
        pane.getChildren().add(text);




        Scene scene = new Scene(pane);
        Map<KeyCode, Boolean> keyPressed = new HashMap<>();
        scene.setOnKeyPressed(event -> {
            keyPressed.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event -> {
            keyPressed.put(event.getCode(), Boolean.FALSE);
        });

        Ship ship = new Ship(WIDTH / 2, HEIGHT / 2);
        AtomicInteger points = new AtomicInteger();

        List<Asteroid> asteroids = new ArrayList<>();
        List<Projectiles> projectiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
            asteroids.add(asteroid);
        }

        asteroids.forEach(asteroid1 -> pane.getChildren().add(asteroid1.getCharacter()));
        pane.getChildren().add(ship.getCharacter());


        new AnimationTimer() {

            @Override
            public void handle(long l) {
                if (keyPressed.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (keyPressed.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (keyPressed.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                if (keyPressed.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {
                    Projectiles projectile = new Projectiles((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate() + (7 * projectiles.size()));

                    projectiles.add(projectile);

                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));

                    pane.getChildren().add(projectile.getCharacter());
                }

                projectiles.forEach(projectiles1 -> {
                    if (projectiles1.getCharacter().getTranslateX() +projectiles1.getMovement().getX() <=0 || projectiles1.getCharacter().getTranslateX()+projectiles1.getMovement().getX() >= AsteroidsApplication.WIDTH
                            || projectiles1.getCharacter().getTranslateY()+projectiles1.getMovement().getY() <= 0 || projectiles1.getCharacter().getTranslateY()+projectiles1.getMovement().getY() >= AsteroidsApplication.HEIGHT) {
                        pane.getChildren().remove(projectiles1.getCharacter());
                    }
                });


                if (Math.random() < 0.008) {
                    Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
                    if (!asteroid.collide(ship)) {
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }

                asteroids.forEach(Character::move);
                ship.move();
                projectiles.forEach(Character::move);


                projectiles.forEach(projectiles1 -> {
                    asteroids.forEach(asteroid -> {
                        if (projectiles1.collide(asteroid)) {
                            asteroid.setAlive(false);
                            projectiles1.setAlive(false);
                        }
                    });

                    if (!projectiles1.isAlive()) {
                        text.setFill(Color.WHITE);
                        text.setText("Points: " + points.addAndGet(100));

                    }
                });

                projectiles.stream()
                        .filter(projectiles1 -> !projectiles1.isAlive())
                        .forEach(projectiles1 -> pane.getChildren().remove(projectiles1.getCharacter()));


                projectiles.removeAll(projectiles.stream()
                        .filter(projectiles1 -> (projectiles1.getCharacter().getTranslateX() < 0 || projectiles1.getCharacter().getTranslateX() > AsteroidsApplication.WIDTH
                                || projectiles1.getCharacter().getTranslateY() < 0 || projectiles1.getCharacter().getTranslateY() > AsteroidsApplication.HEIGHT))
                        .collect(Collectors.toList()));





                projectiles.removeAll(projectiles.stream()
                        .filter(projectiles1 -> !projectiles1.isAlive())
                        .collect(Collectors.toList()));

                asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .forEach(asteroid -> pane.getChildren().remove(asteroid.getCharacter()));

                asteroids.removeAll(asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .collect(Collectors.toList()));

                asteroids.forEach(asteroid -> {
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });

            }
        }.start();

        stage.setScene(scene);
        stage.setTitle("Asteroids!");
        stage.show();
    }


    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }


}