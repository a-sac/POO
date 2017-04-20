
/**
 * Escreva a descrição da classe Driver aqui.
 *
 * @author tdaa
 * @version 20/04
 */
public class Driver{
    private String email;
    private String password;
    private String name;
    private String address;
    private String birthday;
    private double trustFactor;
    private double evaluation;
    private double kms;
    //to do Travel List;
    private boolean isWorking;

    public Driver(Driver d){
      this.email = d.email;
      this.password = d.password;
      this.name = d.name;
      this.address = d.address;
      this.birthday = d.birthday;
      this.factor = d.factor;
      this.evaluation = d.evaluation;
      this.kms = d.kms;
      this.isWorking = d.isWorking;
    }

    public Driver(String email, String password, String name, String address, String birthday, double factor, double evaluation, double kms, boolean isWorking){
      this.email = email;
      this.password = password;
      this.name = name;
      this.address = address;
      this.birthday = birthday;
      this.trustFactor = factor;
      this.evaluation = evaluation;
      this.kms = kms;
      this.isWorking = isWorking;
    }

    public String getEmail(){
      return this.email;
    }

    public String getPassword(){
      return this.password;
    }

    public String getName(){
      return this.name;
    }

    public String getAddress(){
      return this.address;
    }

    public String getBirthday(){
      return this.birthday;
    }

    public double getTrustFactor(){
      return this.trustFactor;
    }

    public double getEvaluation(){
      return this.evaluation;
    }

    public double getKms(){
      return this.kms;
    }

    public boolean getIsWorking(){
      return this.isWorking;
    }

    public Driver clone(){
      return new Driver(this);
    }
}
