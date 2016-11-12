/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;

/**
 *
 * @author Ganesha
 */
public class Task {
    
    public static void main(String[] args) {
        
        StringProperty lastNameProp = new SimpleStringProperty();
        StringProperty surNameProp = new SimpleStringProperty();
        
        //surNameProp.bind(lastNameProp);
        surNameProp.bindBidirectional(lastNameProp);
        
        surNameProp.set("Tony");
        System.out.println("Surname: "+surNameProp.get()+" Lastname: "+lastNameProp.get());
        
        lastNameProp.set("Stark");
        System.out.println("Surname: "+surNameProp.get()+" Lastname: "+lastNameProp.get());
        
        StringProperty firstNameProp = new SimpleStringProperty();
        firstNameProp.set("Tony");
        StringProperty fullNameProp = new SimpleStringProperty();
        fullNameProp.bind(Bindings.concat(firstNameProp+" "+lastNameProp));
        System.out.println("Full Name: "+fullNameProp.get());
        
        
        IntegerProperty length = new SimpleIntegerProperty(18);
        IntegerProperty width = new SimpleIntegerProperty(13);
        IntegerProperty area = new SimpleIntegerProperty();
        
        area.bind(length.multiply(width));
        
        NumberBinding perimeterBinding = length.add(width).multiply(2);
        System.out.println("Area: "+area.get());
        System.out.println("Perimeter: "+perimeterBinding.getValue().intValue());
        
        DoubleProperty radius = new SimpleDoubleProperty(1.5);
        
        DoubleBinding volumeBinding = new DoubleBinding() {
            {
                super.bind(radius);
            }
            @Override
            protected double computeValue() {
                return 4 / 3 * Math.PI * Math.pow(radius.get(), 3);
            }
        };
        System.out.println("Volume: "+volumeBinding.get());
        radius.set(2.5);
        System.out.println("Volume: "+volumeBinding.get());
    }
    
}
