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
	private static Admin admin;
	private Client client;
	private Driver driver;
	private static int userType;
	private static Menu homeMenu, clientMenu, driverMenu, signUpMenu, callingTaxiMenu, favoriteMenu, signUpVehicleMenu, adminMenu, specificVehicleMenu, driverSubMenu, profitMenu;

	public void run() {
		StartApp();
		loadMenus();
		try {
			runHomeMenu();
		} catch (NullPointerException e){
			System.out.println("Problema ao carregar Menu inicial");
		}
		EndApp();
	}

	public UMeRapp(){
		File f = new File("data");
		this.taxiCompany = new UMeR();
		if(!f.exists()) {
			//new Test(this.taxiCompany);
			new Test2(this.taxiCompany);
		}
	}

	private void loadMenus() {
		String[] main = {"Iniciar Sessão",
		"Registar utilizador",
		"Modo Administrador"};

		String[] client = {"Procurar táxis por tipo",
		"Procurar táxis disponíveis",
		"Mostrar todos os Táxis",
		"Pedir Táxi",
		"Mostrar histórico de viagens",
		"Obter lista de favoritos",
		"Mostrar Perfil"};

		String[] driver = {"Começar trabalho",
		"Mostrar histórico de viagens",
		"Terminar trabalho",
		"Mostrar Perfil"};

		String[] signUp = {"Sou cliente",
		"Sou motorista"};

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
		"5 piores motoristas",
		"Lucro Total"};

		String[] specificVehicle = {"Carro",
		"Carrinha",
		"Mota"};

		String[] subDriver = {"Obter informação do cliente seguinte",
		"Iniciar viagem",
		"Finalizar viagem"};

		String[] profit = {"UMeR",
		"Taxi Específico"};

		homeMenu = new Menu(main);
		clientMenu = new Menu(client);
		driverMenu = new Menu(driver);
		signUpMenu = new Menu(signUp);
		adminMenu = new Menu(admin);
		callingTaxiMenu = new Menu(callingTaxi);
		favoriteMenu = new Menu(favorite);
		signUpVehicleMenu = new Menu(signUpVehicle);
		specificVehicleMenu = new Menu(specificVehicle);
		driverSubMenu = new Menu(subDriver);
		profitMenu = new Menu(profit);
	}

	private void runHomeMenu() {
		Scanner read = new Scanner(System.in);
		int code;
		do {
			homeMenu.executaHomeMenu();
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
					this.admin = new Admin();
					System.out.println("Digite o código: ");
					try{
						code = read.nextInt();
						if(code == this.admin.getCode()) runAdmin();
						else System.out.println("Código inválido!");
					} catch (InputMismatchException e) {
						System.out.println("Código inválido!");
						String input = read.next();
						continue;
					}
				} catch (NullPointerException e){
					System.out.println("Houve um problema no último pedido. Voçê não é um admin");
				}
				break;

			}
		} while(homeMenu.getOpcao() != 0);
	}

	private void runClientMenu() {
		do {
			clientMenu.executaClientMenu();
			switch(clientMenu.getOpcao()) {
				case 1: searchTaxisByType(); break;
				case 2: availableTaxis(); break;
				case 3: showAllTaxis(); break;
				case 4: callTaxi(); break;
				case 5: showHistory(); break;
				case 6: getFavorites(); break;
				case 7: showClientProfile(this.client.clone()); break;
			}
		}while(clientMenu.getOpcao() != 0);
	}

	private void runDriverMenu() {
		int flag=0;
		Taxi t;
		do {
			driverMenu.executaDriverMenu();
			switch(driverMenu.getOpcao()) {
				case 1:	if(flag==0){
									if(this.taxiCompany.findDriver(this.driver.clone()) == null){
										t = startWork().clone();
										runDriverSubMenu(t);
										flag = 1;
									} else{
										System.out.println("Já iniciou trabalho");
										t = this.taxiCompany.findDriver(this.driver.clone());
										runDriverSubMenu(t);
									}
								break;
								}

				case 2: showHistory(); break;
				case 3: endWork();
								flag=0;
								break;
				case 4: showDriverProfile(this.driver.clone()); break;
			}
		} while(driverMenu.getOpcao() != 0);
	}

	private void runDriverSubMenu(Taxi t){
		int finish=0;
		do{
			driverSubMenu.executaSubDriverMenu();
			switch(driverSubMenu.getOpcao()){
				case 1: getInformationNextClient(); break;
				case 2: startTaxiRide(t); break;
				case 3: finishTaxiRide(t); break;
			}
		}while(driverSubMenu.getOpcao()!=0);
	}

	private void runAdmin(){
		do{
			adminMenu.executaAdminMenu();
			switch(adminMenu.getOpcao()){
				case 1:
				try{
					signUpVehicle();
				} catch (InputMismatchException e){
					System.out.println("Problema a registar veiculo");
				}
					break;
				case 2:
				try{
					 top10Clients();
				 } catch (ClassCastException e) {
					 System.out.println("Problema a escrever os top 10 clientes (provavelmente ainda não há motoristas registados)");
				 }
				  break;
				case 3:
					try{
						worst5Drivers();
					}catch (ClassCastException e) {
 					 System.out.println("Problema a escrever os 5 motoristas (provavelmente ainda não há motoristas registados)");
 				 }
					break;

					case 4:
						runProfitMenu();
						break;
			}
		}while(adminMenu.getOpcao() != 0);
	}

	private void signUp() {
		String email, nome, password, morada, data;
		Point2D localizacao, destino;
		double destino_x, destino_y, localizacao_x, localizacao_y;
		Scanner input = new Scanner(System.in);
		int answer, nd;

		signUpMenu.executaSignUpMenu();
		try{
			if (signUpMenu.getOpcao() == 0) {
				System.out.println("Registo cancelado");
				return;
			}
		} catch (NullPointerException e){
			System.out.println("Sign up was not successfull");
		}

		email = newEmail();

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
				this.taxiCompany.addClient(this.client.clone());
				try{
					if(this.taxiCompany.getClients().containsKey(this.client.getEmail())) System.out.println("Registo efetuado com sucesso!");
				}catch(NoClientsException e){System.out.println("Sem Clientes");}
				this.userType = 1;
				break;

				case 2: System.out.println("Digite o código de trabalhador: ");
				try{
				answer = input.nextInt();
				if(answer == this.taxiCompany.getDriverCode()){
					if(this.taxiCompany.getNVehicles() > this.taxiCompany.getNDrivers()){
						this.driver = new Driver(email, password, nome, morada, data, 0.0, 0.0, 0.0);
						this.taxiCompany.addDriver(this.driver);
						this.userType = 2;
						nd = this.taxiCompany.getNDrivers();
						nd++;
						this.taxiCompany.setNDrivers(nd);
						System.out.println("Está registado. Um orgulho tê-lo connosco!");
					}
					else {
						System.out.println("Neste momento não há veículos disponíveis. Aguarde notificação.");
					}
				}
				else{System.out.println("Código Inválido!");}
				break;
				} catch (InputMismatchException e) {
					System.out.println("Código inválido!");
				}
			}
		} catch(UserExistsException e){
			System.out.println("utilizador já existe");
		}

	}

	private String newEmail(){
		Scanner read = new Scanner(System.in);
		int flag=0;
		String email="";
		do{
			System.out.println("Email: ");
			email = read.nextLine();
			if(this.taxiCompany.checkEmail(email)){ //true if contains
				System.out.println("Esse email já existe. Tente outra vez: ");
			} else flag = 1;
		}while(flag==0);
		return email;
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

	private void showClientProfile(Client c){
		this.taxiCompany.printClient(c);
	}

	private void showDriverProfile(Driver d){
		this.taxiCompany.printDriver(d);
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

	private void showAllTaxis(){
		try{
			for(Taxi t: this.taxiCompany.getTaxis()){
				System.out.println(t.toString());
			}
		}catch(NoTaxisException e){
			System.out.println("Sem Taxis");
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
			case 1: availableTaxis(1); break;
			case 2: availableTaxis(2); break;
			case 3: availableTaxis(3); break;
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

	private void callTaxi(){
		Scanner read = new Scanner(System.in);
		double x, y, final_x, final_y;
		try{
		System.out.println("Localização: (coordenada x)");
		x = read.nextDouble();
		System.out.println("Localização: (coordenada y)");
		y = read.nextDouble();
		System.out.println("Destino: (coordenada x)");
		final_x = read.nextDouble();
		System.out.println("Destino: (coordenada y)");
		final_y = read.nextDouble();

		this.client.setLocation(x,y);
		this.client.setDestination(final_x, final_y);
		int executed=0;
		do{
			callingTaxiMenu.executaCallTaxiMenu();
			switch(callingTaxiMenu.getOpcao()){
				case 1: specificVehicle();
								executed=1;
								break;

				case 2:	closestTaxi();
								executed=1;
								break;

				case 3:	specificDriver();
								executed=1;
								break;
			}
		}while(callingTaxiMenu.getOpcao() != 0 && executed==0);
	}catch(InputMismatchException e){
		System.out.println("Coordenadas erradas. Exemplo: 1,0 e 2,0");
	}
	}

	private void specificVehicle(){
		String answer;
		int executed = 0;
		Scanner read = new Scanner(System.in);
		do{
			specificVehicleMenu.executaSpecificVehicleMenu();
			switch(specificVehicleMenu.getOpcao()){
				case 1:	specificCar();
								executed = 1;
								break;

				case 2: specificVan();
								executed = 1;
								break;

				case 3: specificMotorBike();
								executed = 1;
								break;
			}
		}while(specificVehicleMenu.getOpcao()!=0 && executed == 0);
	}

	private void specificCar(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestCar(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestCar(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestCar(this.client.clone()).enqueue(this.client.clone());
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeCar(this.client.clone());
					if(t!=null){
						System.out.println("Taxi a caminho!");
						makeTrip(t.clone());
						System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
						answer = read.nextLine();
						if(answer.equals("Sim")) addFavorite(t.clone());
					}
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t);
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t);
			}
		}
		else System.out.println("Sem Carros neste momento");
	}

	private void specificVan(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestVan(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestVan(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestVan(this.client.clone()).enqueue(this.client.clone());
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeVan(this.client.clone());
					if(t!=null){
						System.out.println("Taxi a caminho!");
						makeTrip(t);
						System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
						answer = read.nextLine();
						if(answer.equals("Sim")) addFavorite(t);
					}
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t);
				System.out.println("Pretende adicionar o Taxi aos favoritos?");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t);
			}
		}
		else System.out.println("Sem Carrinhas neste momento");
	}

	private void specificMotorBike(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestMotorBike(this.client.clone())!=null){
			Taxi t = this.taxiCompany.getClosestMotorBike(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestMotorBike(this.client.clone()).enqueue(this.client.clone());
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi-carro livre mais próximo...");
					t = this.taxiCompany.getClosestFreeMotorBike(this.client.clone());
					if(t!=null){
						System.out.println("Taxi a caminho!");
						makeTrip(t);
						System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
						answer = read.nextLine();
						if(answer.equals("Sim")) addFavorite(t);
					}
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t);
				System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t);
			}
		}
		else System.out.println("Sem Motas neste momento");
	}

	private void closestTaxi(){
		String answer;
		Scanner read = new Scanner(System.in);
		if(this.taxiCompany.getClosestTaxi(this.client.clone()) != null){
			Taxi t = this.taxiCompany.getClosestTaxi(this.client.clone());
			if(t.isOccupied() == true){
				System.out.println("O taxi-carro mais próximo está ocupado. Deseja ir para a fila de espera? [Sim/Não]\n Caso não queira, será chamado o taxi-carro livre mais próximo.");
				answer = read.nextLine();
				if(answer.equals("Sim")) this.taxiCompany.getClosestTaxi(this.client.clone()).enqueue(this.client.clone());
				if(answer.equals("Não")) {
					System.out.println("A encontrar taxi livre mais próximo...");
					t = this.taxiCompany.getClosestFreeTaxi(this.client.clone());
					if(t!=null){
						System.out.println("Taxi a caminho!");
						makeTrip(t.clone());
						System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
						answer = read.nextLine();
						if(answer.equals("Sim")) addFavorite(t.clone());
					}
				}
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t.clone());
				System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}
		else System.out.println("Sem taxis");
	}


	private void specificDriver(){
		Scanner read = new Scanner(System.in);
		String email, answer;
		Taxi t = null;
		boolean bool;
		bool = this.taxiCompany.printDrivers();
		if(bool == true){email = this.taxiCompany.writeEmail();}
		else return;
		try{
			try{
			Iterator<Taxi> it = this.taxiCompany.getTaxis().iterator();
			int flag=0;
			while(it.hasNext() && flag==0) {
				t = it.next();
				if(t.getDriver().getEmail().equals(email)) flag=1;
			}
			if(t.isOccupied() == true){
				System.out.println("Este taxi está ocupado. Deseja ir para fila de espera? [Sim/Não]");
				answer = read.nextLine();
				if(answer.equals("Sim")) t.enqueue(this.client.clone());
			}
			else {
				System.out.println("Taxi a caminho!");
				makeTrip(t.clone());
				System.out.println("Pretende adicionar o Taxi aos favoritos? [Sim/Não]");
				answer = read.nextLine();
				if(answer.equals("Sim"))
				addFavorite(t.clone());
			}
		}catch(InputMismatchException e){System.out.println("E-mail é uma string");}
		}catch(NoTaxisException e){
			System.out.println("Sem Taxis");
		}
	}

	private void makeTrip(Taxi t){
		Scanner read = new Scanner(System.in);
		String answer;
		double note, price;
		this.client.callTaxi(t);
		t.goToNextClient();
		t.pickUpClient();
		price = t.rideStart(taxiCompany.getTraffic(t));
		t.rideEnd();
		System.out.println("Deseja atribuir uma nota ao motorista?");
		answer = read.nextLine();
		if(answer.equals("Sim")){
			System.out.println("Avaliação: ");
			note = read.nextDouble();
			t.getDriver().addEvaluation(note);
			t.getDriver().updateEvaluation();
		}
		this.taxiCompany.updateClient(t.getClient());
		this.taxiCompany.updateDriver(t.getDriver());
		t.clientOut();
		double actual = this.taxiCompany.getTotalProfit();
		price+=actual;
		this.taxiCompany.setTotalProfit(price);

	}

	private void showHistory(){
		switch(this.userType){
			case 1: this.client.printHistory(); break;
			case 2: this.driver.printHistory(); break;
		}
	}

	private void addFavorite(Taxi t){
		do{
			favoriteMenu.executaFavoriteMenu();
			switch(favoriteMenu.getOpcao()){
				case 1: addFavoriteDriver(t); break;
				case 2: addFavoriteVehicle(t); break;
			}
		}while(favoriteMenu.getOpcao() != 0);
	}

	private void addFavoriteDriver(Taxi t){
		try{
			this.client.addDriver(t.getDriver());
			System.out.println("Motorista adicionado aos favoritos!");
		}catch(UserExistsException e){
			System.out.println("Motorista existente");
		}
	}

	private void addFavoriteVehicle(Taxi t){
		try{
			this.client.addVehicle(t.getVehicle());
			System.out.println("Veículo adicionado aos favoritos!");
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
		Taxi t = this.taxiCompany.startDay(this.driver.clone());
		if(t != null)
			return t;
		else return null;
	}

	private void endWork(){
		this.taxiCompany.endDay(this.driver.clone());
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

		signUpVehicleMenu.executaSignUpVehicleMenu();
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
			signUpCar(c.clone());
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;

			case 2: Van v = new Van(avgSpeed, factor, plate);
			signUpVan(v.clone());
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;

			case 3: MotorBike m = new MotorBike(avgSpeed, factor, plate);
			signUpMotorBike(m.clone());
			nv = this.taxiCompany.getNVehicles();
			nv++;
			this.taxiCompany.setNVehicles(nv);
			break;
		}
	}

	private void signUpCar(Car c){
		try{
			this.taxiCompany.addVehicle(c.clone());
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void signUpVan(Van v){
		try{
			this.taxiCompany.addVehicle(v.clone());
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void signUpMotorBike(MotorBike m){
		try{
			this.taxiCompany.addVehicle(m.clone());
		}
		catch(VehicleExistsException e){
			System.out.println("Problem while adding vehicle");
		}
	}

	private void startTaxiRide(Taxi t){
		if(t!=null)
			t.rideStart(this.taxiCompany.getTraffic(t));
	}

	private void finishTaxiRide(Taxi t){
		if(t!=null){
			t.rideEnd();
			this.taxiCompany.updateClient(t.getClient());
			this.taxiCompany.updateDriver(t.getDriver());
			t.clientOut();
		}
		else System.out.println("Sem Cliente");
	}

	private void top10Clients(){
		try{
			if(this.taxiCompany.getClients().size() >= 10){
				int i=0;
				TreeSet<Client> top = new TreeSet<Client>(new ClientComparator());
				for(Client c : taxiCompany.getClients().values()){
					top.add(c.clone());
				}
				Iterator<Client> it = top.descendingIterator();
				Client c;
				while(it.hasNext() && i<10){
					i++;
					c = it.next();
					System.out.println(c.toString());
				}
			}
		}catch(NoClientsException e){System.out.println("Sem Clientes suficientes");}
	}

	private void worst5Drivers(){
		try{
			if(this.taxiCompany.getDrivers().size()>=5){
				int i=0;
				TreeSet<Driver> top = new TreeSet<Driver>(new DriverComparator());
				for(Driver d : taxiCompany.getDrivers().values()){
					top.add(d);
				}
				Iterator<Driver> it = top.iterator();
				Driver d;
				while(it.hasNext() && i<5){
					i++;
					d = it.next();
					System.out.println(d.toString());
				}
			}
		}catch(NoDriversException e){System.out.println("Sem motoristas");}
	}

	public void runProfitMenu(){
		do{
			profitMenu.executaProfitMenu();
			switch(profitMenu.getOpcao()){
				case 1: getUMeRProfit();
								break;

				case 2: getTaxiProfit();
								break;
			}
		}while(profitMenu.getOpcao()!=0);
	}

	public void getUMeRProfit(){
		System.out.println(this.taxiCompany.getTotalProfit());
	}

	public void getTaxiProfit(){
		Boolean isEmpty = this.taxiCompany.printTaxis();
		if(isEmpty == false){
			System.out.println("Escolha um dos táxis acima: (Matrícula)");
			String plate = this.taxiCompany.writePlate();
			Taxi t = this.taxiCompany.getSpecificTaxi(plate);
			System.out.println(t.getTotalProfit());
		}
	}

	public void StartApp(){
		try {
				File f = new File("data");
				if (f.exists()){
					FileInputStream fis = new FileInputStream("data");
					ObjectInputStream ois = new ObjectInputStream(fis);
					this.taxiCompany = (UMeR) ois.readObject();
					ois.close();
					System.out.println("Successfully loaded state.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

	public void EndApp(){
			try {
				FileOutputStream fos = new FileOutputStream("data");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.taxiCompany);
				oos.flush();
				oos.close();
				System.out.println("Guardado!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
}
