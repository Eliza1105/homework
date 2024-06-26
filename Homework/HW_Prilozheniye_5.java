package Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//написать приложени, выводящее список товаров мебельного магазина, отсортированного по цене. У каждого товара должны быть заданы: категория,
// название, артикул (уникальный код) и цена. Для мебели в категории «шкафы» необходимо дополнительно указывать тип дверей (обычные, купе,
// стеклянные). Для мебели категории «стол» необходимо указывать количество выдвижных ящиков. Для мебели категории «стул» необходимо указывать
// наличие спинки. Кроме продажи отдельных предметов мебели в магазине могут продаваться наборы со скидкой 5% от общей стоимости всех предметов
// мебели этого набора. Подсчёт стоимости набора мебели организовать через шаблон проектирования «Composite».
public class HW_Prilozheniye_5 {
    public static void main(String[] args) {
        List<StoreGoods> storeGoods = new ArrayList<>();
        storeGoods.add(new Cabinets("Стефани","254674",249,"Купе"));
        storeGoods.add(new Cabinets("Верона","467459",315,"Обычная"));
        storeGoods.add(new Tables("Кельн","466939",199,3));
        storeGoods.add(new Tables("Токио","204958",176,5));
        storeGoods.add(new Chairs("Гарден","946321",83,true));
        storeGoods.add(new Chairs("Вита","798651",69,false));

        List<StoreGoods> storeSet1 = new ArrayList<>();
        storeSet1.add(new Tables("Кельн","466939",199,3));
        storeSet1.add(new Chairs("Гарден","946321",83,true));
        storeSet1.add(new Chairs("Гарден","946321",83,true));
        storeGoods.add(new Sets("Набор 1","576321", storeSet1));

        Collections.sort(storeGoods,(f1,f2)->Double.compare(f1.getPrice(),f2.getPrice()));


     for (StoreGoods good : storeGoods) {
            System.out.println(storeGoods);
     }
    }
}

interface Saleman{
    String toString();
}
abstract class StoreGoods implements Saleman{  // в некоторых источниках рекомендуют сделать класс абстрактным
    String category;
    String name;
    String vendorCode;
     double price;

    public StoreGoods(String category, String name, String vendorCode, double price) {
        this.category = category;
        this.name = name;
        this.vendorCode = vendorCode;
        this.price = price;
    }
    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public double getPrice(){
        return price;
    }
    @Override
    public String toString() {
        return category + ":" + name + ", (Арт. " + vendorCode + " ), цена:  " + price;
    }
}
class Cabinets extends StoreGoods{  //Класс для шкафов
    private String doorType;
    public Cabinets( String name, String vendorCode, double price, String doorType) {
        super("Шкафы", name, vendorCode, price);
        this.doorType = doorType;
    }
    @Override
    public String toString() {
        return super.toString()+" , тип дверей: "+doorType;
    }
}
class Tables extends StoreGoods{  // Класс для столов
    private int sufliadka;

    public Tables(String name, String vendorCode, double price, int sufliadka) {
        super("Столы", name, vendorCode, price);
        this.sufliadka = sufliadka;
    }
    @Override
    public String toString() {
        return super.toString()+" ,количество выдвижных ящиков: "+ sufliadka;
    }
}

class Chairs extends StoreGoods{
    private boolean hasBackrest;

    public Chairs(String name, String vendorCode, double price, boolean chairBack) {
        super("Стулья", name, vendorCode, price);
        this.hasBackrest = chairBack;
    }
    @Override
    public String toString() {
        return super.toString()+" ,наличие спинки:  "+(hasBackrest?"есть":"нет");
    }
}

class Sets extends StoreGoods{
    private List<StoreGoods> goods;

    public Sets(String name, String vendorCode, List<StoreGoods> goods) {
        super("Наборы", name, vendorCode, calculateSetPrice(goods));
        this.goods = goods;
    }

    private static double calculateSetPrice(List<StoreGoods> goods) {
        double totalPrice = 0;
        for (StoreGoods good : goods) {
            totalPrice += good.getPrice();
        }
        return totalPrice * 0.95; // Скидка 5% для набора
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("").append(name).append(" Арт. ").append(vendorCode).append("\n");
        for (StoreGoods good : goods){
            sb.append("- ").append(good.toString()).append("\n");
        }
        sb.append("Общая цена набора с учетом скидки: ").append(price);
        return sb.toString();
    }
}
