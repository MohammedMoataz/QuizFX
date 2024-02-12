package sample;

import connectivity.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Label label;

    @FXML
    private RadioButton radio_1,
            radio_2,
            radio_3,
            radio_4;

    @FXML
    private Button button;

    private ToggleGroup group;

    private String[] questions,
            correct;

    private String[][] choices;

    private int counter = 0,
            qSize = 0,
            rate = 0;

    protected ObservableList<Data> data;

    @FXML
    private void initialize() throws SQLException {
        questions = new String[10];
        correct = new String[10];
        choices = new String[10][4];

//        WorkBook workBook = new WorkBook();
        DataBase database = new DataBase();
        ResultSet rs;

        group = new ToggleGroup();
        radio_1.setToggleGroup(group);
        radio_2.setToggleGroup(group);
        radio_3.setToggleGroup(group);
        radio_4.setToggleGroup(group);

//        questions = workBook.getColumn(0);
//        correct = workBook.getColumn(5);

        rs = database.setQuery("select * from questions");
        for (int i = 0; rs.next(); i++)
            questions[i] = rs.getString("question");

        rs = database.setQuery("select * from correctAnswers");
        for (int i = 0; rs.next(); i++)
            correct[i] = rs.getString("correctAnswer");

        choices = new String[10][4];
        for (int i = 0; i < 4; i++)
//            choices[i] = workBook.getColumn(i);

            rs = database.setQuery("select * from options");
        for (int i = 0; rs.next(); i++) {
            qSize++;
            choices[i][0] = rs.getString("option_1");
            choices[i][1] = rs.getString("option_2");
            choices[i][2] = rs.getString("option_3");
            choices[i][3] = rs.getString("option_4");
        }


        label.setText(questions[counter]);
        radio_1.setText(choices[counter][0]);
        radio_2.setText(choices[counter][1]);
        radio_3.setText(choices[counter][2]);
        radio_4.setText(choices[counter][3]);

        data = FXCollections.observableArrayList();
    }

    public void nextQuiz(ActionEvent eve) throws IOException {
        if (group.getSelectedToggle() != null && counter < qSize) {
            String answer = ((RadioButton) group.getSelectedToggle()).getText();

            data.add(new Data(answer, correct[counter]));

            if (answer.equals(correct[counter]))
                this.rate++;

            counter++;
            if (counter < qSize) {
                label.setText(questions[counter]);

                radio_1.setText(choices[counter][0]);
                radio_2.setText(choices[counter][1]);
                radio_3.setText(choices[counter][2]);
                radio_4.setText(choices[counter][3]);

                group.getSelectedToggle().setSelected(false);

                if (counter == qSize - 1)
                    this.button.setText("Result");
            }
        }

        if (counter == qSize) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
            Parent root = loader.load();

            Result result = loader.getController();
            result.setData(this.rate, data);

            Scene scene = ((Node) eve.getSource()).getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.close();

            scene = new Scene(root);
            stage.setScene(scene);

            stage.setTitle("Result");
            stage.setResizable(false);

            stage.show();
        }
    }
}
