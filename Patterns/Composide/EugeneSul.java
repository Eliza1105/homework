package Patterns.Composide;

import java.util.ArrayList;
import java.util.List;

public class EugeneSul {
    public static void main(String[] args) {
Team team = new Team();

Developer firstDeveloper = new JavaDeveloper();
Developer secondDeveloper = new JavaDeveloper();
Developer cppDeveloper = new CppDeveloper();

team.addDeveloper(firstDeveloper);
team.addDeveloper(secondDeveloper);
team.addDeveloper(cppDeveloper);

team.createProject();
    }
}
interface Developer {
    public void writeCode();
}
class JavaDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
class CppDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Cpp developer writes Cpp code...");
    }
}
class Team {
    private List<Developer> developers = new ArrayList<>();
  public void addDeveloper(Developer developer){
      developers.add(developer);
  }
  public void removeDeveloper(Developer developer){
      developers.remove(developer);
  }
  public void  createProject(){
      System.out.println("Team create project...");
      for (Developer developer:developers){
          developer.writeCode();
      }
  }
}
