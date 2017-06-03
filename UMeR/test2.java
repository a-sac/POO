import java.util.*;
import java.io.Serializable;

public class Test2 implements Serializable
{
  public Test2(UMeR taxiCompany){
    taxiCompany.setNDrivers(3);
    taxiCompany.setNVehicles(3);

    Driver driver1, driver2, driver3;
    driver1 = new Driver("driver13@email.com", "password1", "Esteves", "Rua do Calcanhar de Aquiles nº1024", "22-07-83", 70, 99, 86745);
    driver2 = new Driver("driver14@email.com", "password2", "Messias Pulga", "Rua do Nestor nº98", "27-02-87", 78, 100, 12333);
    driver3 = new Driver("driver15@email.com", "password3", "Tiago Atletico", "Rua do Jesus nº89", "26-03-88", 62, 56, 74764);

    Client client1, client2, client3;
    client1 = new Client("client1@email.com", "pass1", "Bruno Marrocos", "Rua Casillas nº21", "01-02-91", 400);
    client2 = new Client("client2@email.com", "pass2", "Sara de Carvalho", "Rua Buffon nº345", "07-06-92", 40);
    client3 = new Client("client3@email.com", "pass3", "Carolina Oliveira", "Rua Marcelo nº768", "08-05-93", 320);

    Car car1;
    Van van1;
    MotorBike moto1;
    car1 = new Car(72, 1.4, "02-XX-97");
    van1 = new Van(47, 1.3, "87-JHY-32");
    moto1 = new MotorBike(81, 0.5, "02-NH-52");

    try{
      taxiCompany.addVehicle(car1);
      taxiCompany.addVehicle(van1);
      taxiCompany.addVehicle(moto1);
    } catch(VehicleExistsException e){
      System.out.println("Erro loading vehicles");
    }

    try{
      taxiCompany.addClient(client1);
      taxiCompany.addClient(client2);
      taxiCompany.addClient(client3);
    } catch (UserExistsException a) {
      System.out.println("Erro loading clients");
    }

    try{
      taxiCompany.addDriver(driver1);
      taxiCompany.addDriver(driver2);
      taxiCompany.addDriver(driver3);
    } catch (UserExistsException i){
      System.out.println("Erro loading drivers");
    }

    taxiCompany.startDay(driver1);
    taxiCompany.startDay(driver2);
    taxiCompany.startDay(driver3);
  }
}
