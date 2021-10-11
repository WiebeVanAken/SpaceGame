package com.yaeger.spacesimulator;

import com.github.hanyaeger.api.Timer;
import com.yaeger.spacesimulator.entities.SimulationObject;

public class OutOfBoundsTimer extends Timer {
	private SimulationObject object;
	private int counter;
	boolean running;
	
	public OutOfBoundsTimer(SimulationObject object, long intervalInMs) {
		super(intervalInMs);
		this.object = object;
		this.counter = 0;
		this.running = false;
	}
	
	public void start() {
		if(!this.running) {
			this.running = true;
			this.counter = 0;
			this.resume();
		}
	}
	
	public void stop() {
		this.running = false;
		this.counter = 0;
		this.pause();
	}
	
	@Override
	public void onAnimationUpdate(long timestamp) {
		this.counter++;
		
		if(counter >= 5)
			this.object.setShouldBeDeleted(true);
	}
}