package animalsObserved;

import java.util.*;
import java.util.Observable;
import graphics.ZooPanel;

public class ZooObserver extends Thread implements Observer {

	public ZooObserver(){
		super();
	}

	public void run() {
		try {
			while(true) {

				synchronized(this) {

					wait();
				}  
				ZooPanel.getInstance().animalEatAnimal();			
				ZooPanel.getInstance().repaint();
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		synchronized(this)
		{			
			notify();
		}
	}

}