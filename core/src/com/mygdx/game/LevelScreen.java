package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.compression.lzma.Base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelScreen extends BaseScreen {

    private SpaceShip spaceship;
    private List<Rock> rocks;
    private boolean gameOver;

    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new SpaceShip(400, 300, mainStage);

        rocks = new ArrayList<>();

        rocks.add(new Rock(600, 500, mainStage));
        rocks.add(new Rock(600, 300, mainStage));
        rocks.add(new Rock(600, 100, mainStage));
        rocks.add(new Rock(400, 100, mainStage));
        rocks.add(new Rock(400, 500, mainStage));
        rocks.add(new Rock(200, 300, mainStage));
        rocks.add(new Rock(200, 500, mainStage));
        rocks.add(new Rock(200, 100, mainStage));

        gameOver = false;

    }

    @Override
    public void update(float dt) {

        for (BaseActor rockActor: BaseActor.getList(mainStage, "Rock")) {

            if (rockActor.overlaps(spaceship)) {
                if (spaceship.shieldPower <= 0) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);
                }
                else {
                    spaceship.shieldPower -= 34;
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    rockActor.remove();
                }
            }

            for (BaseActor laserActor: BaseActor.getList(mainStage, "Laser")) {
                if (laserActor.overlaps(rockActor)) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    rockActor.remove();
                }
            }
        }


/*        Iterator iterator = rocks.iterator();
        while (iterator.hasNext()) {
            Rock rockActor = (Rock) iterator.next();
            if (rockActor.overlaps(spaceship)) {
                if (spaceship.shieldPower <= 0) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);
                }
                else {
                    spaceship.shieldPower -= 34;
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    iterator.remove();
                    rockActor.setPosition(-1000, -1000);
                }
            }

            Iterator iterator1 = spaceship.getLasers().iterator();
            while (iterator1.hasNext()) {
            BaseActor laserActor = (Laser) iterator1.next();
                if (laserActor.overlaps(rockActor)) {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    iterator.remove();
                    iterator1.remove();
                    laserActor.setPosition(-1000, -1000);
                    rockActor.setPosition(-1000, -1000);
                }
            }
        }*/
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
