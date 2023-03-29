package ibf2022.batch2.ssf.frontcontroller.models;
import java.util.Random;

public class Captcha {
    private String question = "";
    private int answer = 0;
    private int counter = 0;

    public Captcha() {
        Random rand = new Random();
        int num1 = rand.nextInt(50) + 1; // Random number between 1 and 50
        int num2 = rand.nextInt(50) + 1; // Random number between 1 and 50
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

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(int userAnswer) {
        counter++;
        return userAnswer == answer;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        counter++;
    }
}
