package com.tznegame.www.expenseplannerapi.expense.entity;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class ExpenseDTO {
    private int id;
    private String title;
    private Date startDate;
    private Date endTime;
    private Category category;
    private double cost;
    private String description;
    private boolean isAMonthlyExpense;

    public ExpenseDTO(int id, String title, Date startDate, Date endTime, Category category, double cost, String description, boolean isAMonthlyExpense) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endTime = endTime;
        this.category = category;
        this.cost = cost;
        this.description = description;
        this.isAMonthlyExpense = isAMonthlyExpense;
    }

    public static ExpenseDTO entityToDto(Expense expense){
        Date afterAdding10Mins = DateUtils.addMinutes(expense.getStartDate(), expense.getInterval());
        return new ExpenseDTO(expense.getId(), expense.getTitle(), expense.getStartDate(), expense.getStartDate(), expense.getCategory(), expense.getCost(), expense.getDescription(), expense.isMonthlyExpense());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAMonthlyExpense() {
        return isAMonthlyExpense;
    }

    public void setAMonthlyExpense(boolean AMonthlyExpense) {
        isAMonthlyExpense = AMonthlyExpense;
    }


}
