package Patterns.Singleton;

public class SingletonTest {
    public static void main(String[] args) {
        Singletone singletone = Singletone.getInstance();
        System.out.println(singletone.toString());
    }
}
class Singletone{
    static  Singletone instance = new Singletone();
    private Singletone(){

    }
    public static Singletone getInstance(){
        return instance;
    }
}
