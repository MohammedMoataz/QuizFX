package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Result {
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> answersCol;
    @FXML
    private TableColumn<Data, String> correctCol;
    @FXML
    private Label label;

    @FXML
    private void initialize() {
        this.answersCol.setCellValueFactory(new PropertyValueFactory<>("answer"));
        this.correctCol.setCellValueFactory(new PropertyValueFactory<>("correct"));

        this.table.setFixedCellSize(25);
    }

    public void setData(int rate, ObservableList<Data> data) {
        this.table.setItems(data);

        this.label.setText("You answered " + rate + " question correctly.");
    }
}
