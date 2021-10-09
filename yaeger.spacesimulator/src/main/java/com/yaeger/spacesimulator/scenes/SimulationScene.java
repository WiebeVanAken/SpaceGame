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

public class SimulationScene extends DynamicScene implements UpdateExposer, MouseButtonPressedListener, MouseButtonReleasedListener, MouseMovedWhileDraggingListener {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();
	
	private ArrayList<SimulationObject> objects = new ArrayList<SimulationObject>();
	private ObjectPlacementData data = new ObjectPlacementData();
	
	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() { 
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
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		// TODO Auto-generated method stub
		System.out.println(String.format("CLICKED %s - %f, %f", button.name(), coordinate2d.getX(), coordinate2d.getY()));
	}
	
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		// TODO Auto-generated method stub3
		Point2D dir = data.getDirection();
		double angleRad = Math.atan2(dir.getY(),  dir.getX());
		double angleDeg = angleRad * 180 / Math.PI + 90;
		
		if(dir.getX() < 0 && dir.getY() < 0) {
			angleDeg += 360;
		}
		
		//System.out.println(String.format("RELEASED %s - %f, %f", button.name(), coordinate2d.getX(), coordinate2d.getY()));
		System.out.println(String.format("DIFF %f, %f", dir.getX(), dir.getY()));
		System.out.println(String.format("ANGLE %f", angleDeg));
		
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