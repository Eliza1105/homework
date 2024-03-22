package Patterns.Prototype;
//Реализуем копирование учебника биологии на принтере в ручную
//Можно реализовать с помощью уже готового метода Cloneable


/*
Преимущества:1. Позволяет клонировать объекты, не привязываясь к их конкретным классам.
2.Меньше повторяющегося кода инициализации объектов.
3.Ускоряет создание объектов.
4.Альтернатива созданию подклассов для конструирования сложных объектов.
Недостатки: Сложно клонировать составные объекты, имеющие ссылки на другие объекты.

Взаимодействие с другими шаблонами:
-Многие архитектуры начинаются с применения Фабричного метода (более простого и расширяемого через подклассы) и эволюционируют в сторону Абстрактной фабрики, Прототипа или Строителя (более гибких, но и более сложных).
-Классы Абстрактной фабрики чаще всего реализуются с помощью Фабричного метода, хотя они могут быть построены и на основе Прототипа.
-Если Команду нужно копировать перед вставкой в историю выполненных команд, вам может помочь Прототип.
-Архитектура, построенная на Компоновщиках и Декораторах, часто может быть улучшена за счёт внедрения Прототипа. Он позволяет клонировать сложные структуры объектов, а не собирать их заново.
-Прототип не опирается на наследование, но ему нужна сложная операция инициализации. Фабричный метод, наоборот, построен на наследовании, но не требует сложной инициализации.
-Снимок иногда можно заменить Прототипом, если объект, состояние которого требуется сохранять в истории, довольно простой, не имеет активных ссылок на внешние ресурсы либо их можно легко восстановить.
-Абстрактная фабрика, Строитель и Прототип могут быть реализованы при помощи Одиночки. */
public class PrototypeTest {
    public static void main(String[] args) {
Textbook biology = new Textbook(468,"Zoology");

        System.out.println(biology);

        //Textbook biologyClone = (Textbook) biology.copy();
        Printer printer = new Printer(biology);
        Textbook biologyClone = printer.cloneTexbook();
        System.out.println(biologyClone);
    }
}
interface Copyable{
    Object copy();
}
class Textbook implements Copyable{
    private int padgeNumber;
    private String nameOfParagraf;

    public Textbook(int padgeNumber, String nameOfParagraf) {
        this.padgeNumber = padgeNumber;
        this.nameOfParagraf = nameOfParagraf;
    }

    public int getPadgeNumber() {
        return padgeNumber;
    }

    public void setPadgeNumber(int padgeNumber) {
        this.padgeNumber = padgeNumber;
    }

    public String getNameOfParagraf() {
        return nameOfParagraf;
    }

    public void setNameOfParagraf(String nameOfParagraf) {
        this.nameOfParagraf = nameOfParagraf;
    }

    @Override
    public Object copy() {
        Textbook copy =new Textbook(padgeNumber, nameOfParagraf);
        return copy;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "padgeNumber=" + padgeNumber +
                ", nameOfParagraf='" + nameOfParagraf + '\'' +
                '}';
    }
}
class Printer{
    Textbook textbook;

    public Printer(Textbook textbook) {
        this.textbook = textbook;
    }

    public void setTextbook(Textbook textbook) {
        this.textbook = textbook;
    }

    Textbook cloneTexbook(){
        return (Textbook) textbook.copy();
    }
}
