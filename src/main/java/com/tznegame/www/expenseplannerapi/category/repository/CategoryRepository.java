package com.tznegame.www.expenseplannerapi.category.repository;

import com.tznegame.www.expenseplannerapi.category.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
