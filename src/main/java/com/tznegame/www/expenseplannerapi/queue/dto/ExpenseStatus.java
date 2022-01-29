package com.tznegame.www.expenseplannerapi.queue.dto;

import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseStatus {
    private Expense expense;
    private String status;
    private String message;

}
