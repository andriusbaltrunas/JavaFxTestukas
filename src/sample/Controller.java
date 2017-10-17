package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Label questionLabel;

    @FXML
    protected ToggleGroup answers;

    @FXML
    private RadioButton firstAnswer;

    @FXML
    private RadioButton secondAnswer;

    @FXML
    private RadioButton thirdAnswer;

    private Map<Integer, Question> randomQuestion = new LinkedHashMap<>();

    private int selected = 1;

    private int countAnswers = 0;

    public void answer(ActionEvent event) {

        Toggle toggle = answers.getSelectedToggle();
        if (toggle != null) {
          checkAnswer(toggle);
        }else{
            showErrorMessage();
        }
    }


    private void checkAnswer(Toggle toggle){
        Question question = randomQuestion.get(selected);
        if (question.getCorrectAns() == (int) toggle.getUserData()) {
            countAnswers++;
        }
        Question nextQuestion = randomQuestion.get(++selected);
        if (nextQuestion != null) {
            setQuestionToGui(nextQuestion);
        } else {
            showTotalScore();
        }
    }

    private void showErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select correct answer");
        alert.show();
    }
    
    private void showTotalScore(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Jus atsakete teisingai i " + countAnswers + " klausimus is " + randomQuestion.size());
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                System.exit(0);
            }
        });
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadFile readFile = new ReadFile();
        List<Question> questions = readFile.read();
        generateQuestions(questions);
        setQuestionToGui(randomQuestion.get(selected));

    }

    private void setQuestionToGui(Question question) {
        List<RadioButton> radioButtons = Arrays.asList(firstAnswer, secondAnswer, thirdAnswer);
        int temVal = 1;
        questionLabel.setText(question.getQuestion());
        for(int i = 0; i<radioButtons.size(); i++){
            radioButtons.get(i).setText(question.getTempAnswers().get(i));
            radioButtons.get(i).setUserData(temVal++);
        }
        Toggle toggle = answers.getSelectedToggle();
        if(toggle != null){
            toggle.setSelected(false);
        }
    }

    private void generateQuestions(List<Question> questions) {
        int val = 1;
        while (randomQuestion.size() != (questions.size()/2)) {
            Random random = new Random();
            int index = random.nextInt(questions.size());
            if (randomQuestion.get(index) == null) {
                randomQuestion.put(val++, questions.get(index));
            }
        }
    }
}
