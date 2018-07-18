package com.coviam.QuestionMicroservice.controller;


import com.coviam.QuestionMicroservice.dto.CategoryDTO;
import com.coviam.QuestionMicroservice.dto.QuestionDTO;
import com.coviam.QuestionMicroservice.entity.CategoryEntity;
import com.coviam.QuestionMicroservice.entity.QuestionEntity;
import com.coviam.QuestionMicroservice.service.impl.CategoryServiceImpl;
import com.coviam.QuestionMicroservice.service.impl.QuestionServiceImpl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping(value = "/question/")
public class QuestionController {


    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping(method = RequestMethod.GET, value = "getOne/{questionId}")
    public ResponseEntity<?> getOne(@PathVariable("questionId") String questionId) {
        QuestionEntity questionEntity = questionService.findOne(questionId);
        QuestionDTO questionDTO = new QuestionDTO();


        if (questionEntity.equals(null)) {
            return new ResponseEntity("question not found", HttpStatus.OK);
        }
        BeanUtils.copyProperties(questionEntity, questionDTO);
        return new ResponseEntity<QuestionDTO>(questionDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAll")
    public ResponseEntity<?> getAll() {


        List<QuestionEntity> questionEntities = questionService.getAll();
        // Collections.sort(questionEntities,new SortByQuestionId());
        List<QuestionDTO> questionDTOLis = new ArrayList<>();


        if (questionEntities == null) {
            return new ResponseEntity("Data not found", HttpStatus.OK);
        }
        for (QuestionEntity questionEntity : questionEntities) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOLis.add(questionDTO);

        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOLis, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "save/{questionId}")
    public ResponseEntity<Boolean> save(@PathVariable("questionId") String questionId
            , @RequestBody QuestionEntity questionEntity) {

        QuestionEntity questionEntity1 = questionService.save(questionEntity);
        QuestionDTO questionDTO = new QuestionDTO();
        if (questionEntity1 == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        }

        BeanUtils.copyProperties(questionEntity1, questionDTO);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "saveAll")
    public ResponseEntity<Boolean> saveAll(@RequestBody List<QuestionDTO> questionDTOList) {

        List<QuestionEntity> questionEntityList = questionService.saveAll(questionDTOList);
        List<QuestionDTO> questionDTOList1 = new ArrayList<>();
        if (questionEntityList == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        }
        BeanUtils.copyProperties(questionEntityList, questionDTOList1);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getByCategoryId/{categoryId}/{pageNumber}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("categoryId") String categoryId, @PathVariable("pageNumber") int pageNumber) {

        List<QuestionEntity> questionEntityList = questionService.getByCategoryId(categoryId, pageNumber);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionEntityList == null) {
            return new ResponseEntity("", HttpStatus.OK);
        }
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);

        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);

    }

    //
    @RequestMapping(method = RequestMethod.GET, value = "getByCategoryIdAndDifficulty/{categoryId}/{difficulty}/{pageNumber}")
    public ResponseEntity<?> getByCategoryIdAndCategoryName(@PathVariable("categoryId") String categoryId
            , @PathVariable("difficulty") String difficulty
            , @PathVariable("pageNumber") int pageNumber) {

        List<QuestionEntity> questionEntityList = questionService.getByCategoryIdAndDifficulty(categoryId, difficulty, pageNumber);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionEntityList == null) {
            return new ResponseEntity("", HttpStatus.OK);
        }
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);
        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);
    }

    //
    @RequestMapping(method = RequestMethod.GET, value = "getByDifficulty/{difficulty}/{pageNumber}")
    public ResponseEntity<?> getByDifficulty(@PathVariable("difficulty") String difficulty, @PathVariable("pageNumber") int pageNumber) {

        List<QuestionEntity> questionEntityList = questionService.getByDifficulty(difficulty, pageNumber);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionEntityList == null) {
            return new ResponseEntity("", HttpStatus.OK);
        }
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);
        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "category/getAll")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryService.getAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        if (categoryEntityList == null) {
            return new ResponseEntity("", HttpStatus.OK);
        }

        for (CategoryEntity categoryEntity : categoryEntityList) {

            CategoryDTO categoryDTO = new CategoryDTO();

            BeanUtils.copyProperties(categoryEntity, categoryDTO);
            categoryDTOList.add(categoryDTO);

        }
        return new ResponseEntity<List<CategoryDTO>>(categoryDTOList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "category/saveAll")
    public ResponseEntity<Boolean> saveAllCategory(@RequestBody List<CategoryDTO> categoryDTOList) {

        List<CategoryEntity> categoryEntityList = categoryService.saveAll(categoryDTOList);
        List<CategoryDTO> categoryDTOList1 = new ArrayList<>();

        if (categoryEntityList == null) {
            return new ResponseEntity(false, HttpStatus.OK);
        }

        BeanUtils.copyProperties(categoryEntityList, categoryDTOList1);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "readCSV")
    public ResponseEntity<List<QuestionDTO>> readFromCsv() throws IOException {


        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<QuestionEntity> questionEntityList1 = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/ronakgarg/Documents/QuestionMicroservice/question_data.csv"));) {
            CsvToBean<QuestionEntity> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(QuestionEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            questionEntityList1 = csvToBean.parse();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<Object>(questionEntityList1, httpHeaders);
            ResponseEntity<List<QuestionEntity>> rs = restTemplate.exchange("http://10.177.1.100:8080/question/saveAll"
                    , HttpMethod.POST, entity
                    , new ParameterizedTypeReference<List<QuestionEntity>>() {
                    });
            for (QuestionEntity questionEntity : questionEntityList1) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(questionEntity, questionDTO);
                questionDTOList.add(questionDTO);
            }
        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllByStatus/{status}/{pageNumber}")
    public ResponseEntity<?> getAllByStatusPage(@PathVariable("status") String status, @PathVariable("pageNumber") int pageNumber) {
        List<QuestionEntity> questionEntityList = questionService.getAllStatusPage(status, pageNumber);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if (questionEntityList == null) {
            return new ResponseEntity("", HttpStatus.OK);
        }
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);
        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "getQuestionByPage/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> viewQuestion(@PathVariable("pageNumber") int pageNumber) {

        List<QuestionEntity> questionEntityList = questionService.getAllByPage(pageNumber);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);
        }

        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);
    }
    @RequestMapping(value = "getByCategoryId/{categoryId}",method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> getByCategoryId(@PathVariable("categoryId") String categoryId){
        List<QuestionEntity> questionEntityList = questionService.getByCategoryId(categoryId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionEntity, questionDTO);
            questionDTOList.add(questionDTO);
        }
        return new ResponseEntity<List<QuestionDTO>>(questionDTOList, HttpStatus.OK);
     }

}