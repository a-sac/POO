import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class UMeRapp {

	private UMeR taxiCompany;
	private Client client;
	private Driver driver;
	private int userType;
	private Menu homeMenu, clientMenu, driverMenu, signUpMenu, vehicleMenu, callingTaxiMenu, favoriteMenu, signUpVehicleMenu;

	public void run() {
		//load estado parser
		loadMenus();
		runHomeMenu();
		//gravar estado parser
	}

	public UMeRapp(){}

	private void loadMenus() {
		String[] main = {"Iniciar Sessão",
							  "Registar utilizador",
							  "Procurar táxis",
							  "Mostrar todos os Táxis"};

		String[] client = {"Procurar táxis por tipo",
							  "Procurar táxis disponíveis",
							  "Mostrar todos os Táxis",
								"Pedir Táxi",
								"Mostrar histórico de viagens",
							  "Adicionar táxi aos favoritos",
							  "Obter lista de favoritos",
							  "Fechar sessão"};

		String[] driver = {"Mostrar histórico de viagens",
							 "Obter informação do cliente seguinte",
							 "Registar viatura",
							 "Iniciar viagem",
							 "Finalizar viagem",
							 "Fechar sessão"};

		String[] signUp = {"Registar cliente",
							 "Registar motorista"};

		String[] vehicle = {"Mostrar Velocidade média",
							 "Mostrar fator de confiança",
							 "Ver Matrícula"};

		String[] callingTaxi = {"Pedir veículo específico",
							 "Pedir taxi mais próximo",
						 	 "Chamar por motorista específico"};

		String[] favorite = {"Adicionar motorista",
							 "Adicionar veiculo"};

		String[] signUpVehicle = {"Registar Carro",
							 "Registar Carrinha",
						   "Registar Mota"};

		homeMenu = new Menu(main);
		clientMenu = new Menu(client);
		driverMenu = new Menu(driver);
		signUpMenu = new Menu(signUp);
		vehicleMenu = new Menu(vehicle);
		callingTaxiMenu = new Menu(callingTaxi);
		favoriteMenu = new Menu(favorite);
		signUpVehicleMenu = new Menu(signUpVehicle);
	}

	private void runHomeMenu() {
		do {
			homeMenu.executa();
			switch(homeMenu.getOpcao()) {
				case 1: login();
				case 2: signUp();
				case 3: availableTaxis(); break;
				case 4: showAllTaxis(); break;
			}
		} while(homeMenu.getOpcao() != 0);
	}

	private void runClientMenu() {
		do {
			clientMenu.executa();
			switch(clientMenu.getOpcao()) {
				case 1: searchTaxisByType();
				case 2: availableTaxis(); break;
				case 3: showAllTaxis(); break;
				case 4: callTaxi(); break;
				case 5: showHistory(); break;
				case 6: addFavorite(); break;
				case 7: getFavorites(); break;
				case 8: logout(); break;
			}
		} while(clientMenu.getOpcao() != 8);

	}

	private void runDriverMenu() {
		do {
			driverMenu.executa();
			switch(driverMenu.getOpcao()) {
				case 1: signUpVehicle(); break;
				case 2: showHistory(); break;
				case 3: getInformationNextClient(); break;
				case 4: startTaxiRide(); break;
				case 5: finishTaxiRide(); break;
				case 6: logout(); break;
			}
		} while(driverMenu.getOpcao() != 6);
	}

	private void signUp() {
		String email, nome, password, morada, data;
		Point2D localizacao, destino;
		double destino_x, destino_y, localizacao_x, localizacao_y;
		Scanner input = new Scanner(System.in);

		signUpMenu.executa();
		if (signUpMenu.getOpcao() == 0) {
			System.out.println("Registo cancelado");
			return;
		}

		System.out.print("Email: ");
		email = input.nextLine();

		System.out.print("Nome: ");
		nome = input.nextLine();

		System.out.print("Password: ");
		password = input.nextLine();

		System.out.print("Morada: ");
		morada = input.nextLine();

		System.out.print("Localização atual: (coordenada x)");
		localizacao_x = input.nextDouble();

		System.out.print("Localização atual: (coordenada y)");
		localizacao_y = input.nextDouble();

		localizacao = new Point2D(localizacao_x, localizacao_y);

		System.out.print("Destino: (coordenada x)");
		destino_x = input.nextDouble();
		System.out.print("Destino: (coordenada y)");
		destino_y = input.nextDouble();

		destino = new Point2D(destino_x, destino_y);

		System.out.print("Data de nascimento (dd-MM-yyyy): ");
		data = input.next();

		switch(signUpMenu.getOpcao()) {
			case 0: input.close(); return;

			case 1: this.client = new Client(localizacao, destino, email, password, nome, morada, data, 0.0);
							this.userType = 1;
							break;

			case 2: this.driver = new Driver(email, password, nome, morada, data, 0.0, 0.0, 0.0);
							this.userType = 2;
							break;
		}

		//TODO adicionar o user cliente/motorista à umer

	}

	private void login() {
		Scanner input = new Scanner(System.in);
		String email, password;

		System.out.print("Email: ");
		email = input.nextLine();

		System.out.print("Password: ");
		password = input.nextLine();

		taxiCompany.login(email, password);

		switch (taxiCompany.getUserType()) {
			case 1: saveClientData(email);
							runClientMenu();
							break;
			case 2: saveDriverData(email);
							runDriverMenu();
							break;
		}
	}

	private void logout(){
		runHomeMenu();
	}

	private void saveClientData(String email){
		Client c = this.taxiCompany.getClients().get(email);
		this.client = c.clone();
		this.userType = 1;
	}

	private void saveDriverData(String email){
		Driver d = this.taxiCompany.getDrivers().get(email);
		this.driver = d.clone();
		this.userType = 2;
	}

	private void availableTaxis(){
		for(Taxi t: taxiCompany.getTaxis()){
			if(t.isOccupied()==false) System.out.println(t.getID());
		}
	}

	private void availableTaxis(int op){
		for(Taxi t: taxiCompany.getTaxis()){
			if(op==1 && (t.getVehicle().getClass() == Car.class)){
				System.out.println(t.getID());
			}
			if(op==2 && (t.getVehicle().getClass() == Van.class)){
				System.out.println(t.getID());
			}
			if(op==3 && (t.getVehicle().getClass() == MotorBike.class)){
				System.out.println(t.getID());
			}
		}
	}

	private void showAllTaxis(){
		for(Taxi t: taxiCompany.getTaxis()){
			System.out.println(t.getID());
		}
	}

	private void searchTaxisByType(){
		int op;
		Scanner read = new Scanner(System.in);
		System.out.println("Opção 1: Carro");
		System.out.println("Opção 2: Carrinha");
		System.out.println("Opção 3: Mota");
		op = read.nextInt();
		if(op==1) availableTaxis(1);
		if(op==2) availableTaxis(2);
		if(op==3) availableTaxis(3);
	}

	private void callTaxi(){
		do{
			callingTaxiMenu.executa();
			switch(callingTaxiMenu.getOpcao()){
				case 1: specificVehicle(); break;
				case 2: closestTaxi(); break;
				case 3: specificDriver(); break;
			}
		} while(callingTaxiMenu.getOpcao() != 0);
	}

	private void specificVehicle(){
		int option;
		String answer;
		Scanner read = new Scanner(System.in);
		System.out.println("Opção 1: Carro");
		System.out.println("Opção 2: Carrinha");
		System.out.println("Opção 3: Mota");
		option = read.nextInt();
		if(option == 1){
			if(this.taxiCompany.getClosestCar().isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para a fila de espera? [Sim/Não]");
				answer = read.nextLine();
				if(answer == "Sim") this.taxiCompany.getClosestCar().enqueue(this.client);//TODO passar cliente como argumento.
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestCar());
			}
		}
		if(option == 2){
			if(this.taxiCompany.getClosestVan().isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para a fila de espera? [Sim/Não]");
				answer = read.nextLine();
				if(answer == "Sim") this.taxiCompany.getClosestVan().enqueue(this.client);//TODO passar cliente como argumento.
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestVan());
			}
		}
		if(option == 3){
			if(this.taxiCompany.getClosestMotorBike().isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para a fila de espera? [Sim/Não]");
				answer = read.nextLine();
				if(answer == "Sim") this.taxiCompany.getClosestMotorBike().enqueue(this.client);//TODO passar cliente como argumento.
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestMotorBike());
			}
		}
	}

	private void closestTaxi(){
		Taxi t;
		Scanner read = new Scanner(System.in);
		String answer;
		Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
		while(it.hasNext()) {
			t = it.next();
			if(t.isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para fila de espera? [Sim/Nao]");
				answer = read.nextLine();
				if(answer == "Sim") t.enqueue(this.client);//TODO passar cliente como argumento.
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t);
			}
		}
	}

	private void specificDriver(){
		Scanner read = new Scanner(System.in);
		String email, answer;
		Taxi t = null;
		printDrivers();
		email = writeEmail();
		Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
		int flag=0;
		while(it.hasNext() && flag==0) {
			t = it.next();
			if(t.getDriver().getEmail() == email) flag=1;
		}
		if(t.isOccupied() == true){
			System.out.println("Este taxi está ocupado. Deseja ir para fila de espera? [Sim/Nao]");
			answer = read.nextLine();
			if(answer == "Sim") t.enqueue(this.client);//TODO passar cliente como argumento.
		}
		else {
			System.out.println("Taxi a caminho!");
			makeTrip(t);
		}
	}

	private String writeEmail(){
		Scanner read = new Scanner(System.in);
		String email;
		do{
			System.out.print("Escolha o e-mail do motorista pretendido: ");
			email = read.nextLine();
			if(this.taxiCompany.getDrivers().containsKey(email) == false){
				System.out.print("E-mail inválido. Tente outra vez!");
			}
		}while(this.taxiCompany.getDrivers().containsKey(email) == false);
		return email;
	}

	private String writePlate(){
		Scanner read = new Scanner(System.in);
		String plate;
		do{
			System.out.print("Escolha a matrícula do veículo pretendido: ");
			plate = read.nextLine();
			if(this.taxiCompany.getVehicles().containsKey(plate) == false){
				System.out.print("Matrícula inválida. Tente outra vez!");
			}
		}while(this.taxiCompany.getVehicles().containsKey(plate) == false);
		return plate;
	}

	private void printDrivers(){
		for (String email: this.taxiCompany.getDrivers().keySet()){
				System.out.println(email);
		}
	}

	private void makeTrip(Taxi t){
		t.enqueue(this.client);
		t.goToNextClient();
		t.pickUpClient();
		t.rideStart();
		t.rideEnd();
	}

	private void showHistory(){
		switch(this.userType){
			case 1: this.client.printHistory();
			case 2: this.driver.printHistory();
		}
	}

	private void addFavorite(){
		do{
			favoriteMenu.executa();
			switch(favoriteMenu.getOpcao()){
				case 1: addFavoriteDriver(); break;
				case 2: addFavoriteVehicle(); break;
			}
		} while(callingTaxiMenu.getOpcao() != 0);
	}

	private void addFavoriteDriver(){
		String email = writeEmail();
		Driver d = this.taxiCompany.getDrivers().get(email);
		this.client.getFavoriteDrivers().put(d.getEmail(), d);
	}

	private void addFavoriteVehicle(){
		String plate = writePlate();
		Vehicle v = this.taxiCompany.getVehicles().get(plate);
		this.client.getFavoriteVehicles().put(v.getPlate(), v);
	}

	private void getFavorites(){
		Scanner read = new Scanner(System.in);
		int answer;
		System.out.print("Opcao 1 - Motoristas");
		System.out.print("Opcao 2 - Veículos");
		answer = read.nextInt();
		switch(answer){
			case 1: this.client.printFavoriteDrivers();
			case 2: this.client.printFavoriteVehicles();
		}
	}

	private void getInformationNextClient(){
		Taxi t = null;
		Client c;
		for(Taxi t2: this.taxiCompany.getTaxis()){
			if(t2.getDriver().getEmail().equals(this.driver.getEmail())) t = t2;
		}
		c = t.getWaitingQ().poll();
		System.out.println(t.getWaitingQ().poll().getName());
		System.out.println(t.getWaitingQ().poll().getLocation());
		System.out.println(t.getWaitingQ().poll().getDestination());

		t.goToNextClient();
		t.pickUpClient();
	}

	private void signUpVehicle(){
		Scanner input = new Scanner(System.in);
		String plate;
		double avgSpeed, factor;

		signUpVehicleMenu.executa();
		if (signUpVehicleMenu.getOpcao() == 0) {
			System.out.println("Registo cancelado");
			return;
		}

		System.out.print("Plate: ");
		plate = input.nextLine();

		System.out.print("Average Speed: ");
		avgSpeed = input.nextDouble();

		System.out.print("Factor: ");
		factor = input.nextDouble();

		switch(signUpVehicleMenu.getOpcao()){
			case 0: input.close(); return;
			case 1: Car c = new Car(avgSpeed, factor, plate);
							signUpCar(c);
							break;
			case 2: Van v = new Van(avgSpeed, factor, plate);
							signUpVan(v);
							break;
			case 3: MotorBike m = new MotorBike(avgSpeed, factor, plate);
							signUpMotorBike(m);
							break;
		}
	}

	private void signUpCar(Car c){
		this.taxiCompany.addVehicle(c);
	}

	private void signUpVan(Van v){
		this.taxiCompany.addVehicle(v);
	}

	private void signUpMotorBike(MotorBike m){
		this.taxiCompany.addVehicle(m);
	}

	private void startTaxiRide(){
		Taxi t=null;
		for(Taxi t2: this.taxiCompany.getTaxis()){
			if(t2.getDriver().getEmail().equals(this.driver.getEmail())) t = t2;
		}
		t.rideStart();
	}

	private void finishTaxiRide(){
		Taxi t=null;
		for(Taxi t2: this.taxiCompany.getTaxis()){
			if(t2.getDriver().getEmail().equals(this.driver.getEmail())) t = t2;
		}
		t.rideEnd();
	}

	private  double lerDouble(String msg) {
		Scanner input = new Scanner(System.in);
		double d = 0.0;

		System.out.print(msg);
		try {
			d = input.nextDouble();
		}
		catch (InputMismatchException e) {
			System.out.println("Formato incorreto");
			d = lerDouble(msg);
		}

		finally {
			input.close();
		}

		return d;
	}

	private  int lerInt(String msg) {
		Scanner input = new Scanner(System.in);
		int d = 0;

		System.out.print(msg);
		try {
			d = input.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("Formato incorreto");
			d = lerInt(msg);
		}

		finally {
			input.close();
		}

		return d;
	}

	private  boolean lerBoolean(String msg) {
		Scanner input = new Scanner(System.in);
		String s;
		boolean res = true;

		System.out.print(msg);
		s = input.nextLine();

		if (s.charAt(0) == 'n')
			res = false;

		input.close();
		return res;
	}
}
