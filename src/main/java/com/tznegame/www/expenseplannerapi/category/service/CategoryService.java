package com.tznegame.www.expenseplannerapi.category.service;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import com.tznegame.www.expenseplannerapi.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        List<Category> categories = new ArrayList<Category>();
        Iterable<Category> c= categoryRepository.findAll();
        c.forEach(categories::add);
        return categories;
    }
}
