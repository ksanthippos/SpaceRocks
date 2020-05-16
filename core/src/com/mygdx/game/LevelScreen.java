package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.compression.lzma.Base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelScreen extends BaseScreen {

    private SpaceShip spaceship;
    private boolean gameOver;

    public void initialize() {

        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new SpaceShip(400, 300, mainStage);

        new Rock(600, 500, mainStage);
        new Rock(600, 300, mainStage);
        new Rock(600, 100, mainStage);
        new Rock(400, 100, mainStage);
        new Rock(400, 500, mainStage);
        new Rock(200, 300, mainStage);
        new Rock(200, 500, mainStage);
        new Rock(200, 100, mainStage);

        gameOver = false;

    }

    @Override
    public void update(float dt) {

        for (BaseActor rockActor: BaseActor.getList(mainStage, Rock.class.getCanonicalName())) {

            if (rockActor.overlaps(spaceship)) {
                if (spaceship.shieldPower <= 0) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);

                    BaseActor messageLose = new BaseActor(0, 0, uiStage);
                    messageLose.loadTexture("message-lose.png");
                    messageLose.centerAtPosition(400, 300);
                    messageLose.setOpacity(0);
                    messageLose.addAction(Actions.fadeIn(1));
                    gameOver = true;

                }
                else {
                    spaceship.shieldPower -= 34;
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    rockActor.remove();
                }
            }

            for (BaseActor laserActor: BaseActor.getList(mainStage, Laser.class.getCanonicalName())) {
                if (laserActor.overlaps(rockActor)) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    rockActor.remove();
                }
            }
        }

        if (!gameOver && BaseActor.count(mainStage, Rock.class.getCanonicalName()) == 0) {
            BaseActor messageWin = new BaseActor(0, 0, uiStage);
            messageWin.loadTexture("message-win.png");
            messageWin.centerAtPosition(400, 300);
            messageWin.setOpacity(0);
            messageWin.addAction(Actions.fadeIn(1));
        }
    }

    @Override
    public void dispose() {
        super.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.X)
            spaceship.warp();
        if (keycode == Input.Keys.SPACE)
            spaceship.shoot();
        if (keycode == Input.Keys.ESCAPE)
            System.exit(0);

        return false;
    }

}
