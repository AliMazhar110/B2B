package sample;

public class Flights {
	private static String airline;
	private static String flightNo;
	private static String source;
	private static String destination;
	private static String departureTime;
	private static String arrivalTime;
	private static String duration;
	private static String price;

	public Flights(String airline, String flightNo, String source,
		String destination, String departureTime, String arrivalTime,
		String duration, String price) {
		this.airline = airline;
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.price = price;
	}

	public String getAirline() { return this.airline; }
	public String getFlightNo() { return this.flightNo; }
	public String getSource() { return this.source; }
	public String getDestination() { return this.destination; }
	public String getDepartureTime() { return this.departureTime; }
	public String getArrivalTime() { return this.arrivalTime; }
	public String getDuration() { return this.duration; }
	public String getPrice() { return this.price; }
}