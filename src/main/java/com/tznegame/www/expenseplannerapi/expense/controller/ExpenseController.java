package com.tznegame.www.expenseplannerapi.expense.controller;

import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import com.tznegame.www.expenseplannerapi.expense.entity.ExpenseDTO;
import com.tznegame.www.expenseplannerapi.expense.service.ExpenseService;
import com.tznegame.www.expenseplannerapi.queue.config.MessagingConfig;
import com.tznegame.www.expenseplannerapi.queue.dto.ExpenseStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @Autowired
    private RabbitTemplate template;
//    @Autowired
//    ExpenseRepository repositoryService;
    @GetMapping("/update")
    public ResponseEntity<List<Expense>> getNewExpense(@RequestParam("date") String date) {
        try {
            List<Expense> expenses;
            expenses = expenseService.getNewExpense(date);

            if (expenses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addExpense(@RequestBody ExpenseDTO dto) {
        try {
            Expense expense = Expense.dtoToEntity(dto);
            expense = expenseService.save(expense);


            //Create resource location
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(expense.getId())
                    .toUri();

            //Send location in response
            return ResponseEntity.created(location).body(expense.getId());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Object> putExpense(@RequestBody ExpenseDTO dto) {
        try {
            Expense expense = expenseService.replaceExpense(dto, dto.getId());


            //Create resource location
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(expense.getId())
                    .toUri();

            //Send location in response
            return ResponseEntity.created(location).body(expense.getId());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable int id) {
        try {
            expenseService.delete(id);

            //Send location in response
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addToQueue")
    public ResponseEntity<Object> addToQueue(@RequestBody Expense expense) {
        try {
            expense.setId((int) Math.random());

            ExpenseStatus expenseStatus = new ExpenseStatus(expense, "PROCESS", "Expense placed succesfully!");
            //DIRECT EXCHANGE
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.BINDING_KEY, expenseStatus);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(expense.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
