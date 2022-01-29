package com.tznegame.www.expenseplannerapi.expense.service;

import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import com.tznegame.www.expenseplannerapi.expense.entity.ExpenseDTO;
import com.tznegame.www.expenseplannerapi.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public List<Expense> getNewExpense(String dateString){
        Date date;
        List<Expense> expenses = new ArrayList<>();
        try {
            date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS").parse(dateString);
            expenses = new ArrayList<Expense>();
            expenseRepository.findAllWithLastUpdateAfter(date).forEach(expenses::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public Expense save(Expense expense){
        return expenseRepository.save(expense);
    }

    public void delete(int id){
        expenseRepository.deleteById(id);
    }

    public Expense replaceExpense(ExpenseDTO dto, int id) {
        Expense expense = Expense.dtoToEntity(dto);
        return expenseRepository.findById(id)
                .map(dbExpense -> {
                    dbExpense.setTitle(expense.getTitle());
                    dbExpense.setStartDate(expense.getStartDate());
                    dbExpense.setInterval(expense.getInterval());
                    dbExpense.setCost(expense.getCost());
                    dbExpense.setDescription(expense.getDescription());
                    dbExpense.setMonthlyExpense(expense.isMonthlyExpense());
                    dbExpense.setCategory(expense.getCategory());
                    dbExpense.setLastUpdate(expense.getLastUpdate());
                    return expenseRepository.save(dbExpense);
                })
                .orElseGet(() -> {
                    expense.setId(id);
                    return expenseRepository.save(expense);
                });
    }
}
