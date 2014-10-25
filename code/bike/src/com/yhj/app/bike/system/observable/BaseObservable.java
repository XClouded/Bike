package com.yhj.app.bike.system.observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yhj.app.bike.model.BaseData;
import com.yhj.app.bike.system.Application;

public abstract class BaseObservable<T extends BaseData, O> {
	protected List<O> observers = new ArrayList<O>();
	protected T data;
			
	public void registerObserver(O observer) {
		// TODO Auto-generated method stub
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void removeObserver(O observer) {
		// TODO Auto-generated method stub
		int index = observers.indexOf(observer);
		if(index >= 0) {
			observers.remove(index);
		}
	}
	
	public void notifyObservers() {
		// TODO Auto-generated method stub
		Application.getInstance().runOnUIThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Iterator<O> observerIt = observers.iterator();
				while(observerIt.hasNext()) {
					O observer = observerIt.next();
					update(observer, data);
				}
			}
		});
		
	}
	
	protected abstract void update(O o, T t);

	public synchronized T getData() {
		return data;
	}

	public synchronized void setData(T data) {
		if(data != null) {
			if(this.data == null) {
				this.data = data;
				notifyObservers();
			} else {
				if(!this.data.realEquals(data)) {
					this.data = data;
					notifyObservers();
				}
			}
		}
	}
}
