package ibf2022.batch2.ssf.frontcontroller.models;
import java.util.Random;

public class Captcha {
    private String question = "";
    private float answer = 0f;
    private float userAnswer = 0f;
    private int counter = 0;

    public Captcha() {
        Random rand = new Random();
        float num1 = rand.nextInt(50) + 1; // Random number between 1 and 50
        float num2 = rand.nextInt(50) + 1; // Random number between 1 and 50
        int operatorIndex = rand.nextInt(4); // Random index between 0 and 3
        char operator = ' ';
        switch (operatorIndex) {
            case 0:
                operator = '+';
                this.answer = num1 + num2;
                break;
            case 1:
                operator = '-';
                this.answer = num1 - num2;
                break;
            case 2:
                operator = '*';
                this.answer = num1 * num2;
                break;
            case 3:
                operator = '/';
                this.answer = num1 / num2;
                num1 = this.answer * num2; // Ensure that the question uses a whole number as the dividend
                break;
        }
        this.question = num1 + " " + operator + " " + num2 + " = ?";
        this.counter = 0;
    }

    public Captcha(String question, float userAnswer, int counter) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.counter = counter;
    }

    public Captcha(float userAnswer) {
        this.userAnswer = userAnswer;
    }


    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(float userAnswer) {
        // counter++;
        return userAnswer == this.answer;
    }

    public int getCounter() {return counter;}
    public void setCounter(int counter) {this.counter = counter;}

    public float getAnswer() {return answer;}
    public void setAnswer(float answer) {this.answer = answer;}

    public float getUserAnswer() {return userAnswer;}
    public void setUserAnswer(float userAnswer) {this.userAnswer = userAnswer;}

    public void increment() {
        counter++;
    }

}
