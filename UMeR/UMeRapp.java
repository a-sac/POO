import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UMeRapp implements Serializable {

	private UMeR taxiCompany;
	private Admin admin;
	private Client client;
	private Driver driver;
	private int userType;
	private Menu homeMenu, clientMenu, driverMenu, signUpMenu, vehicleMenu, callingTaxiMenu, favoriteMenu, signUpVehicleMenu, adminMenu, specificVehicleMenu, driverSubMenu;

	public void run() {
		StartApp();
		loadMenus();
		try {
			runHomeMenu();
		} catch (NullPointerException e){
			System.out.println("Problem loading HomeMenu");
		}
		EndApp();
	}

	public UMeRapp(){
		this.taxiCompany = new UMeR();
	}

	private void loadMenus() {
		String[] main = {"Iniciar Sessão",
		"Registar utilizador",
		"Procurar táxis",
		"Mostrar todos os Táxis",
		"Modo Administrador"};

		String[] client = {"Procurar táxis por tipo",
		"Procurar táxis disponíveis",
		"Mostrar todos os Táxis",
		"Pedir Táxi",
		"Mostrar histórico de viagens",
		"Adicionar táxi aos favoritos",
		"Obter lista de favoritos",
		"Fechar sessão"};

		String[] driver = {"Começar trabalho",
		"Mostrar histórico de viagens",
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

		String[] admin ={"Registar viatura",
		"10 clientes mais gastadores",
		"5 piores motoristas"};

		String[] specificVehicle = {"Carro",
		"Carrinha",
		"Mota"};

		String[] subDriver = {"Obter informação do cliente seguinte",
		"Iniciar viagem",
		"Finalizar viagem",
		"Terminar trabalho"};

		homeMenu = new Menu(main);
		clientMenu = new Menu(client);
		driverMenu = new Menu(driver);
		signUpMenu = new Menu(signUp);
		vehicleMenu = new Menu(vehicle);
		callingTaxiMenu = new Menu(callingTaxi);
		favoriteMenu = new Menu(favorite);
		signUpVehicleMenu = new Menu(signUpVehicle);
		adminMenu = new Menu(admin);
		specificVehicleMenu = new Menu(specificVehicle);
		driverSubMenu = new Menu(subDriver);
	}

	private void runHomeMenu() {
		Scanner read = new Scanner(System.in);
		int code;
		do {
			homeMenu.executa();
			switch(homeMenu.getOpcao()) {
				case 1: try {
					login();
				} catch (NullPointerException e){
					System.out.println("There was a problem during the last request.  Could not login");
				}
				break;

				case 2: try {
					signUp();
				} catch (NullPointerException e){
					System.out.println("There was a problem during the last request. Could not sign up");
				}
				break;

				case 3: try {
					availableTaxis();
				} catch (NullPointerException e){
					System.out.println("There was a problem during the last request. There are no available taxis");
				}
				break;

				case 4: try {
					showAllTaxis();
				} catch (NullPointerException e){
					System.out.println("There was a problem during the last request. There are no taxis");
				}
				break;

				case 5: try {
					this.admin = new Admin();
					System.out.println("Digite o código: ");
					code = read.nextInt();
					if(code == this.admin.getCode()) admin();
					else System.out.println("Código inválido");
				} catch (NullPointerException e){
					System.out.println("There was a problem during the last request. You are not an admin");
				}
				break;

			}
		} while(homeMenu.getOpcao() != 0);
	}

	private void runClientMenu() {
		do {
			clientMenu.executa();
			switch(clientMenu.getOpcao()) {
				case 1: searchTaxisByType(); break;
				case 2: availableTaxis(); break;
				case 3: showAllTaxis(); break;
				case 4: callTaxi(); break;
				case 5: showHistory(); break;
				case 6: getFavorites(); break;
				case 7: logout(); break;
			}
		}while(clientMenu.getOpcao() != 0);
	}

	private void runDriverMenu() {
		do {
			driverMenu.executa();
			switch(driverMenu.getOpcao()) {
				case 1:	Taxi t = startWork();
				runDriverSubMenu(t.clone());
				break;
				case 2: showHistory(); break;
				case 3: logout(); break;
			}
		} while(driverMenu.getOpcao() != 0);
	}

	private void runDriverSubMenu(Taxi t){
		do{
			driverSubMenu.executa();
			switch(driverSubMenu.getOpcao()){
				case 1: getInformationNextClient(); break;
				case 2: startTaxiRide(t); break;
				case 3: finishTaxiRide(t); break;
				case 4: endWork(); break;
			}
		}while(driverSubMenu.getOpcao()!=0);
	}

	private void admin(){
		do{
			adminMenu.executa();
			switch(adminMenu.getOpcao()){
				case 1: signUpVehicle(); break;
				case 2: top10Clients(); break;
				case 3: worst5Drivers(); break;
			}
		}while(adminMenu.getOpcao() != 0);
	}

	private void signUp() {
		String email, nome, password, morada, data;
		Point2D localizacao, destino;
		double destino_x, destino_y, localizacao_x, localizacao_y;
		Scanner input = new Scanner(System.in);
		int answer, nd;

		signUpMenu.executa();
		try{
			if (signUpMenu.getOpcao() == 0) {
				System.out.println("Registo cancelado");
				return;
			}
		} catch (NullPointerException e){
			System.out.println("Sign up was not successfull");
		}

		System.out.print("Email: ");
		email = input.nextLine();

		System.out.print("Nome: ");
		nome = input.nextLine();

		System.out.print("Password: ");
		password = input.nextLine();

		System.out.print("Morada: ");
		morada = input.nextLine();

		System.out.print("Data de nascimento (dd-MM-yyyy): ");
		data = input.next();

		try{
			switch(signUpMenu.getOpcao()) {
				case 0: input.close();
				return;

				case 1: this.client = new Client(email, password, nome, morada, data, 0.0);
				this.taxiCompany.addClient(this.client);
				this.userType = 1;
				break;

				case 2: System.out.println("Digite o código de trabalhador: ");
				answer = input.nextInt();
				if(answer == this.taxiCompany.getDriverCode()){
					if(this.taxiCompany.getNVehicles() > this.taxiCompany.getNDrivers()){
						this.driver = new Driver(email, password, nome, morada, data, 0.0, 0.0, 0.0);
						this.taxiCompany.addDriver(this.driver);
						this.userType = 2;
						nd = this.taxiCompany.getNDrivers();
						nd++;
						this.taxiCompany.setNDrivers(nd);
					}
					else {
						System.out.println("Neste momento não há veículos disponíveis. Aguarde notificação.");
					}
				}
				else{System.out.println("Código errado");}
				break;
			}
		} catch(UserExistsException e){
			System.out.println("utilizador já existe");
		}

	}

	private void login() {
		Scanner input = new Scanner(System.in);
		String email, password;

		System.out.print("Email: ");
		email = input.nextLine();

		System.out.print("Password: ");
		password = input.nextLine();
		try{
			try{
				taxiCompany.login(email, password);
			} catch (NullPointerException e){
				System.out.println("Problema no login");
			}
			switch (taxiCompany.getUserType()) {
				case 1: saveClientData(email);
				runClientMenu();
				break;
				case 2: saveDriverData(email);
				runDriverMenu();
				break;
			}
		}
		catch(UserDoesNotExistsException e){
			System.out.println(e.getMessage());
		}
	}

	private void logout(){
		runHomeMenu();
	}

	private void saveClientData(String email){
		try{
			Client c = this.taxiCompany.getClients().get(email).clone();
			this.client = c;
			this.userType = 1;
		} catch (NoClientsException e){
			System.out.println("Could not access all clients");
		}
	}

	private void saveDriverData(String email){
		try{
			Driver d = this.taxiCompany.getDrivers().get(email).clone();
			this.driver = d;
			this.userType = 2;
		}catch (NoDriversException e){
			System.out.println("Could not access all drivers");
		}
	}

	//for clients
	private void availableTaxis(){
		try{
			for(Taxi t: taxiCompany.getTaxis()){
				if(t.isOccupied()==false) System.out.println(t.toString());
			}
		}catch(NoTaxisException e){
			System.out.println(e.getMessage());
		}
	}

	private void availableTaxis(int op){
		try{
			for(Taxi t: taxiCompany.getTaxis()){
				if(op==1 && (t.getVehicle() instanceof Car)){
					System.out.println(t.toString());
				}
				if(op==2 && (t.getVehicle() instanceof Van)){
					System.out.println(t.toString());
				}
				if(op==3 && (t.getVehicle() instanceof MotorBike)){
					System.out.println(t.toString());
				}
			}
		}catch(NoTaxisException e){
			System.out.println(e.getMessage());
		}
	}

	private void showAllTaxis(){
		try{
			for(Taxi t: this.taxiCompany.getTaxis()){
				System.out.println(t.toString());
			}
		}catch(NoTaxisException e){
			System.out.println(e.getMessage());
		}
	}

	private void searchTaxisByType(){
		int op;
		Scanner read = new Scanner(System.in);
		System.out.println("Opção 1: Carro");
		System.out.println("Opção 2: Carrinha");
		System.out.println("Opção 3: Mota");
		op = read.nextInt();
		switch(op){
			case 1: availableTaxis(1);
			case 2: availableTaxis(2);
			case 3: availableTaxis(3);
		}
	}

	private void callTaxi(){
		Scanner read = new Scanner(System.in);
		double x, y, final_x, final_y;
		System.out.println("Localização: (coordenada x)");
		x = read.nextDouble();
		System.out.println("Localização: (coordenada y)");
		y = read.nextDouble();
		System.out.println("Destino: (coordenada x)");
		final_x = read.nextDouble();
		System.out.println("Destino: (coordenada y)");
		final_y = read.nextDouble();

		this.client.addLocation(x,y);
		this.client.addDestination(final_x, final_y);

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
		String answer;
		Scanner read = new Scanner(System.in);
		do{
			specificVehicleMenu.executa();
			switch(specificVehicleMenu.getOpcao()){
				case 1:	specificCar(); break;
				case 2: specificVan(); break;
				case 3: specificMotorBike(); break;
			}
		}while(specificVehicleMenu.getOpcao()!=0);
	}

	private void specificCar(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestCar(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestCar(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestCar(this.client.clone()).enqueue(this.client);
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeCar(this.client.clone());
					System.out.println("Taxi a caminho!");
					makeTrip(t);
					System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
					answer = read.nextLine();
					if(answer.equals("Sim")) addFavorite(t.clone());
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestCar(this.client.clone()));
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}
	}

	private void specificVan(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestVan(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestVan(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestVan(this.client.clone()).enqueue(this.client);
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeVan(this.client.clone());
					System.out.println("Taxi a caminho!");
					makeTrip(t);
					System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
					answer = read.nextLine();
					if(answer.equals("Sim")) addFavorite(t.clone());
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestVan(this.client.clone()));
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}
	}

	private void specificMotorBike(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestMotorBike(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestMotorBike(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestMotorBike(this.client.clone()).enqueue(this.client);
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeMotorBike(this.client.clone());
					System.out.println("Taxi a caminho!");
					makeTrip(t);
					System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
					answer = read.nextLine();
					if(answer.equals("Sim")) addFavorite(t.clone());
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestMotorBike(this.client.clone()));
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}
	}

	private void closestTaxi(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestTaxi(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestTaxi(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestTaxi(this.client.clone()).enqueue(this.client);
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeTaxi(this.client.clone());
					System.out.println("Taxi a caminho!");
					makeTrip(t);
					System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
					answer = read.nextLine();
					if(answer.equals("Sim")) addFavorite(t.clone());
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(this.taxiCompany.getClosestTaxi(this.client.clone()));
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}
	}


	private void specificDriver(){
		Scanner read = new Scanner(System.in);
		String email, answer;
		Taxi t = null;
		printDrivers();
		email = writeEmail();
		try{
			Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
			int flag=0;
			while(it.hasNext() && flag==0) {
				t = it.next();
				if(t.getDriver().getEmail().equals(email)) flag=1;
			}
			if(t.isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para fila de espera? [Sim/Nao]");
				answer = read.nextLine();
				if(answer.equals("Sim")) t.enqueue(this.client);
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t);
			}
		}catch(NoTaxisException e){
			System.out.println(e.getMessage());
		}
	}

	private String writeEmail(){
		Scanner read = new Scanner(System.in);
		String email=null, actual=null;
		try{
			Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
			Taxi t;
			int flag=0;
			do{
				System.out.print("Escolha o e-mail do motorista pretendido: ");
				email = read.nextLine();
				while(it.hasNext() && flag==0){
					t = it.next();
					actual = t.getDriver().getEmail();
					if(actual.equals(email)==false) System.out.println("E-mail inválido. Tente outra vez!");
					else flag=1;
				}
			}while(email.equals(actual)==false);
		}catch(NoTaxisException e){System.out.println("Sem Taxis");}
		return email;
	}

	private String writePlate(){
		Scanner read = new Scanner(System.in);
		String plate=null, actual=null;
		try{
			Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
			Taxi t;
			int flag=0;
			do{
				System.out.print("Escolha o e-mail do motorista pretendido: ");
				plate = read.nextLine();
				while(it.hasNext() && flag==0){
					t = it.next();
					actual = t.getVehicle().getPlate();
					if(actual.equals(plate)==false) System.out.println("E-mail inválido. Tente outra vez!");
					else flag=1;
				}
			}while(plate.equals(actual)==false);
		}catch(NoTaxisException e){System.out.println("Sem Taxis");}
		return plate;
	}

	private void printDrivers(){
		try{
			for (Taxi t: this.taxiCompany.getTaxis()){
				System.out.println(t.getDriver().getEmail());
			}
		} catch(NoTaxisException e){
			System.out.println("Sem motoristas");
		}
	}

	private void makeTrip(Taxi t){
		this.client.callTaxi(t);
		t.goToNextClient();
		t.pickUpClient();
		t.rideStart(taxiCompany.getTraffic(t));
		t.rideEnd();
	}

	private void showHistory(){
		switch(this.userType){
			case 1: this.client.printHistory();
			case 2: this.driver.printHistory();
		}
	}

	private void addFavorite(Taxi t){
		do{
			favoriteMenu.executa();
			switch(favoriteMenu.getOpcao()){
				case 1: addFavoriteDriver(t); break;
				case 2: addFavoriteVehicle(t); break;
			}
		}while(favoriteMenu.getOpcao() != 0);
	}

	private void addFavoriteDriver(Taxi t){
		try{
			this.client.addDriver(t.getDriver().clone());
		}catch(UserExistsException e){
			System.out.println("Motorista existente");
		}
	}

	private void addFavoriteVehicle(Taxi t){
		try{
			this.client.addVehicle(t.getVehicle().clone());
		}catch(VehicleExistsException e){
			System.out.println("Veículo já existente");
		}
	}

	private void getFavorites(){
		Scanner read = new Scanner(System.in);
		int answer;
		System.out.println("Opcao 1 - Motoristas");
		System.out.println("Opcao 2 - Veículos");
		answer = read.nextInt();
		switch(answer){
			case 1: this.client.printFavoriteDrivers(); break;
			case 2: this.client.printFavoriteVehicles(); break;
		}
	}

	//for drivers
	private Taxi startWork(){
		Taxi t = this.taxiCompany.startDay(this.driver);
		return t;
	}

	private void endWork(){
		this.taxiCompany.endDay(this.driver);
	}

	private void getInformationNextClient(){
		Taxi t = null;
		Client c;
		try{
			for(Taxi t2: this.taxiCompany.getTaxis()){
				if(t2.getDriver().getEmail().equals(this.driver.getEmail())) t = t2;
			}

			c = t.getWaitingQ().poll();
			if(c!=null){
				System.out.println(c.getName());
				System.out.println(c.getLocation());
				System.out.println(c.getDestination());
				t.goToNextClient();
				t.pickUpClient();
			}
			else System.out.println("Sem pedidos de Clientes!");
		}catch(NoTaxisException e){
			System.out.println("Sem Taxis");
		}
	}

	private void signUpVehicle(){
		Scanner input = new Scanner(System.in);
		String plate;
		double avgSpeed, factor;
		int nv;

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
			case 0: input.close();
			return;
			case 1: Car c = new Car(avgSpeed, factor, plate);
			signUpCar(c);
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;
			case 2: Van v = new Van(avgSpeed, factor, plate);
			signUpVan(v);
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;
			case 3: MotorBike m = new MotorBike(avgSpeed, factor, plate);
			signUpMotorBike(m);
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;
		}
	}

	private void signUpCar(Car c){
		try{
			this.taxiCompany.addVehicle(c);
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void signUpVan(Van v){
		try{
			this.taxiCompany.addVehicle(v);
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void signUpMotorBike(MotorBike m){
		try{
			this.taxiCompany.addVehicle(m);
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void startTaxiRide(Taxi t){
		t.rideStart(this.taxiCompany.getTraffic(t));
	}

	private void finishTaxiRide(Taxi t){
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

	private boolean lerBoolean(String msg) {
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

	private void top10Clients(){
		try{
			int i;
			TreeSet<Client> top = new TreeSet<Client>();
			for(Client c : taxiCompany.getClients().values()){
				top.add(c);
			}
			for(i=0; i<10; i++){
				System.out.println(top.pollLast().toString());
			}
		}
		catch(NoClientsException e){System.out.println("Problem while accessing all clients");}
	}

	private void worst5Drivers(){
		try{
			int i;
			TreeSet<Driver> top = new TreeSet<Driver>();
			for(Driver d : taxiCompany.getDrivers().values()){
				top.add(d);
			}
			for(i=0; i<5 && !top.isEmpty(); i++){
				System.out.println(top.pollLast().toString());
			}
		}
		catch(NoDriversException e){System.out.println("Problem accessing drivers");}
	}

	public void StartApp(){
		try {
			FileInputStream file = new FileInputStream(new File("data"));
			ObjectInputStream ios = new ObjectInputStream(file);
			/*try{
			this.taxiCompany = (UMeR) ios.readObject();
		} catch(ClassNotFoundException i){
		System.out.println("Error while loading data.");
		i.printStackTrace(System.out);
	}*/
	ios.close();
} catch(IOException e) {
	System.out.println("Error while loading data.");
	e.printStackTrace(System.out);
}
}

public void EndApp(){
	try {
		ObjectOutputStream oos = new ObjectOutputStream ( new FileOutputStream("data"));
		oos.writeObject(this.taxiCompany);
		oos.flush();
		oos.close();
	} catch(IOException e) {
		System.out.println("Error while saving data.");
		e.printStackTrace(System.out);
	}
}
}
