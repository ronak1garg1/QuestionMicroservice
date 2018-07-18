package com.coviam.QuestionMicroservice.service.impl;

import com.coviam.QuestionMicroservice.dto.CategoryDTO;
import com.coviam.QuestionMicroservice.entity.CategoryEntity;
import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import com.coviam.QuestionMicroservice.repository.CategoryRepository;
import com.coviam.QuestionMicroservice.service.CategoryServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface{

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<CategoryEntity> getAll() {

        return (List<CategoryEntity>)categoryRepository.findAll();
    }

    @Override
    public List<CategoryEntity> saveAll(List<CategoryDTO> categoryDTOS) {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for(CategoryDTO categoryDTO:categoryDTOS)
        {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(categoryDTO,categoryEntity);
            categoryEntityList.add(categoryEntity);
        }
        return (List<CategoryEntity>) categoryRepository.saveAll(categoryEntityList);
    }

}
