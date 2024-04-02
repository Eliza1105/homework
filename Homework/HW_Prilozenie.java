package Homework;
//Разработать приложение, генерирующее несколько последовательностей чисел (по заранее заданному алгоритму, например,
// последовательность чисел Фибоначчи, последовательность простых чисел,последовательность факториалов целых неотрицательных чисел).
// Генерирование типа последовательности и количество генерируемых элементов должно определяться пользователем. Для каждой
//последовательности после генерации указать время работы соответствующего алгоритма. Определение этого времени реализовать, используя шаблон проектирования «Decorator».
import java.util.Scanner;
public class HW_Prilozenie {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
        System.out.println("Для выбора типа последовательности (1-последовательность простых чисел, 2-последовательность факториалов целых неотрицательных чиселб 3- последовательность чисел Фибоначчи) введите цифру от 1 до 3: ");
        int type = scanner.nextInt();

        System.out.println("Введите количество элементов в последовательности: ");
        int n = scanner.nextInt();

        Sequence sequence = null;

        switch (type){
            case 1:
                sequence = new SequenceDecorator(new PrimeNumbers());
                break;
            case 2:
                sequence = new SequenceDecorator(new Factorials());
                break;
            case 3:
                sequence = new SequenceDecorator(new Febonaci());
                break;
            default:
                System.out.println("Неправильный тип последовательности.");
        }
        if (sequence!=null){
            sequence.generateSequence(n);
        }
    }
}
interface Sequence{
    public void generateSequence(int n);
}
class PrimeNumbers implements Sequence{
    private boolean isPrime(int num) {
    if (num <= 1) {
        return false;
    }
    for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
            return false;
        }
    }
    return true;
}
    @Override
    public void generateSequence(int n) {
        int count = 0;
        for (int i = 2; count < n; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
    }

}
class Factorials implements Sequence{
    @Override
    public void generateSequence(int n) {
for (int i =1; i<=n; i++){
    System.out.println(factorial(i)+" ");
}
    }
    private long factorial(int num){
       long fact = 1;
       for (int i =1; i<=num; i++){
           fact*=i;
       }
       return fact;
    }
}
class Febonaci implements Sequence{
    @Override
    public void generateSequence(int n) {
        long a=0, b=1;
        for (int i=0; i<=n; i++){
            System.out.println(a+" ");
            long temp = a;
            a=b;
            b=temp+b;
        }
    }
}
class SequenceDecorator implements Sequence{
    private Sequence sequence;

    public SequenceDecorator(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void generateSequence(int n) {
long startTime = System.nanoTime();
sequence.generateSequence(n);
long finishTime = System.nanoTime();
long time = (finishTime - startTime)/1000000;
        System.out.println("\n Время работы: "+time+ " миллисекунд(ы).");
    }
}