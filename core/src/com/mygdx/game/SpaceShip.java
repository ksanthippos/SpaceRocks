package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SpaceShip extends BaseActor {

    public SpaceShip(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("spaceship.png");
        setBoundaryPolygon(8);
        setAcceleration(200);
        setDeceleration(10);
        setMaxSpeed(200);   // orig value 100
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        float degreesPerSecond = 180;   // orig value 120
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            rotateBy(degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rotateBy(-degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            accelerateAtAngle(getRotation());

        applyPhysics(dt);
        wrapAroundWorld();
        
    }



}
