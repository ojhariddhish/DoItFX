/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import static javafx.beans.property.ReadOnlyIntegerProperty.readOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private ProgressBar pbTask;
    
    @FXML
    private TableView<Task> tableTasks;

    @FXML
    private TableColumn<Task, String> colPriority;

    @FXML
    private TableColumn<Task, String> colDescription;

    @FXML
    private TableColumn<Task, String> colProgress;

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

    private final  Task currentTask = new Task();
    private final  ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final  HashMap<Integer, Task> tasksMap = new HashMap<>();
    private int lastId = 0;
    
    
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
//                currentTask.setDescription(tfDescription.getText().toString());
//                //pbTask.setProgress((1.0 * newValue) / 100);
//                System.out.println("Description: "+currentTask.getDescription());
//                System.out.println("Priority: "+currentTask.getPriority());
//                System.out.println("Progress: "+currentTask.getProgress());
            }
        });
        
        ReadOnlyIntegerProperty intProgress = readOnlyIntegerProperty(spProgress.valueProperty());
        pbTask.progressProperty().bind(intProgress.divide(100.0));
        
        comboBoxPriority.valueProperty().bindBidirectional(currentTask.priorityProperty());
        tfDescription.textProperty().bindBidirectional(currentTask.descriptionProperty());
        spProgress.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());
        
        tableTasks.setItems(tasks);
        colPriority.setCellValueFactory(rowData -> rowData.getValue().priorityProperty());
        colDescription.setCellValueFactory(rowData -> rowData.getValue().descriptionProperty());
        colProgress.setCellValueFactory(rowData -> Bindings.concat(rowData.getValue().progressProperty(),"%"));
        
//        tasks.addAll(new Task(1, "High", "Learn JavaFX", 50),
//                        new Task(2, "Medium", "Make an app", 0),
//                        new Task(3, "Low", "Research on JavaFX", 30));
        
        tableTasks.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Task> observable, Task oldValue, Task newValue) -> {
            setCurrentTask(newValue);
	});
        
        StringBinding addButtonBinding = new StringBinding() {
            {
                super.bind(currentTask.idProperty());
            }
            @Override
            protected String computeValue() {
                if (currentTask.getId() == null) {
                    return "Add";
                }
                else {
                    return "Update";
                }
            }
        };
        btnAdd.textProperty().bind(addButtonBinding);
        btnAdd.disableProperty().bind(Bindings.greaterThan(3, currentTask.descriptionProperty().length()));
        
//        btnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
//            System.out.println("Filter: Mouse Clicked");
//        });
//        
//        btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Handler: Mouse Clicked");
//            }
//        });
//
//        btnAdd.setOnAction(event -> {
//            System.out.println("Action occurred");
//        });
        
    }
    
    @FXML
    void addClicked(ActionEvent event) {
        if (currentTask.getId() == null) {
            Task t = new Task(++lastId, currentTask.getPriority(), currentTask.getDescription(), currentTask.getProgress());
            tasks.add(t);
            tasksMap.put(lastId, t);
        }
        else {
            Task t = tasksMap.get(currentTask.getId());
            t.setDescription(currentTask.getDescription());
            t.setPriority(currentTask.getPriority());
            t.setProgress(currentTask.getProgress());
        }
        
        setCurrentTask(null);
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Task Update");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.getButtonTypes().remove(0, 2);
        alert.getButtonTypes().add(0, ButtonType.YES);
        alert.getButtonTypes().add(0, ButtonType.NO);
        Optional<ButtonType> confirmationResponse = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.YES) {
            setCurrentTask(null);
            tableTasks.getSelectionModel().clearSelection();
        }
        
        
    }
    
    private void setCurrentTask(Task selectedTask) {
        if (selectedTask!=null) {
            currentTask.setId(selectedTask.getId());
            currentTask.setPriority(selectedTask.getPriority());
            currentTask.setDescription(selectedTask.getDescription());
            currentTask.setProgress(selectedTask.getProgress());
        }
        else {
            currentTask.setId(null);
            currentTask.setPriority("");
            currentTask.setDescription("");
            currentTask.setProgress(0);
            tableTasks.getSelectionModel().clearSelection();
        }
    }
}
