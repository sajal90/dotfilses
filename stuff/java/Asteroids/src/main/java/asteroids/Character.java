package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public abstract class Character {
    private Polygon character;
    private Point2D movement;
    private boolean alive;

    public Character(Polygon polygon,int x,int y){
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.character.setFill(Color.WHITE);
        this.alive = true;

        this.movement = new Point2D(0,0);
    }


    public Polygon getCharacter() {
        return character;
    }

    public void turnRight(){
        this.character.setRotate(this.character.getRotate()+5);
    }

    public void turnLeft(){
        this.character.setRotate(this.character.getRotate()-5);
    }

    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

        if(this.character.getTranslateX()<0){
            this.character.setTranslateX(character.getTranslateX()+AsteroidsApplication.WIDTH);
        }

        if(this.character.getTranslateX()>AsteroidsApplication.WIDTH){
            this.character.setTranslateX(character.getTranslateX()%AsteroidsApplication.WIDTH);
        }

        if(this.character.getTranslateY()<0){
            this.character.setTranslateY(character.getTranslateY()+AsteroidsApplication.HEIGHT);
        }

        if(this.character.getTranslateY()>AsteroidsApplication.HEIGHT){
            this.character.setTranslateY(character.getTranslateY()%AsteroidsApplication.HEIGHT);
        }
    }

    public void accelerate(){
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX,changeY);
    }

    public boolean collide(Character other){
        Shape CollisionArea = Shape.intersect(this.character,other.getCharacter());
        return CollisionArea.getBoundsInLocal().getWidth() != -1;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }
    public void reset(int x,int y){
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
    }
}
