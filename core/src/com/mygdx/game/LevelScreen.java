package com.mygdx.game;

public class LevelScreen extends BaseScreen {

    private SpaceShip spaceship;

    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new SpaceShip(400, 300, mainStage);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
