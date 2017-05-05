import java.util.*;

/**
* Write a description of class Person here.
*
* @author jhbb
* @version 02/05
*/
public abstract class Person{

  private String email;
  private String password;
  private String name;
  private String address;
  private String birthday;
  private Map<Date, List<TaxiRide>> history;

  public Person(String email, String password, String name, String address, String birthday){
    this.email = email;
    this.password = password;
    this.name = name;
    this.address = address;
    this.birthday = birthday;
    this.history = new TreeMap<Date, List<TaxiRide>>();
  }

  public Person(Person p){
    this.email = p.getEmail();
    this.password = p.getPassword();
    this.name = p.getName();
    this.address = p.getAddress();
    this.birthday = p.getBirthday();
    this.history = p.getHistory();
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

  public Map<Date, List<TaxiRide>> getHistory(){
    Map<Date, List<TaxiRide>> neo = new TreeMap<Date, List<TaxiRide>>();
    for(Map.Entry<Date, List<TaxiRide>> entrys : this.history.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public void addToHistory(Date d, TaxiRide tr){
    System.out.println("containsKey: " + this.history.containsKey(d));
    if(this.history.containsKey(d)){
      this.history.get(d).add(tr.clone());
    } else {
      List<TaxiRide> neo = new LinkedList<TaxiRide>();
      neo.add(tr.clone());
      this.history.put(d, neo);
      System.out.println("After inserting is it empty? " + this.history.size());
    }
  }

  public abstract Person clone();

  public String toString(){
    return this.name + " and his/her email is " + this.email + " - " + this.history.size();
  }

  public void printHistory(){
    Set<Map.Entry<Date, List<TaxiRide>>> t = this.history.entrySet();
    System.out.println("Is the EntrySet of the history empty when we try to print it? " + this.history.size());
    for(Map.Entry<Date, List<TaxiRide>> elem : t){
      System.out.println("TaxiRides in " + elem.getKey().toString());
      for(TaxiRide tr : elem.getValue()){
        System.out.println(tr.toString());
      }
    }
  }
}
