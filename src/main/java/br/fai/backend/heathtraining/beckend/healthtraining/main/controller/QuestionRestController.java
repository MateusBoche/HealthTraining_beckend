package br.fai.backend.heathtraining.beckend.healthtraining.main.controller;


import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.question.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionRestController {
  private final QuestionService questionService;

  public QuestionRestController(QuestionService questionService) {
    this.questionService = questionService;
  }


  @GetMapping()
  public ResponseEntity<List<QuestionModel>> getEntities(){
    List<QuestionModel> questions = questionService.findAll();
    return ResponseEntity.ok().body(questions);

  }

  @GetMapping("/{id}")
  public ResponseEntity<QuestionModel> getEntityById(@PathVariable final int id){
    QuestionModel question = questionService.findById(id);
    return ResponseEntity.ok().body(question);
  }

  @GetMapping("/question/{categoty}")
  public ResponseEntity<QuestionModel> getEntityByCategory(@PathVariable final String category){
    QuestionModel question = questionService.findByCategory(category);
    return ResponseEntity.ok().body(question);
  }




}
