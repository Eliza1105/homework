package Patterns.Composide;

import java.util.ArrayList;
import java.util.Iterator;

public class Harb {
    public static void main(String[] args) {
ContentsComponent components = new Contents("Сожержание",1);

ContentsComponent introduction = new Contents("1.Введение",1);
        ContentsComponent p1 = new Contents("2.Анатомия скелета",7);
        ContentsComponent p2 = new Contents("3.Топография органов",58);

        components.add(introduction);
        components.add(p1);
        components.add(p2);

        p1.add(new ContentsItem("2.1 Нервная система ", 27));
        p1.add(new ContentsItem("2.2 Сердечно-сосудистая система ", 35));
        p1.add(new ContentsItem("2.3 ЖКТ ", 55));

        p2.add(new ContentsItem("3.1 ОБП", 47));
        p2.add(new ContentsItem("3.2 рганы грудной клетки", 38));
        p2.add(new ContentsItem("3.3 Органы малого таза", 53));

        System.out.println("\"Сокращенная анатомия человека\" Александр Становенкоб 2007");

        Book book = new Book(components);
        book.printContends();
    }
}
abstract class ContentsComponent{
    public void add(ContentsComponent contentsComponent){
        throw new UnsupportedOperationException();
    }
    public String getName(){
        throw new UnsupportedOperationException();
    }
    public Integer getPages(){
        throw new UnsupportedOperationException();
    }
    public void print(){
        throw new UnsupportedOperationException();
    }
}
class Contents extends ContentsComponent{
    ArrayList<ContentsComponent> contentsComponents = new ArrayList<>();
    String name;
    Integer pages;


    public Contents(String name, Integer pages) {
        this.name = name;
        this.pages = pages;
    }
    public void add(ContentsComponent contentsComponent){
        contentsComponents.add(contentsComponent);
    }
    public String getName(){
        return name;
    }
    public Integer getPages(){
       return pages;
    }
    public void print(){
        System.out.println("\n"+getName());
        for (int i =0; i<70-getName().length();i++){
            System.out.print(".");
        }
        System.out.println(getPages());

        Iterator<ContentsComponent> iterator = contentsComponents.iterator(); //выводим название главы и номера страницы
        contentsComponents.stream()
                .takeWhile(x->iterator.hasNext())
                .map(n->iterator.next())
                .forEach(ContentsComponent::print);
    }
}

class ContentsItem extends ContentsComponent{
    String name;
    Integer pages;

    public ContentsItem(String name, Integer pages) {
        this.name = name;
        this.pages = pages;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPages() {
        return pages;
    }
    public void print(){ //выводим название параграфа и номера страницы
        System.out.println(" "+ getName());
        for (int i =0; i<68-getName().length(); i++){
            System.out.print(".");
        }
        System.out.println(getPages());
    }
}

class Book{
    ContentsComponent components;

    public Book(ContentsComponent components) {
        this.components = components;
    }
    public void printContends(){
        components.print();
    }
}

