package Patterns.Composide;

import java.util.ArrayList;
import java.util.List;

/*
Преимущества:1.Упрощает архитектуру клиента при работе со сложным деревом компонентов.
             2. Облегчает добавление новых видов компонентов.
Недостатки: Создаёт слишком общий дизайн классов.

Взаимодействие с другими шаблонами:
-Строитель позволяет пошагово сооружать дерево Компоновщика.
-Цепочку обязанностей часто используют вместе с Компоновщиком. В этом случае запрос передаётся от дочерних компонентов к их родителям.
-Можно обходить дерево Компоновщика, используя Итератор.
-Можно выполнить какое-то действие над всем деревом Компоновщика при помощи Посетителя.
-Компоновщик часто совмещают с Легковесом, чтобы реализовать общие ветки дерева и сэкономить при этом память.
-Компоновщик и Декоратор имеют похожие структуры классов из-за того, что оба построены на рекурсивной вложенности. Она позволяет связать в одну структуру бесконечное количество объектов.
Декоратор оборачивает только один объект, а узел Компоновщика может иметь много детей. Декоратор добавляет вложенному объекту новую функциональность, а Компоновщик не добавляет ничего нового, но «суммирует» результаты всех своих детей.
Но они могут и сотрудничать: Компоновщик может использовать Декоратор, чтобы переопределить функции отдельных частей дерева компонентов.
Архитектура, построенная на Компоновщиках и Декораторах, часто может быть улучшена за счёт внедрения Прототипа. Он позволяет клонировать сложные структуры объектов, а не собирать их заново.
 */
public class HW_Mila {
    public static void main(String[] args) {


PresentsComposide present1 = new Present("Shower gel", "Vanilla-passion fruit", 15);
PresentsComposide present2 = new Present("Hand cream", "Pomegranate-pear", 17);
PresentsComposide present3 = new Present("Bath foam", "Grade-hubuscus", 14);
PresentsComposide present4 = new Present("Shampoo", "Green tea", 10);

Bag bag1 = new Bag("Hand cream","Vanilla-passion fruit", 25 );
bag1.add(present1);
bag1.add(present2);
Bag bag2 = new Bag("Bath foam","Green tea", 20);
bag2.add(present3);
bag2.add(present4);

Box box = new Box();
box.addPresents(bag1);
box.addPresents(bag2);


bag1.packThings();
bag2.packThings();
box.packBox();

    }
}
interface Salesman{
    public void packThings();
}
class PresentsComposide implements Salesman{
    public String name;
    public String smell;
    public int price;

    public PresentsComposide(String name, String smell, int price) {
        this.name = name;
        this.smell = smell;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getSmell() {
        return smell;
    }

    public int getPrice() {
        return price;
    }
    @Override
    public void packThings() {
        System.out.println("В пакет упакован: "+ getName()+" с ароматом " + getSmell()+ " , по цене = "+getPrice());
    }
}

class Present extends PresentsComposide {
    public Present(String name, String smell, int price) {
        super(name, smell, price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSmell() {
        return smell;
    }

    @Override
    public int getPrice() {
        return price;
    }
    public void packThings(){
        System.out.println("Упакован: "+ getName()+" с ароматом " + getSmell()+ " , по цене = "+getPrice());
    }
}

class Bag extends PresentsComposide{
   private List<PresentsComposide> presents = new ArrayList<>();
    public Bag(String name, String smell, int price) {
        super(name, smell, price);
    }

    @Override
    public void packThings() {
        presents.forEach((present) -> {
            present.packThings();
        });
    }
    public void add(PresentsComposide presentsComposide){
        presents.add(presentsComposide);
    }
}
class Box {
    private List<PresentsComposide> presents = new ArrayList<>();

    public void addPresents(PresentsComposide present) {
        presents.add(present);
    }

    public void removePresents(PresentsComposide present) {
        presents.remove(present);
    }

    public void packBox() {
        System.out.println("Коробка упакована:");
        for (PresentsComposide presents1 : presents) {
            presents1.packThings();
        }
    }
}
