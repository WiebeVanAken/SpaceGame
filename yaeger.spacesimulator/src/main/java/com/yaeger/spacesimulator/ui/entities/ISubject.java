package com.yaeger.spacesimulator.ui.entities;

public interface ISubject<T> {
	void observe(IObserver<T> observer);

	void unobserve(IObserver<T> observer);

	void notifyObservers();
}
