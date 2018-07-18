package com.coviam.QuestionMicroservice.repository;

import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface QuestionRespository extends CrudRepository<QuestionEntity, String> {

    public List<QuestionEntity> getAllByCategoryId(String categoryId);

}
