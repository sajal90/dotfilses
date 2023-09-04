package asteroids;

import javafx.scene.shape.Polygon;

public class Projectiles extends Character{

    public Projectiles(int x,int y){
        super(new Polygon(2,-2,2,2,-2,2,-2,-2),x,y);
    }

    @Override
    public void move() {
        super.getCharacter().setTranslateX(super.getCharacter().getTranslateX() + super.getMovement().getX());
        super.getCharacter().setTranslateY(super.getCharacter().getTranslateY() + super.getMovement().getY());
    }
}
