package BankQueue;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * @author joshuahouse
 * Simulation class that holds the main simulation as well as the methods to process arrival and
 * departure events.
 */
public class Simulation {
	/**
	 * Main method that runs the bank simulation
	 * @param args
	 */
	public static void main(String args[])
	{
		Queue<Event> bankQueue = new LinkedList<Event>(); // Creates the bankQueue
		EventList bankEventList = new EventList(); //Creates the eventList
		Event FirstEvent, newEvent; // Event objects to hold values between lists
		String input; // Variable to hold the line from the file
		int index; // Index variable for substring
		int inputLength; // Variable to hold the length of the input from the file for substring use
		int arrivalTime; // Variable to hold the arival time read from the file
		int transactionLength; // Variable to hold the transaction length read from the file
		int waitTime = 0; // Variable to hold wait time calculated when a departure event is run
		int people = 0; // Variable to hold number of people processed updated when a new arrival is processed
		double averageWaitTime = 0; // Variable that is updated when a departure event is processed using waitTime variable
		
		Scanner fileInput;
		File inFile = new File("assg8_input.txt");
		
		System.out.println("Simulation Begins");
		
		try {
			//Gets the first event and adds it to the list
			fileInput = new Scanner(inFile);
			input = fileInput.nextLine();
			index = input.indexOf(" ");
			inputLength = input.length();
			arrivalTime = Integer.valueOf(input.substring(0, index));
			transactionLength = Integer.valueOf(input.substring(index + 1, inputLength));
			FirstEvent = new Event(arrivalTime, transactionLength);
			bankEventList.insert(FirstEvent);
			
			//Runs continuously until the bank list is empty. goes through and processes each arrival and departure event
			while (!bankEventList.isEmpty()) {
				newEvent = bankEventList.retrieve(0);
				//Processes arrival event
				if(newEvent.isArrival())
				{
					System.out.println("Processing an arrival event at time:\t" + newEvent.getTime());
					processArrival(newEvent, fileInput, bankEventList, bankQueue);
					people++;
				}
				//Processes departure event
				else
				{
					System.out.println("Processing a departure event at time:\t" + newEvent.getTime());
					processDeparture(newEvent, bankEventList, bankQueue);
					// Calculates average wait time by taking the departure time of the previous arrival event and 
					// subtracting the arrival time of the next event in the queue. It uses this wait time to update
					// averageWaitTime
					if(!bankQueue.isEmpty())
					{
						waitTime = newEvent.getTime() - bankQueue.peek().getTime();
						averageWaitTime = (averageWaitTime + waitTime) / 2;
					}
				}
			}
			fileInput.close();
			System.out.println("Simulation Ends");
			//Prints Final Statistics
			System.out.println("\nFinal Statistics:");
			System.out.println("Total number of people processed:\t" + people);
			System.out.println("Average of time spent waiting:\t\t" + String.format("%.1f",averageWaitTime));
			
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	/**
	 * Processes an arrival event by editing the queue and event list removing the inputted arival event
	 * from the list and adding it to the queue. If it is the first event in the queue it adds a departure
	 * event. If there are more events to read from the file, it adds the next event to the list.
	 * @param arrivalEvent
	 * @param fileInput
	 * @param list
	 * @param queue
	 */
	public static void processArrival(Event arrivalEvent, Scanner fileInput, EventList list, Queue<Event> queue) {
		boolean atFront;
		Event newEvent, departureEvent;
		String input, temp;
		int index, arrivalTime, transactionLength;
		
		atFront = queue.isEmpty();
		queue.add(arrivalEvent);
		list.remove();
		
		if(atFront)
		{
			departureEvent = new Event(arrivalEvent.getTime() + arrivalEvent.getDuration());
			list.insert(departureEvent);
		}
		
		if (fileInput.hasNextLine()) 
		{
			input = fileInput.nextLine();
			index = input.indexOf(" ");
			arrivalTime = Integer.valueOf(input.substring(0, index));
			temp = input.substring(index + 1);
			transactionLength = Integer.valueOf(temp);
			newEvent = new Event(arrivalTime, transactionLength);
			list.insert(newEvent);
		}

	}
	/**
	 * Processes a departure event by removing the departure event from the list and the arrival event from
	 * the queue. If the queue is not empty, it adds the next departure event by adding an event at the time
	 * of the arrival event + the duration of the next arrival event in queue.
	 * @param arrivalEvent
	 * @param list
	 * @param queue
	 * @param waitTime
	 */
	public static void processDeparture(Event arrivalEvent, EventList list, Queue<Event> queue) {
		queue.remove();
		list.remove();
		if(!queue.isEmpty())
		{
			list.insert(new Event(arrivalEvent.getTime() + queue.peek().getDuration()));
		}
	}
}
