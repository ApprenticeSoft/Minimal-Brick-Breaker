package com.minimal.brick.breaker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.minimal.brick.breaker.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Minimal Brick Breaker";
	    config.width = 480;
	    config.height = 800;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
