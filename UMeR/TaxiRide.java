
/**
 * Write a description of class TaxiRide here.
 *
 * @author jhbb
 * @version 02/04
 */
public class TaxiRide{
  private Point2D start;
  private Point2D destination;
  private Vehicle vehicle;
  private double distance;
  private double expectedTime;
  private double actualTime;
  private double price;

  public TaxiRide(Point2D start, Point2D destination, Vehicle vehicle, double distance, double expectedTime, double actualTime, double price){
    this.start = start;
    this.destination = destination;
    this.vehicle = vehicle;
    this.distance = distance;
    this.expectedTime = expectedTime;
    this.actualTime = actualTime;
    this.price = price;
  }

  public TaxiRide(TaxiRide tr){
    this.start = tr.getStart();
    this.destination = tr.getDestination();
    this.vehicle = tr.getVehicle();
    this.distance = tr.getDistance();
    this.expectedTime = tr.getExpectedTime();
    this.actualTime = tr.getActualTime();
    this.price = tr.getPrice();
  }

	public Point2D getStart() {
		return this.start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getDestination() {
		return this.destination;
	}

	public void setDestination(Point2D destination) {
		this.destination = destination;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}


	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getExpectedTime() {
		return this.expectedTime;
	}

	public void setExpectedTime(double expectedTime) {
		this.expectedTime = expectedTime;
	}

	public double getActualTime() {
		return this.actualTime;
	}

	public void setActualTime(double actualTime) {
		this.actualTime = actualTime;
	}

  public double getPrice(){
    return this.price;
  }

  public TaxiRide clone(){
    return new TaxiRide(this);
  }
}
