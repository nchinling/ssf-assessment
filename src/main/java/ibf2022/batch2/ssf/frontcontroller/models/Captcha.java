package ibf2022.batch2.ssf.frontcontroller.models;

public class Captcha {
    private int num1;
    private int num2;
    private String operator;
    private int counter;

    
    public Captcha() {}
    
    public Captcha(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public Captcha(int counter) {this.counter = counter;}

    public Captcha(int num1, int num2, String operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public int getNum1() {return num1;}
    public void setNum1(int num1) {this.num1 = num1;}
    
    public int getNum2() {return num2;}
    public void setNum2(int num2) {this.num2 = num2;}

    public String getOperator() {return operator;}
    public void setOperator(String operator) {this.operator = operator;}

    public int getCounter() {return counter;}
    public void setCounter(int counter) {this.counter = counter;}


    //to keep track of login attempts
    public void increment(){this.counter++;}



    
}
