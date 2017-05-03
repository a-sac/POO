
/**
 * Escreva a descrição da classe Driver aqui.
 *
 * @author tdaa/jhbb
 * @version 02/05
 */
public class Driver extends Person{

    private double trustFactor;
    private double evaluation;
    private double kms;

    public Driver(Driver d){
      super(d);
      this.trustFactor = d.getTrustFactor();
      this.evaluation = d.getEvaluation();
      this.kms = d.getKms();
    }

    public Driver(String email, String password, String name, String address, String birthday, double factor, double evaluation, double kms){
      super(email, password, name, address, birthday);
      this.trustFactor = factor;
      this.evaluation = evaluation;
      this.kms = kms;
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
