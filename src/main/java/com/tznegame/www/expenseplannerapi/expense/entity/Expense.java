package com.tznegame.www.expenseplannerapi.expense.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tznegame.www.expenseplannerapi.category.entity.Category;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "startdate")
    private Date startDate;

    private int interval;

    private double cost;

    private String description;

    @Column(name = "monthlyexpense")
    private boolean monthlyExpense;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @JsonIgnoreProperties("expenses")
    @ManyToOne()
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    private Category category;

    public Expense (){}

    public Expense(Date date, Category category, double cost, String description) {
        this.startDate = date;
        this.category = category;
        this.cost = cost;
        this.description = description;
    }

    public Expense(Integer id, String title, Date startDate, int interval, double cost, String description, boolean monthlyExpense, Category category, Date lastUpdate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.interval = interval;
        this.cost = cost;
        this.description = description;
        this.monthlyExpense = monthlyExpense;
        this.category = category;
        this.lastUpdate = lastUpdate;
    }

    public static Expense dtoToEntity (ExpenseDTO dto){
        long diffInMillies = Math.abs(dto.getEndTime().getTime() - dto.getStartDate().getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return new Expense(dto.getId(), dto.getTitle(), dto.getStartDate(), (int) diff, dto.getCost(), dto.getDescription(), dto.isAMonthlyExpense(), dto.getCategory(), new Date());
    }
}
