/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ganesha
 */
public class DoItFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        AnchorPane anchor = null;
        try {
            anchor = FXMLLoader.load(getClass().getResource("ui.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DoItFX.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        Scene scene = new Scene(anchor, 600, 450);
        
        
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
