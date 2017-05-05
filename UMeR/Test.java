
/**
 * Write a description of class Test here.
 *
 * @author jhbb
 * @version 03/05
 */
public class Test
{
  public static void main(){
    UMeR test = new UMeR();
    Driver testDriver = new Driver("email@email.com", "1234password", "Adolphe", "Rua", "11-11-11", 100, 100, 0);
    Client testClient = new Client(new Point2D(), new Point2D(5,9), "emailCliente", "pwd cliente", "nomecliente", "ruacliente", "clienteBIRTHDAY", 19);
    Car testCar = new Car(70, 1, "XX-XX-XX");

    test.addVehicle(testCar);
    test.addDriver(testDriver);
    test.startDay();
    Taxi testTaxi = test.getClosestTaxi();
    testClient.callTaxi(testTaxi);
    testTaxi.goToNextClient();
    testTaxi.pickUpClient();
    testTaxi.rideStart();
    testTaxi.rideEnd();
    System.out.println(testClient.toString());
    testClient.printHistory();
  }
}
