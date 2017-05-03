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
  private Map<String, List<TaxiRide>> history;

  public Person(String email, String password, String name, String address, String birthday){
    this.email = email;
    this.password = password;
    this.name = name;
    this.address = address;
    this.birthday = birthday;
    this.history = new TreeMap<String, List<TaxiRide>>();
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

  public Map<String, List<TaxiRide>> getHistory(){
    Map<String, List<TaxiRide>> neo = new TreeMap<String, List<TaxiRide>>();
    for(Map.Entry<String, List<TaxiRide>> entrys : neo.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public void addToHistory(String id, TaxiRide tr){
    List<TaxiRide> current = this.history.get(id);
    if(current != null){
      current.add(tr.clone());
    } else {
      List<TaxiRide> neo = new LinkedList<TaxiRide>();
      neo.add(tr.clone());
      this.history.put(id, neo);
    }
  }

  public abstract Person clone();

  public String toString(){
    return this.name + " and his/her email is " + this.email;
  }

}
