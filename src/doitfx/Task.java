/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Ganesha
 */
public class Task {
    
    public static void main(String[] args) {
        SimpleIntegerProperty intProp = new SimpleIntegerProperty();
        intProp.set(10);
        System.out.println("IntProp: "+intProp.get());
        
        
        SimpleStringProperty stringProp = new SimpleStringProperty("InitialValue");
        System.out.println("StringProp: "+stringProp.get());
        stringProp.set("New Value");
        System.out.println("NewString: "+stringProp.get());
        
        ReadOnlyBooleanWrapper readOnlyBool = new ReadOnlyBooleanWrapper(true);
        ReadOnlyBooleanProperty boolProp = readOnlyBool.getReadOnlyProperty();
        System.out.println("ReadOnlyBool: "+boolProp);
        
//        intProp.addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("IntProp change from "+oldValue+" to "+newValue);
//            }
//        });
        
        // Lambda expression
        intProp.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println("IntProp change from "+oldValue+" to "+newValue);
        });
        
        intProp.set(90);
        intProp.set(100);
    }
    
}
