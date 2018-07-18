package com.coviam.QuestionMicroservice.service.impl;

import com.coviam.QuestionMicroservice.dto.QuestionDTO;
import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import com.coviam.QuestionMicroservice.repository.QuestionPageRepository;
import com.coviam.QuestionMicroservice.repository.QuestionRespository;
import com.coviam.QuestionMicroservice.service.QuestionServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionServiceInterface {


    private final static int PAGESIZE = 10;

    @Autowired
    QuestionRespository questionRespository;

    @Autowired
    QuestionPageRepository questionPageRepository;


    @Override
    public QuestionEntity findOne(String questionId) {
        return questionRespository.findById(questionId).get();
    }

    @Override
    public QuestionEntity save(QuestionEntity questionEntity) {

        return questionRespository.save(questionEntity);

    }

    @Override
    public List<QuestionEntity> getAll() {

        return (List<QuestionEntity>) questionRespository.findAll();
    }

    @Override
    public List<QuestionEntity> saveAll(List<QuestionDTO> questionDTOList) {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (QuestionDTO questionDTO : questionDTOList) {
            QuestionEntity questionEntity = new QuestionEntity();
            BeanUtils.copyProperties(questionDTO, questionEntity);
            questionEntities.add(questionEntity);
        }
        return (List<QuestionEntity>) questionRespository.saveAll(questionEntities);
    }

    @Override
    public List<QuestionEntity> getByCategoryId(String categoryId, int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "categoryId");

        return questionPageRepository.getAllByCategoryId(categoryId, request).getContent();
    }

    //
    @Override
    public List<QuestionEntity> getByCategoryIdAndDifficulty(String categoryId, String difficulty, int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "categoryId");
        return questionPageRepository.getAllByCategoryIdAndDifficulty(categoryId, difficulty, request).getContent();

    }

    //
    @Override
    public List<QuestionEntity> getByDifficulty(String difficulty, int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "categoryId");

        List<QuestionEntity> questionEntities = questionPageRepository.getAllByDifficulty(difficulty, request).getContent();
        return questionEntities;
    }


    public List<QuestionEntity> getAllByPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "categoryId");
        return questionPageRepository.findAll(request).getContent();
    }

    @Override
    public List<QuestionEntity> getAllStatusPage(String status,int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "categoryId");
        List<QuestionEntity> questionEntities = questionPageRepository.getAllByStatus(status, request).getContent();
        return questionEntities;
    }

    @Override
    public List<QuestionEntity> getByCategoryId(String categoryId) {
        List<QuestionEntity>  questionEntityList = questionRespository.getAllByCategoryId(categoryId);
        return questionEntityList;
    }


}
