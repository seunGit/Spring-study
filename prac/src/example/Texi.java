package example;

public class Texi {

    private int texiNum;

    private int gas;

    private int person;

    private int maxpeople;

    private int fee;

    private int money;

    private int basicfee;

    public Texi(int texiNum) {
        this.texiNum = texiNum;
        this.person = 0;
        this.gas = 0;
        this.maxpeople = 5;
        this.fee = 1000;
        this.money = 0;
        this.basicfee = 3000;

    }

    public void addPerson(int person) {
        this.person += person;
        this.money +=person + this.basicfee;
        if (this.person > this.maxpeople) {
            System.out.println("person = " + "정원이 초과되었습니다.");
        }
    }

    public void addGas(int gas) {
        this.gas += gas;
    }

}


