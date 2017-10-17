package sample;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andriusbaltrunas on 10/16/2017.
 */
public class Question {

    private String question;
    private int correctAns;
    private List<String> tempAnswers;
    private boolean answeredCorrect;

    public Question(String question, int correctAns, String... tempAnswer) {
        this.question = question;
        this.correctAns = correctAns;
        this.tempAnswers = Arrays.asList(tempAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public List<String> getTempAnswers() {
        return tempAnswers;
    }

    public boolean isAnsweredCorrect() {
        return answeredCorrect;
    }

    public void setAnsweredCorrect(boolean answeredCorrect) {
        this.answeredCorrect = answeredCorrect;
    }
}