package model.observers;

public interface Observer<T extends IObservable<T>> {
	public void update(T observable);
}
