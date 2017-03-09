package model.observers;

import java.util.ArrayList;

public class Observable<T extends IObservable<T>> implements IObservable<T>{
	private ArrayList<Observer<T>> observers=new ArrayList<Observer<T>>();
	
	@Override
	public void addObserver(Observer<T> observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<T> observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(Observer<T> observer) {
		observer.update((T) this);
	}

	@Override
	public void notifyObservers() {
		for(Observer<T> observer: observers)
		{
			observer.update((T) this);
		}
	}
}
