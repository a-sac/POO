import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.IOException;

public class Parser {
 

    public static void main(String args[]) throws FileNotFoundException  {

        FileInputStream fis = new FileInputStream("teste.txt");
        Scanner scanner = new Scanner(fis);
        System.out.println("Reading file line by line in Java using Scanner");
        
        try{
            PrintWriter writer = new PrintWriter("teste1.txt", "UTF-8");
            while(scanner.hasNextLine()){
            writer.println(scanner.nextLine());
            }
            writer.close();
        } catch (IOException e) {
        scanner.close();
        }
    }  
     
}