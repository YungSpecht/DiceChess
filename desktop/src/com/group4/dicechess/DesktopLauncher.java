package com.group4.dicechess;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.group4.dicechess.DiceChess;
import com.group4.dicechess.GUI.DiceChessGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(900, 600);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("Dice Chess");
		new Lwjgl3Application(new DiceChessGame(), config);
	}
}
