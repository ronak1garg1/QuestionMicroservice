package com.coviam.QuestionMicroservice.repository;

import com.coviam.QuestionMicroservice.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends CrudRepository<CategoryEntity,String> {
}
