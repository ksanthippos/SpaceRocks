package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SpaceShip extends BaseActor {

    private Thrusters thrusters;
    private Shield shield;
    private int shieldPower;

    public SpaceShip(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("spaceship.png");
        setBoundaryPolygon(8);
        setAcceleration(200);
        setDeceleration(10);
        setMaxSpeed(200);   // orig value 100

        // thrusters
        thrusters = new Thrusters(0, 0, s);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(), getHeight() / 2  - thrusters.getHeight() / 2);

        // shields
        shield = new Shield(0, 0, s);
        addActor(shield);
        shield.centerAtPosition(getWidth() / 2, getHeight() / 2);
        shieldPower = 100;
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        shield.setOpacity(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);

        float degreesPerSecond = 180;   // orig value 120
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            rotateBy(degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rotateBy(-degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            accelerateAtAngle(getRotation());
            thrusters.setVisible(true);
        }
        else {
            thrusters.setVisible(false);
        }

        applyPhysics(dt);
        wrapAroundWorld();
        
    }

    public void warp() {
        if (getStage() == null) // in case if spaceship is removed from stage (= destroyed)
            return;

        Warp warp1 = new Warp(0, 0, this.getStage());
        warp1.centerAtActor(this);
        centerAtPosition(MathUtils.random(800), MathUtils.random(600));
        Warp warp2 = new Warp(0, 0, this.getStage());
        warp2.centerAtActor(this);

    }



}
