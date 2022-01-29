package com.tznegame.www.expenseplannerapi;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import com.tznegame.www.expenseplannerapi.expense.controller.ExpenseController;
import com.tznegame.www.expenseplannerapi.expense.entity.Expense;
import com.tznegame.www.expenseplannerapi.expense.entity.ExpenseDTO;
import com.tznegame.www.expenseplannerapi.expense.repository.ExpenseRepository;
import com.tznegame.www.expenseplannerapi.expense.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestExpenseController {
    @InjectMocks
    ExpenseController expenseController;

    @Mock
    ExpenseService expenseService;

    @Mock
    ExpenseRepository expenseRepository;

    /*@Test
    public void testAddEmployee()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Expense expense = new Expense();
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
        Date date =  new GregorianCalendar(2015, 12 - 1, 1).getTime();
        ExpenseDTO expenseToAdd = new Expense(date, new Category(2), 1, "test");
        ResponseEntity<Object> responseEntity = expenseController.addExpense(expenseToAdd);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }*/

//    @Test
//    public void testFindAll()
//    {
//        // given
//        Employee employee1 = new Employee("Lokesh", "Gupta", "howtodoinjava@gmail.com");
//        Employee employee2 = new Employee("Alex", "Gussin", "example@gmail.com");
//        List<Employee> list = new ArrayList<Employee>();
//        list.addAll(Arrays.asList(employee1, employee2));
//
//        when(employeeRepository.findAll()).thenReturn(list);
//
//        // when
//        Employees result = employeeController.getEmployees();
//
//        // then
//        assertThat(result.getEmployeeList().size()).isEqualTo(2);
//
//        assertThat(result.getEmployeeList().get(0).getFirstName())
//                .isEqualTo(employee1.getFirstName());
//
//        assertThat(result.getEmployeeList().get(1).getFirstName())
//                .isEqualTo(employee2.getFirstName());
//    }
}
