package com.coviam.QuestionMicroservice.repository;

import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface QuestionPageRepository extends PagingAndSortingRepository<QuestionEntity, Long> {


    public Page<QuestionEntity> getAllByCategoryId(String categoryId, Pageable pageable);

    public Page<QuestionEntity> getAllByCategoryIdAndDifficulty(String categoryId, String difficulty, Pageable pageable);

    public Page<QuestionEntity> getAllByDifficulty(String difficulty, Pageable pageable);

    public Page<QuestionEntity> getAllByStatus(String status, Pageable pageable);

}
