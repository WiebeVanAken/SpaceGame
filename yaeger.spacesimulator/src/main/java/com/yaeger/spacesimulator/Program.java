package com.yaeger.spacesimulator;

import com.github.hanyaeger.api.YaegerGame;
import com.yaeger.spacesimulator.scenes.SimulationScene;

public class Program extends YaegerGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);
	}
	
	@Override
	public void setupGame() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setupScenes() {
		// TODO Auto-generated method stub
		this.addScene(0, new SimulationScene());
	}

}
