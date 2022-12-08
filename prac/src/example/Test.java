package example;

public class Test {
    public static void main(String[] args) {
        Bus bus1 = new Bus(1000);               // 생성자 호출
        Bus bus2 = new Bus(500);

        bus1.addPerson(2);
        bus1.addPerson(1);
        bus1.addGas(30);
        bus1.addGas(20);
        bus1.addPerson(30);
    }
}
