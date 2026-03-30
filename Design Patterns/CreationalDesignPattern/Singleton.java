package CreationalDesignPattern;

public class Singleton {
  public static void main(String[] args) {
mySingleton1 obj11=mySingleton1.getInstance();
mySingleton1 obj21=mySingleton1.getInstance();

      System.out.println("---Not Thread Safe---");
      System.out.println(obj11+"====> "+obj21);

      mySingleton2 obj12=mySingleton2.getInstance();
      mySingleton2 obj22=mySingleton2.getInstance();
      System.out.println("---Not Thread Safe---");
      System.out.println(obj12+"====> "+obj22);

      mySingleton3 obj13=mySingleton3.getInstance();
      mySingleton3 obj23=mySingleton3.getInstance();
      System.out.println("---Using Volatile---");
      System.out.println(obj13+"====> "+obj23);

      mySingleton4 obj14=mySingleton4.getInstance();
      mySingleton4 obj24=mySingleton4.getInstance();
      System.out.println("---Eager Initialization---");
      System.out.println(obj14+"====> "+obj24);

      mySingleton5 obj15=mySingleton5.getInstance();
      mySingleton5 obj25=mySingleton5.getInstance();
      System.out.println("---Eager Initialization---");
      System.out.println(obj15+"====> "+obj25);

  }


}

//Not Thread Safe
class mySingleton1{
    private mySingleton1(){}
    private static mySingleton1 instance;
    public static mySingleton1 getInstance(){
        if(instance==null){
            instance= new mySingleton1();
        }
        return instance;
    }
}

//Thread Safe...
class mySingleton2{
    private static mySingleton2 instance;
    private mySingleton2(){}

    public static synchronized mySingleton2 getInstance(){
        if(instance==null){
            instance= new mySingleton2();
        }
        return instance;
    }
}

//Using Volatile to reflect the change across all object
class mySingleton3{
    private static volatile mySingleton3 instance;

    private mySingleton3(){}

    public static mySingleton3 getInstance(){
        if(instance==null) {
            synchronized (mySingleton3.class) {
                if (instance == null) {
                    instance = new mySingleton3();
                }
            }
        }
        return instance;
    }
}

//Simplest way using static and final
class mySingleton4{
    private static final mySingleton4 instance=new mySingleton4();
    private mySingleton4(){}

    public static mySingleton4 getInstance(){
        return instance;
    }
}

//Using static block

class mySingleton5{
    private static mySingleton5 instance;
    private mySingleton5(){}
    static {
        instance=new mySingleton5();
    }

    public static mySingleton5 getInstance(){
        return instance;
    }
}