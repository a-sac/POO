import java.util.*;
import java.io.Serializable;

public class Test implements Serializable
{
  public Test(UMeR taxiCompany){
    taxiCompany.setNDrivers(15);
    taxiCompany.setNVehicles(15);

    Driver driver1, driver2, driver3, driver4, driver5, driver6, driver7, driver8, driver9, driver10, driver11, driver12, driver13, driver14, driver15;
    driver1 = new Driver("driver1@email.com", "password1", "Abel Joao", "Rua do Canudo nº31", "31-09-69", 70, 80, 23456);
    driver2 = new Driver("driver2@email.com", "password2", "Rui Joaquim", "Rua do Camoes nº39", "02-11-80", 90, 90, 15698);
    driver3 = new Driver("driver3@email.com", "password3", "José Esquina", "Rua do Vasco da Gama nº61", "04-02-71", 60, 95, 34565);
    driver4 = new Driver("driver4@email.com", "password4", "Adelino da Silva", "Rua do Pedro Alvates Cabral nº98", "21-10-60", 70, 70, 67908);
    driver5 = new Driver("driver5@email.com", "password5", "Pedro Carrasco", "Rua do Afonso Henriques nº311", "31-05-91", 70, 88, 20896);
    driver6 = new Driver("driver6@email.com", "password6", "Cristiano Ronaldo", "Rua do Salazar nº321", "10-02-84", 100, 95, 54356);
    driver7 = new Driver("driver7@email.com", "password7", "Sandro Corte", "Rua do Marcelo Rebelo de Sousa nº4", "31-09-89", 67, 72, 46754);
    driver8 = new Driver("driver8@email.com", "password8", "Carlos Limede", "Rua do Marques nº 36", "30-11-79", 82, 91, 13455);
    driver9 = new Driver("driver9@email.com", "password9", "Manuel Qalhó", "Rua dos Aliados nº28", "13-08-79", 91, 74, 6546);
    driver10 = new Driver("driver10@email.com", "password10", "Vitor Queirós", "Rua dos Campeoes nº18", "17-08-69", 74, 81, 2456);
    driver11 = new Driver("driver11@email.com", "password11", "Fernando Santos", "Rua D. Dinis nº41", "15-09-89", 51, 55, 56454);
    driver12 = new Driver("driver12@email.com", "password12", "Eder Deus", "Rua do Hercules nº 670", "31-11-65", 89, 87, 76865);
    driver13 = new Driver("driver13@email.com", "password13", "Esteves", "Rua do Calcanhar de Aquiles nº1024", "22-07-83", 70, 99, 86745);
    driver14 = new Driver("driver14@email.com", "password14", "Messias Pulga", "Rua do Nestor nº98", "27-02-87", 78, 100, 12333);
    driver15 = new Driver("driver15@email.com", "password15", "Tiago Atletico", "Rua do Jesus nº89", "26-03-88", 62, 56, 74764);

    Client client1, client2, client3, client4, client5, client6, client7, client8, client9, client10, client11, client12, client13, client14, client15, client16, client17, client18, client19, client20;
    client1 = new Client("client1@email.com", "pass1", "Bruno Marrocos", "Rua Casillas nº21", "01-02-91", 400);
    client2 = new Client("client2@email.com", "pass2", "Sara de Carvalho", "Rua Buffon nº345", "07-06-92", 40);
    client3 = new Client("client3@email.com", "pass3", "Carolina Oliveira", "Rua Marcelo nº768", "08-05-93", 320);
    client4 = new Client("client4@email.com", "pass4", "Vanessa Do Canto", "Rua Modric nº45", "31-09-86", 420);
    client5 = new Client("client5@email.com", "pass5", "Gonçalo Guedes Paciencia", "Rua Busquets nº324", "28-02-89", 696);
    client6 = new Client("client6@email.com", "pass6", "Patricia Barros", "Rua Iniesta nº999", "17-09-79", 619);
    client7 = new Client("client7@email.com", "pass7", "Arnaldo Lopes", "Rua Suarez nº546", "01-05-78", 69);
    client8 = new Client("client8@email.com", "pass8", "Leandro Coelho", "Rua Benzema nº453", "26-02-92", 443);
    client9 = new Client("client9@email.com", "pass9", "Adriana Correia", "Rua Bale nº08", "25-12-68", 123);
    client10 = new Client("client10@email.com", "pass10", "Maria Tavares", "Rua Gaitan nº785", "24-02-95", 158);
    client11 = new Client("client11@email.com", "pass11", "Joana Barroso", "Rua Brahimi nº231", "11-06-98", 321);
    client12 = new Client("client12@email.com", "pass12", "Menezes", "Rua William nº588", "14-03-76", 73);
    client13 = new Client("client13@email.com", "pass13", "Helena da POnte", "Rua Adrien nº23", "06-05-91", 875);
    client14 = new Client("client14@email.com", "pass14", "Bruno Oliveira", "Rua Sanches nº99", "30-10-94", 153);
    client15 = new Client("client15@email.com", "pass15", "Paulo Khullman", "Rua Andre Silva nº876", "30-10-94", 153);
    client16 = new Client("client16@email.com", "pass16", "Nuno Brown", "Rua Bernardo nº901", "30-10-94", 153);
    client17 = new Client("client17@email.com", "pass17", "Hugo Silva", "Rua Bruyne nº10", "30-10-94", 153);
    client18 = new Client("client18@email.com", "pass18", "Tania Ballesteros", "Rua Pogba nº756", "30-10-94", 153);
    client19 = new Client("client19@email.com", "pass19", "Cátia Jesus", "Rua Dybala nº43", "30-10-94", 153);
    client20 = new Client("client20@email.com", "pass20", "Griezmann", "Rua Cristiano Ronaldo nº7", "30-10-94", 153);

    Car car1, car2, car3, car4, car5, car6, car7, car8, car9;
    Van van1, van2, van3;
    MotorBike moto1, moto2, moto3;
    car1 = new Car(72, 1.4, "02-XX-97");
    car2 = new Car(80, 1, "23-FG-97");
    car3 = new Car(68, 1.6, "02-XFD-65");
    car4 = new Car(65, 1.9, "76-BC-98");
    car5 = new Car(71, 1, "76-XF-56");
    car6 = new Car(92, 0.9, "02-GE-32");
    car7 = new Car(99, 0.7, "62-BG-57");
    car8 = new Car(78, 1, "11-SD-98");
    car9 = new Car(69, 1.3, "01-WQ-77");
    van1 = new Van(55, 1.6, "88-DG-88");
    van2 = new Van(60, 1.8, "02-VF-32");
    van3 = new Van(47, 1.3, "87-JHY-32");
    moto1 = new MotorBike(60, 1, "76-BS-90");
    moto2 = new MotorBike(70, 1.2, "12-QW-43");
    moto3 = new MotorBike(81, 0.5, "02-NH-52");

    try{
      taxiCompany.addVehicle(car1);
      taxiCompany.addVehicle(car2);
      taxiCompany.addVehicle(car3);
      taxiCompany.addVehicle(car4);
      taxiCompany.addVehicle(car5);
      taxiCompany.addVehicle(car6);
      taxiCompany.addVehicle(car7);
      taxiCompany.addVehicle(car8);
      taxiCompany.addVehicle(car9);
      taxiCompany.addVehicle(van1);
      taxiCompany.addVehicle(van2);
      taxiCompany.addVehicle(van3);
      taxiCompany.addVehicle(moto1);
      taxiCompany.addVehicle(moto2);
      taxiCompany.addVehicle(moto3);
    } catch(VehicleExistsException e){
      System.out.println("Erro loading vehicles");
    }

    try{
      taxiCompany.addClient(client1);
      taxiCompany.addClient(client2);
      taxiCompany.addClient(client3);
      taxiCompany.addClient(client4);
      taxiCompany.addClient(client5);
      taxiCompany.addClient(client6);
      taxiCompany.addClient(client7);
      taxiCompany.addClient(client8);
      taxiCompany.addClient(client9);
      taxiCompany.addClient(client10);
      taxiCompany.addClient(client11);
      taxiCompany.addClient(client12);
      taxiCompany.addClient(client13);
      taxiCompany.addClient(client14);
      taxiCompany.addClient(client15);
      taxiCompany.addClient(client16);
      taxiCompany.addClient(client17);
      taxiCompany.addClient(client18);
      taxiCompany.addClient(client19);
      taxiCompany.addClient(client20);
    } catch (UserExistsException a) {
      System.out.println("Erro loading clients");
    }

    try{
      taxiCompany.addDriver(driver1);
      taxiCompany.addDriver(driver2);
      taxiCompany.addDriver(driver3);
      taxiCompany.addDriver(driver4);
      taxiCompany.addDriver(driver5);
      taxiCompany.addDriver(driver6);
      taxiCompany.addDriver(driver7);
      taxiCompany.addDriver(driver8);
      taxiCompany.addDriver(driver9);
      taxiCompany.addDriver(driver10);
      taxiCompany.addDriver(driver11);
      taxiCompany.addDriver(driver12);
      taxiCompany.addDriver(driver13);
      taxiCompany.addDriver(driver14);
      taxiCompany.addDriver(driver15);
    } catch (UserExistsException i){
      System.out.println("Erro loading drivers");
    }

    taxiCompany.startDay(driver10);
    taxiCompany.startDay(driver5);
    taxiCompany.startDay(driver4);
    taxiCompany.startDay(driver14);

    /*taxiCompany.addTaxi(driver10, car4);
    taxiCompany.addTaxi(driver5, car8);
    taxiCompany.addTaxi(driver4, van2);
    taxiCompany.addTaxi(driver14, moto1);*/
  }
}
