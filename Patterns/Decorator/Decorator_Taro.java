package Patterns.Decorator;

public class Decorator_Taro {
    public static void main(String[] args) {
        Service taro = new Divination("Таро", 1000);
        Service chakra = new Chakra(taro);
        Service aura = new Aura(chakra);

        Service horoscope = new Horoscope("Телец", 2000);
        Service love = new LoveHoroscope(horoscope);

        // И общая стоимость
        System.out.println(aura.getPrice()+ " + "+ love.getPrice());
    }
}
interface Service {
    public double getPrice();

    public String getLabel();
}

 class Divination implements Service{
    private String label;
    private double price;

    public Divination(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return this.label;
    }

    public double getPrice() {
        return this.price;
    }
    }
 class Horoscope implements Service{
    private String label;
    private double price;

    public Horoscope(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return this.label;
    }
    public double getPrice() {
        return this.price;
    }
    }
class OptionDecorator implements Service {//класс для добавления доп сервисов
    private Service service;
    private String label;
    private double price;

    public OptionDecorator(Service service, String label, double price) {
        this.service = service;
        this.label = label;
        this.price = price;
    }

    public double getPrice() {
        return this.price + service.getPrice();
    }

    public String getLabel() {
        return this.label + service.getLabel();
    }
}
class Aura extends OptionDecorator {
    public Aura(Service service) {
        super(service, "Характеристика ауры", 1500);
    }
}
class Chakra extends OptionDecorator {
    public Chakra(Service service) {
        super(service, "Характеристика чакр", 500);
    }
}
class LoveHoroscope extends OptionDecorator{
    public LoveHoroscope(Service service){
        super(service, "Совместимость по любви", 3000);

    }
}



