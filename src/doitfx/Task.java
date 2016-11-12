/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doitfx;

import javafx.beans.property.*;

/**
 *
 * @author Ganesha
 */
public class Task {
    
    StringProperty priority = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    ObjectProperty<Integer> progress = new SimpleObjectProperty<>(0);

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }
    
    public StringProperty priorityProperty() {
        return priority;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }

    public Integer getProgress() {
        return progress.get();
    }

    public void setProgress(Integer progress) {
        this.progress.set(progress);
    }
    
    public ObjectProperty<Integer> progressProperty() {
        return progress;
    }
    
}
