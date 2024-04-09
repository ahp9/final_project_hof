package hi.verkefni.vinnsla;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers;
    
    public Observable(){
        observers = new ArrayList<Observer>();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        System.out.println("Test");
        System.out.println(observers);
        for(Observer observer: observers){
            observer.update();
        }
    }
}
