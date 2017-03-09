package model.observers;

public interface IObservable<T extends IObservable<T>> {
	public void addObserver(Observer<T> observer);
	
	public void removeObserver(Observer<T> observer);
	
	public void notifyObserver(Observer<T> observer);
	
	public void notifyObservers();
}
