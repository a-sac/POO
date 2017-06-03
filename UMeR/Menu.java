
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

    public void executaProfitMenu() {
      this.choiceMenu = 11;
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        switch(this.choiceMenu){
          case 1: System.out.println("\n*** Bem-vindo à UMeR ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 2: System.out.println("\n*** Cliente ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 3: System.out.println("\n*** Motorista ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 4: System.out.println("\n*** Trabalho ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println( "0 - Sair");
          break;

          case 5: System.out.println("\n*** Registo ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println( "0 - Sair");
          break;

          case 6: System.out.println("\n*** Chamar Taxi ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println( "0 - Sair");
          break;

          case 7: System.out.println("\n*** Favoritos ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 8: System.out.println("\n*** Registar Veículo ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 9: System.out.println("\n*** Administrador ***");
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
          break;

          case 10: System.out.println( "\n*** Veículo Específico ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - ");
              System.out.println(this.opcoes.get(i));
          }
          System.out.println( "0 - Sair");
          break;

          case 11: System.out.println("\n*** Total Faturado ***" );
          for (int i=0; i<this.opcoes.size(); i++) {
              System.out.print((i+1));
              System.out.print(" - " );
              System.out.println(this.opcoes.get(i));
          }
          System.out.println("0 - Sair");
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
