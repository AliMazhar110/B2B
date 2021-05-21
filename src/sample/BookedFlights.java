package sample;

public class BookedFlights {
    private String id;
    private String airline;
	private String flightNo;
	private String source;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private String seats;
	private String date;
	private String pnr;

    public BookedFlights(String id, String airline, String flightNo,
                         String pnr, String s, String d, String dep,
						 String arr, String date, String seat) {
		this.id = id;
		this.airline = airline;
		this.flightNo = flightNo;
		this.source = s;
		this.destination = d;
		this.departureTime = dep;
		this.arrivalTime = arr;
		this.pnr = pnr;
		this.date = date;
		this.seats = seat;
    }

	public String getId() { return this.id; }
	public String getAirline() { return this.airline; }
	public String getFlightNo() { return this.flightNo; }
	public String getSource() { return this.source; }
	public String getDestination() { return this.destination; }
	public String getDepartureTime() { return this.departureTime; }
	public String getArrivalTime() { return this.arrivalTime; }
	public String getPNR() { return this.pnr; }
	public String getSeats() { return this.seats; }
	public String getDate() { return this.date; }
}
