package ibf2022.batch2.ssf.frontcontroller.models;

import java.util.Random;

public class Captcha {
    private int num1;
    private int num2;
    private String stringEqn;
    private float result;
    private int counter = 0;
    private boolean flag = false;

    
    public Captcha() {}
    
    public Captcha(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public Captcha(int counter) {this.counter = counter;}

 
    public int getNum1() {return num1;}
    public void setNum1(int num1) {this.num1 = num1;}
    
    public int getNum2() {return num2;}
    public void setNum2(int num2) {this.num2 = num2;}

    

    public String getStringEqn() {return stringEqn;}
    public void setStringEqn(String stringEqn) {this.stringEqn = stringEqn;}

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCounter() {return counter;}
    public void setCounter(int counter) {this.counter = counter;}

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }



    //to keep track of login attempts
    public void increment(){this.counter++;}

    //implement random numbers generator
    public void generateEqn(){

        Random random = new Random();
        Random operatorChoice = new Random();
        String operatorSwitch;
        float correctAnswer = 0;

        int operator = operatorChoice.nextInt(4);
        this.num1 = random.nextInt(51);
        this.num2 = random.nextInt(51);

        switch (operator){

            case 0: operatorSwitch= "+";
                correctAnswer = this.num1+this.num2;
                break;
            case 1: operatorSwitch= "-";
                correctAnswer = this.num1-this.num2;
                break;
            case 2: operatorSwitch= "*";
                correctAnswer = this.num1*this.num2;
                break;
            case 3: operatorSwitch= "/";
                correctAnswer = this.num1/this.num2;
                break;
            default: operatorSwitch= "";
        }

        System.out.println("What is: "+this.num1+operatorSwitch+this.num2+"?");

        if(this.result != correctAnswer){
            System.out.println("Wrong answer! Right answer is: "+ correctAnswer);
        }
        else{
            System.out.println("Your answer is correct");
            this.flag = true; 
        }
    }
 

}

