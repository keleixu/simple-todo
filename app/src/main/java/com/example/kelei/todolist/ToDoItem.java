package com.example.kelei.todolist;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


import java.io.Serializable;

/**
 * Created by kelei on 1/25/15.
 */
@Table(name = "ToDoItem")
public class ToDoItem extends Model implements Serializable {
    @Column(name = "Name")
    public String name;

    // Make sure to define this constructor (with no arguments)
    // If you don't querying will fail to return results!
    public ToDoItem() {
        super();
    }

    // Be sure to call super() on additional constructors as well
    public ToDoItem(String name){
        super();
        this.name = name;
    }
}
