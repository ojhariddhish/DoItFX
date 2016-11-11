/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Ganesha
 */
public class UIController implements Initializable {
    
    @FXML
    private TableView<?> tableTasks;

//    @FXML
//    private TableColumn colPriority;
//
//    @FXML
//    private TableColumn colDescription;
//
//    @FXML
//    private TableColumn colProgress;

    @FXML
    private ComboBox<String> comboBoxPriority;

    @FXML
    private TextField tfDescription;

    @FXML
    private Button btnAdd;

    @FXML
    private Label textProgress;

    @FXML
    private Spinner<Integer> spProgress;

    @FXML
    private CheckBox cbCompleted;

    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tfDescription.setText("Dummy value");
        
        comboBoxPriority.getItems().addAll("High", "Medium", "Low");
        spProgress.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        
        spProgress.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue.intValue() == 100) {
                    cbCompleted.setSelected(true);
                }
                else {
                    cbCompleted.setSelected(false);
                }
            }
        });
    }    
    
}
