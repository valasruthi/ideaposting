package com.stackroute.ideahamsterpostingidea.controller;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.exception.IdeaAlreadyExistException;
import com.stackroute.ideahamsterpostingidea.exception.IdeaNotFoundException;
import com.stackroute.ideahamsterpostingidea.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*@RestController annotation in a Spring application to build a Restful controller. */
@RestController
/*RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods. */
@RequestMapping("api/v1/")
public class IdeaController {

  private IdeaService ideaService;

  public IdeaController() {

  }

  /*@Autowired annotation is used for automatic dependency injection.*/
  @Autowired
  public IdeaController(IdeaService ideaService) {
    this.ideaService = ideaService;
  }

  /*handle the HTTP POST requests matched with given URI expression.*/
  @PostMapping("idea")
  public ResponseEntity<?> savedIdea(@RequestBody Idea idea) throws IdeaAlreadyExistException {
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    idea.setPostedOn(dateobj);
    ResponseEntity responseEntity;
    Idea savedIdea = ideaService.save(idea);
    return new ResponseEntity<>(savedIdea, HttpStatus.CREATED);
  }

  /* @GetMapping annotated methods handle the HTTP GET requests matched with given URI expression.get the idea by id*/
  @GetMapping("idea/{id}")
  public ResponseEntity<?> getIdeaById(@PathVariable int id) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea getIdea = ideaService.getIdeaById(id);
    return new ResponseEntity<>(getIdea, HttpStatus.OK);
  }

  /*Get the idea by title*/
  @GetMapping("ideas/{title}")
  public ResponseEntity<?> getIdeaByTitle(@PathVariable String title) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea getIdeaByTitle = ideaService.getIdeaByTitle(title);
    return new ResponseEntity<>(getIdeaByTitle, HttpStatus.OK);
  }

  /*Get the idea by location*/
  @GetMapping("ideas/get/{location}")
  public ResponseEntity<?> getIdeaByLocation(@PathVariable String location) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea getIdeaBylocation = ideaService.getIdeaByLocation(location);
    return new ResponseEntity<>(getIdeaBylocation, HttpStatus.OK);
  }

  /*get All ideas*/
  @GetMapping("ideas")
  public ResponseEntity<?> getAllIdeas() throws Exception {
    ResponseEntity responseEntity;
    List<Idea> getAllIdeas = ideaService.getAllIdeas();
    return new ResponseEntity<>(getAllIdeas, HttpStatus.OK);
  }

  /*Delete the idea by id*/
  @DeleteMapping("idea/{id}")
  public ResponseEntity<?> deleteById(@PathVariable int id) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea deleteIdea = ideaService.deleteIdeaById(id);
    return new ResponseEntity<>(deleteIdea, HttpStatus.CONFLICT);
  }

  /*Delete the idea by title*/
  @DeleteMapping("ideas/{title}")
  public ResponseEntity<?> deletIdeaByTitle(@PathVariable String title) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea deleteIdeaByTitle = ideaService.deleteIdeaByTitle(title);
    return new ResponseEntity<>(deleteIdeaByTitle, HttpStatus.CONFLICT);
  }

  /*update the idea by id*/
  @PutMapping("idea/{id}")
  public ResponseEntity<?> updateIdea(@RequestBody Idea idea) throws IdeaNotFoundException {
    ResponseEntity responseEntity;
    Idea updateIdea = ideaService.updateIdea(idea);
    return new ResponseEntity<>(updateIdea, HttpStatus.UPGRADE_REQUIRED);
  }

  /*get the recentIdeas by the time he have updated*/
  @GetMapping("recentIdeas")
  public ResponseEntity<?> getRecentIdeas() throws Exception {
    ResponseEntity responseEntity;
    List<Idea> recentIdeas = ideaService.getRecentIdeas();
    return new ResponseEntity<>(recentIdeas, HttpStatus.OK);
  }
}


