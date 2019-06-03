package org.eok.medicalsupportsystem.model;

import org.eok.medicalsupportsystem.view.Observer;

public class Observable {

	   private java.util.List<Observer> observers;
	   
	   public void attachObserver(Observer observer) {
		   addObservers(observer);
	   }
	   
	   public void detachObserver(Observer observer) {
		   removeObservers(observer);
	   }
	   
	   public void notifyObservers(Object event) {
	      for (Observer observer : getObservers()) {
	    	  observer.update(event);
	      }
	   }
	    
	   public java.util.List<Observer> getObservers() {
	      if (observers == null)
	         observers = new java.util.ArrayList<Observer>();
	      return observers;
	   }
	   
	   public void addObservers(Observer newObserver) {
	      if (newObserver == null)
	         return;
	      if (this.observers == null)
	         this.observers = new java.util.ArrayList<Observer>();
	      if (!this.observers.contains(newObserver))
	         this.observers.add(newObserver);
	   }
	   
	   public void removeObservers(Observer oldObserver) {
	      if (oldObserver == null)
	         return;
	      if (this.observers != null)
	         if (this.observers.contains(oldObserver))
	            this.observers.remove(oldObserver);
	   }
}