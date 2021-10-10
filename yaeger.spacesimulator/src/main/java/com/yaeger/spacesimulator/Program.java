package com.yaeger.spacesimulator;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.yaeger.spacesimulator.scenes.SimulationScene;

public class Program extends YaegerGame {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void setupGame() {
		setGameTitle("Gravity simulator");
		setSize(new Size(getScreenSize().getWidth(), getScreenSize().getHeight() - 70));
	}

	@Override
	public void setupScenes() {
		addScene(0, new SimulationScene());
	}

	private Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

}
