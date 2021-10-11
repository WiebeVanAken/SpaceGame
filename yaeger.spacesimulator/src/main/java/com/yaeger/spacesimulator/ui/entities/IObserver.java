package com.yaeger.spacesimulator.ui.entities;

public interface IObserver<T> {
	void update(ISubject<T> subject, T data);
}
