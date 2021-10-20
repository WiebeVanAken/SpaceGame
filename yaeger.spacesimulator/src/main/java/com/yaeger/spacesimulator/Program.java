package com.yaeger.spacesimulator;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.yaeger.spacesimulator.scenes.SimulationScene;

/**
 * The main class with the {@link #main(String[])} method where the game is
 * launched.
 */
public class Program extends YaegerGame {

	/**
	 * The main method where the game is launched.
	 *
	 * @param args the command line arguments passed to the application. An
	 *             application may get these parameters using the getParameters()
	 *             method.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Used to call the methods responsible for setting up the Yaeger Game. The
	 * following methods are available:
	 * <ul>
	 * <li>setSize(Size)</li>
	 * <li>setGameTitle(String)</li>
	 * <li>setBackgroundAudio(String)</li>
	 * <li>getBackgroundAudioVolume()</li>
	 * </ul>
	 *
	 */
	@Override
	public void setupGame() {
		setGameTitle("Gravity simulator");
		setSize(new Size(getScreenSize().getWidth(), getScreenSize().getHeight() - 70));
	}

	/**
	 * Used to add the instances of YaegerScene that make up the Game.
	 */
	@Override
	public void setupScenes() {
		addScene(0, new SimulationScene());
	}

	/**
	 * Gets the size of the screen. On systems with multiple displays, the primary
	 * display is used.
	 *
	 * @return the size of the screen in pixels.
	 */
	private Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

}
