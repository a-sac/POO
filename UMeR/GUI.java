import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
* Write a description of class GUI here.
*
* @author jhbb
* @version (a version number or a date)
*/

public class GUI extends Application {

  Stage stage;
  Scene homeScene, loginScene, signupScene, clientRegisterScene, driverRegisterScene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Image logo = new Image("logo.png");
    ImageView viewLogo = new ImageView();
    viewLogo.setFitWidth(400);
    viewLogo.setImage(logo);
    viewLogo.setPreserveRatio(true);
    viewLogo.setSmooth(true);

    VBox homeV = new VBox(5);
    homeV.setPadding(new Insets(10));
    homeV.setSpacing(60);
    homeV.setAlignment(Pos.CENTER);

    Button loginBtn = new Button("Log In");
    loginBtn.setPrefSize(200, 40);
    loginBtn.setOnAction(e -> {
      stage.setScene(loginScene);
    });

    Button signupBtn = new Button("Sign Up");
    signupBtn.setPrefSize(200, 40);
    signupBtn.setOnAction(e -> {
      stage.setScene(signupScene);
    });

    homeV.setStyle("-fx-background-color: #FFFFFF;");
    homeV.getChildren().addAll(viewLogo, loginBtn, signupBtn);

    homeScene = new Scene(homeV, 400, 700);

    Button clientRegisterBtn = new Button("I'm a Client!");
    clientRegisterBtn.setPrefSize(200, 40);
    clientRegisterBtn.setOnAction(e -> {
      stage.setScene(clientRegisterScene);
    });
    Button driverRegisterBtn = new Button("I'm a Driver!");
    driverRegisterBtn.setPrefSize(200, 40);
    driverRegisterBtn.setOnAction(e -> {
      stage.setScene(driverRegisterScene);
    });

    VBox registerV = new VBox(20);
    registerV.setPadding(new Insets(10));
    registerV.setSpacing(30);
    registerV.setAlignment(Pos.CENTER);

    registerV.setStyle("-fx-background-color: #30ACA4;");
    registerV.getChildren().addAll(clientRegisterBtn, driverRegisterBtn);

    signupScene = new Scene(registerV, 400, 700);

    VBox clientRegV = new VBox(20);
    clientRegV.setPadding(new Insets(20));
    clientRegV.setSpacing(30);
    clientRegV.setAlignment(Pos.CENTER);

    Label titleClientReg = new Label("Let's make you \n     a client!");
    titleClientReg.setFont(Font.font(30));

    Label name = new Label("Nome    \t");
    TextField nameTf = new TextField();

    Label email = new Label("Email   \t");
    TextField emailTf = new TextField();

    Label password = new Label("Pass    \t");
    PasswordField passwordPf = new PasswordField();
    passwordPf.setPromptText("Password");

    Label confpwd = new Label("Confirm \t");
    PasswordField confpwdPf = new PasswordField();
    confpwdPf.setPromptText("Confirm Password");

    Label address = new Label("Address \t");
    TextField addressTf = new TextField();

    Label date = new Label("Birthday \t");
    TextField day = new TextField();
    day.setPromptText("dd");
    day.setPrefWidth(42);
    TextField month = new TextField();
    month.setPromptText("mm");
    month.setPrefWidth(42);
    TextField year = new TextField();
    year.setPromptText("yyyy");
    year.setPrefWidth(46);

    HBox names = new HBox(20);
    names.getChildren().addAll(name, nameTf);

    HBox emails = new HBox(20);
    emails.getChildren().addAll(email, emailTf);

    HBox passwords = new HBox(20);
    passwords.getChildren().addAll(password, passwordPf);

    HBox confpwds = new HBox(20);
    confpwds.getChildren().addAll(confpwd, confpwdPf);

    HBox addresses = new HBox(20);
    addresses.getChildren().addAll(address, addressTf);

    HBox dates = new HBox(20);
    dates.getChildren().addAll(date, day, month, year);

    Button register = new Button("Register");
    register.setFont(Font.font(30));
    register.setPrefWidth(340);

    clientRegV.setStyle("-fx-background-color: #30ACA4;");
    clientRegV.getChildren().addAll(titleClientReg, names, emails, passwords, confpwds, addresses, dates, register);

    clientRegisterScene = new Scene(clientRegV, 400, 700);

    Label titleDriverReg = new Label("Let's make you \n     a driver!");
    titleDriverReg.setFont(Font.font(30));

    VBox driverRegV = new VBox(20);
    driverRegV.setPadding(new Insets(20));
    driverRegV.setSpacing(30);
    driverRegV.setAlignment(Pos.CENTER);
    driverRegV.setStyle("-fx-background-color: #30ACA4");
    driverRegV.getChildren().addAll(titleDriverReg, names, emails, passwords, confpwds, addresses, dates, register);

    driverRegisterScene = new Scene(driverRegV, 400, 700);

    VBox loginV = new VBox(20);
    loginV.setPadding(new Insets(10));
    loginV.setSpacing(30);
    loginV.setAlignment(Pos.CENTER);

    loginV.setStyle("-fx-background-color: #E95334");
    loginV.getChildren().addAll(emails, passwords);

    loginScene = new Scene(loginV, 400, 700);

    stage.setTitle("UMeR");
    stage.setScene(homeScene);
    stage.show();
  }
}

// ORANGE: #E95334
// BLUE  : #30ACA4
