package example;

public class Bus {
    private int busNum;
    private int gas;
    private int person;

    private int fee;

    private int money;

    private int maxpeople;

    public Bus(int busNum) {
        this.busNum = busNum;
        this.gas = 0;
        this.person = 0;
        this.fee = 1000;
        this.money = 0;
        this.maxpeople = 30;
    }
    public void addPerson(int person) {
        this.person += person;
        // money = fee*person
        this.money += this.fee*person;

        if (this.person > this.maxpeople) {
            System.out.println("정원이 초과되었습니다.");
        }
        System.out.println(this.money);
        System.out.println(this.person);
    }

    public void addGas(int gas) {
        this.gas += gas;
        System.out.println(this.gas);
    }
    // 사람을 내리는 메서드를 만들어보고 택시로 연습. 실행.
    // 혼공자 23456

}
