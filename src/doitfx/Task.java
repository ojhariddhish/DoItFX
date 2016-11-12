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
    
    private final StringProperty priority = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<Integer> progress = new SimpleObjectProperty<>(0);
    
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);

    public Task() {
        
    }
    
    public Task(Integer id, String priority, String description, Integer progress) {
        this.id.set(id);
        this.priority.set(priority);
        this.description.set(description);
        this.progress.set(progress);
    }
    
    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public Integer getId() {
        return this.id.get();
    }
    
    public ObjectProperty<Integer> idProperty() {
        return id;
    }
    
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
