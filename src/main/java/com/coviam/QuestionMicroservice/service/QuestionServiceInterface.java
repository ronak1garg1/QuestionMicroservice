package com.coviam.QuestionMicroservice.service;

import com.coviam.QuestionMicroservice.dto.QuestionDTO;
import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import java.util.List;

public interface QuestionServiceInterface {

    public QuestionEntity findOne(String questionId);

    public QuestionEntity save(QuestionEntity questionEntity);

    public List<QuestionEntity> getAll();

    public List<QuestionEntity> saveAll(List<QuestionDTO> questionDTOList);


    public List<QuestionEntity> getByCategoryId(String categoryId, int pageNumber);


    public List<QuestionEntity> getByCategoryIdAndDifficulty(String categoryId, String difficulty, int pageNumber);

    public List<QuestionEntity> getByDifficulty(String difficulty, int pageNumber);

    public List<QuestionEntity> getAllByPage(int pageNumber);

    public List<QuestionEntity> getAllStatusPage(String status,int pageNumber);
    public List<QuestionEntity> getByCategoryId(String categoryId);
    public List<QuestionEntity> getByDifficulty(String difficulty);

}
