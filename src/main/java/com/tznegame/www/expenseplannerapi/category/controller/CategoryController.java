package com.tznegame.www.expenseplannerapi.category.controller;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import com.tznegame.www.expenseplannerapi.category.repository.CategoryRepository;
import com.tznegame.www.expenseplannerapi.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://10.0.2.2:8080"})
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllTutorials() {
        try {
            List<Category> categories = new ArrayList<Category>();
            categories = categoryService.getAll();

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
