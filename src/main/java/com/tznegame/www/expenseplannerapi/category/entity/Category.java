package com.tznegame.www.expenseplannerapi.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String color;

    //@OneToMany(mappedBy = "category")
    //private List<Expense> expenses;

    public Category (){}

    public Category(Integer id) {
        this.id = id;
    }

    public Category (String name, String description, String color){
        this.name = name;
        this.description = description;
        this.color = color;
    }
}
