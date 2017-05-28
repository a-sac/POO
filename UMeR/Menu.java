
import java.io.Serializable;
/**
 * Esta classe implementa um menu em modo texto.
 *
 * @author Josá Creissac Campos
 * @version v2.1 (20170504)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu implements Serializable{
    // variáveis de instância
    private List<String> opcoes;
    private int op;
    private int choiceMenu;

    /**
     * Colours for menu
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     *
     */
    public void executaHomeMenu() {
        this.choiceMenu = 1;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaClientMenu() {
        this.choiceMenu = 2;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaDriverMenu() {
        this.choiceMenu = 3;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaSubDriverMenu() {
        this.choiceMenu = 4;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaSignUpMenu() {
        this.choiceMenu = 5;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaCallTaxiMenu() {
      this.choiceMenu = 6;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaFavoriteMenu() {
      this.choiceMenu = 7;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaSignUpVehicleMenu() {
      this.choiceMenu = 8;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaAdminMenu() {
      this.choiceMenu = 9;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void executaSpecificVehicleMenu() {
      this.choiceMenu = 10;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        switch(this.choiceMenu){
          case 1: System.out.println(ANSI_RED + "\n*** Bem-vindo à UMeR ***" + ANSI_RESET);
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 2: System.out.println(ANSI_RED + "\n*** Cliente ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 3: System.out.println(ANSI_RED + "\n*** Motorista ***" + ANSI_RESET);
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 4: System.out.println(ANSI_RED + "\n*** Trabalho ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 5: System.out.println(ANSI_RED + "\n*** Registo ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 6: System.out.println(ANSI_RED + "\n*** Chamar Taxi ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 7: System.out.println(ANSI_RED + "\n*** Favoritos ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 8: System.out.println(ANSI_RED + "\n*** Registar Veículo ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 9: System.out.println(ANSI_RED + "\n*** Administrador ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

          case 10: System.out.println(ANSI_RED + "\n*** Veículo Específico ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print(ANSI_WHITE + (i+1) + ANSI_RESET);
              System.out.print(ANSI_WHITE + " - " + ANSI_RESET);
              System.out.println(ANSI_WHITE + this.opcoes.get(i) + ANSI_RESET);
          }
          System.out.println(ANSI_WHITE + "0 - Sair" + ANSI_RESET);
          break;

        }

    }

    /** Ler uma opção válida */
    private int lerOpcao() {
        int op;
        Scanner is = new Scanner(System.in);

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }
}
