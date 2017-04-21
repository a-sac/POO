
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

    public Driver(Driver d){
      this.email = d.email;
      this.password = d.password;
      this.name = d.name;
      this.address = d.address;
      this.birthday = d.birthday;
      this.trustFactor = d.trustFactor;
      this.evaluation = d.evaluation;
      this.kms = d.kms;
    }

    public Driver(String email, String password, String name, String address, String birthday, double factor, double evaluation, double kms){
      this.email = email;
      this.password = password;
      this.name = name;
      this.address = address;
      this.birthday = birthday;
      this.trustFactor = factor;
      this.evaluation = evaluation;
      this.kms = kms;
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

    public void addKms(double kms){
      this.kms += kms;
    }

    public Driver clone(){
      return new Driver(this);
    }
}
