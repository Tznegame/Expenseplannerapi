package com.tznegame.www.expenseplannerapi.expense.repository;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
    @Query("select e from Expense e where e.lastUpdate > :lastUpdate")
    List<Expense> findAllWithLastUpdateAfter(
            @Param("lastUpdate") Date lastUpdate);

}
