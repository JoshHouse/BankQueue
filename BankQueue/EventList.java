package BankQueue;

import java.util.*;
/**
 * @author joshuahouse
 * EventList class initializes an array list of type event and provides various methods to do with
 * that array list
 */
public class EventList {
	private ArrayList<Event> list;
/**
 * Constructor for the event list that initializes the array list
 */
	public EventList() {
		this.list = new ArrayList<Event>();
	}
	/**
	 * Returns a true or false value if the arraylist is empty
	 * @return
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/**
	 * Compare to function considers type so compare to is used to test where to put input on the list
	 * @param input  an event
	 */
	public void insert(Event input) {
		if (list.isEmpty()) {
			list.add(input);
		} else if (list.get(0).compareTo(input) == -1) {
			list.add(input);
		} else if (list.get(0).compareTo(input) == 1) {
			list.add(0, input);
		} else {
			list.add(input);
		}
	}
	/**
	 * Removes the first item on the list
	 */
	public void remove() {
		if(!list.isEmpty()) {
			list.remove(0);
		}
	}
	/**
	 * Retrieves the item from the arraylist at the index inputed
	 * @param index
	 * @return the event at the inputted index or null if there is no item at that index.
	 */
	public Event retrieve(int index) {
		if (!list.isEmpty() && index <= 1) {
			return list.get(index);
		} else {
			return null;
		}
	}
	
}
