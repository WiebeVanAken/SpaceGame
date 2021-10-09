package com.yaeger.spacesimulator.services;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.geometry.Point2D;

public class AngleCalculatorService {
	private static AngleCalculatorService instance;

	private AngleCalculatorService() { }
	
	public double calculateAngle(Coordinate2D dir) {
		Point2D normDir = dir.normalize();
		return (normDir.getX() >= 0 
			? new Point2D(0, 1).angle(normDir)
			: new Point2D(0, -1).angle(normDir) + 180
		);
	}
	
	public static AngleCalculatorService getInstance() {
		if(instance == null) {
			instance = new AngleCalculatorService();
		}
		
		return instance;
	}
}
