/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Ganesha
 */
public class DoItFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        grid.setMinWidth(600);
        grid.setMinHeight(400);
        grid.setHgap(20);
        grid.setVgap(5);
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        Button btn2 = new Button();
//        btn2.setText("Button 2");
//        
//        Button btn3 = new Button();
//        btn3.setText("Button 3");
//        
//        Button btn4 = new Button();
//        btn4.setText("Button 4");
//        
//        GridPane.setConstraints(btn, 1, 1);
//        GridPane.setConstraints(btn2, 2, 2);
//        GridPane.setConstraints(btn3, 3, 1);
//        GridPane.setConstraints(btn4, 4, 2);
        
        
//        BorderPane border = new BorderPane();
//        Button top = new Button("Top");
//        top.setAlignment(Pos.TOP_CENTER);
//        border.setTop(top);
//        grid.setAlignment(Pos.TOP_CENTER);

        TableView tableArea = new TableView();
        tableArea.setMinWidth(550);
        tableArea.setMinHeight(300);
        
        TableColumn col1 = new TableColumn("Priority");
        TableColumn col2 = new TableColumn("Description");
        TableColumn col3 = new TableColumn("Progress");
        
        tableArea.getColumns().addAll(col1, col2, col3);
        
        GridPane.setConstraints(tableArea, 1, 1, 3, 1);
        
        TextField tf = new TextField();
        tf.setPromptText("Enter task name");
        tf.setMinWidth(300);
        GridPane.setConstraints(tf, 2, 2);
        
        ComboBox priority = new ComboBox();
        priority.getItems().addAll("High", "Medium", "Low");
        priority.setPromptText("Select priority");
        GridPane.setConstraints(priority, 1, 2);
        
        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(100);
        GridPane.setConstraints(btnAdd, 3, 2);
        
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(100);
        GridPane.setConstraints(btnCancel, 3, 3);
        
        HBox progressArea = new HBox();
        progressArea.getChildren().addAll(new Label("Progress"),
                                            new Spinner<Integer>(0, 100, 0),
                                            new CheckBox("Completed"));
        progressArea.setAlignment(Pos.CENTER_RIGHT);
        progressArea.setSpacing(10);
        GridPane.setConstraints(progressArea, 1, 3, 2, 1);
        
        grid.getChildren().addAll(tableArea, tf, priority, btnAdd, btnCancel, progressArea);
        
        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
        

        
        Scene scene = new Scene(grid, 600, 450);
        
        
        primaryStage.setTitle("DoItFX!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
