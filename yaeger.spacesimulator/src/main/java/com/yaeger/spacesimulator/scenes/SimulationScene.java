package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.github.hanyaeger.api.userinput.MouseMovedWhileDraggingListener;
import com.yaeger.spacesimulator.data.ObjectPlacementData;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.SimulationUpdateService;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene implements UpdateExposer, MouseButtonReleasedListener, MouseMovedWhileDraggingListener {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();
	
	private ArrayList<SimulationObject> objects = new ArrayList<SimulationObject>();
	private ObjectPlacementData data = new ObjectPlacementData();
	
	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		System.out.println("RED");
		objects.add(new Planet(new Coordinate2D(100, 100), new Coordinate2D(0, -1), 1.d, 10.d, 1.d, Color.RED)); //0
		System.out.println("BLUE");
		objects.add(new Planet(new Coordinate2D(100, 100), new Coordinate2D(1, 0), 1.d, 10.d, 1.d, Color.BLUE)); //90
		System.out.println("YELLOW");
		objects.add(new Planet(new Coordinate2D(100, 100), new Coordinate2D(0, 1), 1.d, 10.d, 1.d, Color.YELLOW)); //180
		System.out.println("GREEN");
		objects.add(new Planet(new Coordinate2D(100, 100), new Coordinate2D(-1, 0), 1.d, 10.d, 1.d, Color.GREEN)); //270
		objects.forEach(obj -> { this.addEntity(obj); });
	}
	
	@Override
	public void explicitUpdate(long timestamp) {
		simulationUpdater.updateSimulation(objects);
	}
	
	private void placePlanet(ObjectPlacementData data) {
		Planet planet = new Planet(data.getStartPosition(), data.getDirection(), data.getVolume(), data.getDensity(), data.getVelocity(), data.getColor() );
		this.objects.add(planet);
		this.addEntity(planet);
	}
	
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		if(button.name() == MouseButton.PRIMARY.toString() && data.getPlacing()) {
			placePlanet(data);
			data.reset();
		}
	}
	
	@Override
	public void onMouseMovedWhileDragging(Coordinate2D coordinate2d) {
		if(data.getPlacing()) {
			data.setStopPosition(coordinate2d);
		} else {
			data.setStartPosition(coordinate2d);
			data.setPlacing(true);
		}
	}
}