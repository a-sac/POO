import java.io.Serializable;
import java.util.*;
/**
 * Escreva a descrição da classe Driver aqui.
 *
 * @author tdaa/jhbb
 * @version 02/05
 */
public class Driver extends Person implements Serializable{

    private double trustFactor;
    private double actualEvaluation;
    private double kms;
    private double timeExceeded;
    private ArrayList<Double> evaluations;

    public Driver(Driver d){
      super(d);
      this.trustFactor = d.getTrustFactor();
      this.actualEvaluation = d.getActualEvaluation();
      this.kms = d.getKms();
      this.timeExceeded = d.getTimeExceeded();
      //this.clientsEvaluation = d.getClientsEvaluation();
      this.evaluations = new ArrayList<Double>();
    }

    public Driver(String email, String password, String name, String address, String birthday, double factor, double evaluation, double kms){
      super(email, password, name, address, birthday);
      this.trustFactor = factor;
      this.actualEvaluation = evaluation;
      this.kms = kms;
      //this.clientsEvaluation = 0;
      this.evaluations = new ArrayList<Double>();
    }

    public double getTrustFactor(){
      return this.trustFactor;
    }

    public double getActualEvaluation(){
      return this.actualEvaluation;
    }

    public double getKms(){
      return this.kms;
    }

    public double getTimeExceeded(){
      return this.timeExceeded;
    }

    public void setFactor(double factor){
      this.trustFactor = factor;
    }

    /*public int getClientsEvaluation(){
      return this.clientsEvaluation;
    }*/

    public ArrayList<Double> getEvaluations() throws NoEvaluationsException{
      if(this.evaluations.isEmpty()) throw new NoEvaluationsException("Sem Avaliações");
      else{
        ArrayList<Double> neo = new ArrayList<Double>();
        for(double d : this.evaluations){
          neo.add(d);
        }
        return neo;
      }
    }

    public void updateEvaluation(){
      double count=0;
      for(double d : this.evaluations){
        count+=d;
      }
      this.actualEvaluation = count/this.evaluations.size();
    }

    /*public void setClientsEvaluation(int n){
      this.clientsEvaluation = n;
    }*/

    public void setEvaluationsList(ArrayList<Double> list){
      for(double d : list){
        this.evaluations.add(d);
      }
    }

    public void addEvaluation(double note){
      this.evaluations.add(note);
    }

    public void addKms(double kms){
      this.kms += kms;
    }

    public void addTimeLost(double time){
      this.timeExceeded+=time;
    }

    public Driver clone(){
      Driver d = new Driver(this.getEmail(), this.getPassword(), this.getName(), this.getAddress(), this.getBirthday(), this.getTrustFactor(), this.getActualEvaluation(), this.getKms());
      try{
        d.setEvaluationsList(this.getEvaluations());
      }catch(NoEvaluationsException e){
        System.out.println("Sem avaliações");
      }
      return d;
    }

}
