package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.SpaceRocks;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Game myGame = new SpaceGame();
		LwjglApplication launcher = new LwjglApplication(myGame, "Space Rocks", 800, 600);

	}
}
