package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Label questionLabel;

    @FXML
    protected ToggleGroup atsakymai;

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

        Toggle toggle = atsakymai.getSelectedToggle();
        Question question = randomQuestion.get(selected);
        if(question.getCorrectAns() == (int)toggle.getUserData()){
            countAnswers++;
        }
        Question nextQuestion = randomQuestion.get(++selected);
        if(nextQuestion!= null){
            setQuestionToGui(nextQuestion);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Jus atsakete teisingai i " + countAnswers + " klausimus is "+randomQuestion.size());
            alert.show();
        }
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