package com.coviam.QuestionMicroservice.service;

import com.coviam.QuestionMicroservice.dto.CategoryDTO;
import com.coviam.QuestionMicroservice.entity.CategoryEntity;

import java.util.List;
import java.util.Locale;

public interface CategoryServiceInterface {

    public List<CategoryEntity> getAll();
    public List<CategoryEntity> saveAll(List<CategoryDTO> categoryDTOS);
}
