/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ganesha
 */
public class DoItFX extends Application {

    UIController controller;
    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("ui.fxml"));

        AnchorPane anchor = null;
        try {
            anchor = loader.load();
            controller = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(DoItFX.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(anchor, 600, 450);

        primaryStage.setTitle("DoItFX!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(false);

        primaryStage.setOnCloseRequest(this::onClose); // Method references in Java8 using Lambda
        controller.setTasksMap(readTasksFile());

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private HashMap<Integer, Task> readTasksFile() {
        FileInputStream in = null;
        HashMap<Integer, Task> tasksMap = new HashMap<>();
        try {
            in = new FileInputStream("tasks.xml");
            XMLDecoder decoder = new XMLDecoder(in);
            tasksMap = (HashMap<Integer, Task>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            return tasksMap;
        }
    }

    private void onClose(WindowEvent event) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("tasks.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(controller.getTasksMap());
            encoder.close();
        } catch (Exception e) {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
