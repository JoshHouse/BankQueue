package BankQueue;
/**
 * @author joshuahouse
 * Event Class that holds arrival and departure events
 */
public class Event {
	private char type;
	private int time;
	private int duration;
	/**
	 * Constructor for arrival event that takes in the time and duration
	 * @param time
	 * @param duration
	 */
	public Event(int time, int duration) {
		this.type = 'A';
		this.time = time;
		this.duration = duration;
	}
/**
 * Constructor for departure event that takes the time of departure
 * @param time
 */
	public Event(int time) {
		this.type = 'D';
		this.time = time;
	}
/**
 * returns the type as a char either A or D for arrival or departure
 * @return 'A'
 * @return 'D'
 */
	public char getType() {
		return type;
	}
/**
 * returns the time of the arrival or departure
 * @return time
 */
	public int getTime() {
		return time;
	}
/**
 * returns the duration of the transaction
 * @return duration
 */
	public int getDuration() {
		return duration;
	}
	/**
	 * returns event object as a string with spaces in between
	 * @return String of type and time, or type, time, and duration
	 */
	public String toString() {
		if(type == 'A')
		{
			return type + " " + time + " " + duration;
		} else {
			return type + " " + time;
		}
	}
	/**
	 * returns a positive int if this time is greater than the inputted events time
	 * and a negative integer if this time is less than the inputted time. If the times
	 * are equivalent, it returns a negative integer if this type is an arrival and the 
	 * Inputed type is a departure. It returns a positive integer if this type is a departure
	 * and the inputed type is an arrival. it returns 0 if the types and times are equivalent.
	 * @param input
	 * @return 1
	 * @return -1
	 * @return 0
	 */
	public int compareTo(Event input)
	{
		if (this.time > input.getTime())
		{
			return 1;
		} else if (this.time < input.getTime()) {
			return -1;
		} else {
			if (this.type == 'A' && input.getType() == 'D')
			{
				return -1;
			} else if (this.type == 'D' && input.getType() == 'A') {
				return 1;
			} else {
				return 0;
			}
		}
	}
	/**
	 * returns a true or false value if this type is an 'A'
	 * @return true
	 * @return false
	 */
	public boolean isArrival() {
		return this.type == 'A';
	}
	/**
	 * returns a true or false value if this type is an 'D'
	 * @return true
	 * @return false
	 */
	public boolean isDeparture() {
		return this.type == 'D';
	}
	
}
