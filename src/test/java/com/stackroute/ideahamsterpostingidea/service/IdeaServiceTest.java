package com.stackroute.ideahamsterpostingidea.service;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.domain.Role;
import com.stackroute.ideahamsterpostingidea.exception.IdeaAlreadyExistException;
import com.stackroute.ideahamsterpostingidea.exception.IdeaNotFoundException;
import com.stackroute.ideahamsterpostingidea.repository.IdeaRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class IdeaServiceTest {
  private Idea idea;
  private List<Idea> expectedIdeas;
  private Role role;
  /**
   * Create a mock for TrackRepository
   */
  @Mock
  IdeaRepository ideaRepository;
  /**
   * Inject the mocks as dependencies into TrackServiceImplements
   */
  @InjectMocks
  IdeaServiceImpl ideaServiceimplements;

  /**
   * Run this before each test case
   */
  @Before
  public void setUp() {
    //Initialising the mock object
    MockitoAnnotations.initMocks(this);
    idea = new Idea();
    List<String> list = new ArrayList<>();
    list.add("java");
    list.add("testing");
    idea.setId(1);
    idea.setTitle("java project");
    idea.setDescription("It is an shopping project");
    idea.setDomain("IT");
    idea.setDuration("four month");
    idea.setCost(50000);
    idea.setSubDomain("software enginner");
    role = new Role();
    role.setExperience("4");
    role.setNoOfPeople("3");
    role.setRoleName("se");
    role.setSkills(list);
    List<Role> list1 = new ArrayList<>();
    list1.add(role);
    idea.setRole(list1);
    idea.setLocation("banglore");
    idea.setStatus("open");
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    idea.setPostedOn(dateobj);
    expectedIdeas = new ArrayList<>();
  }

  @After
  public void tearDown() {
    this.idea = null;
    this.expectedIdeas = null;
    this.role = null;
  }

  @Test
  public void givenIdeaShouldSaveTheIdea() throws IdeaAlreadyExistException {
    //act
    when(ideaRepository.save((Idea) any())).thenReturn(idea);
    Idea savedIdea = ideaServiceimplements.save(idea);
    //assert
    Assert.assertEquals(idea, savedIdea);

    //verify here verifies that trackRepository save method is only called once
    verify(ideaRepository, times(1)).save(idea);
  }


  @Test
  public void givenIdShouldReturnIdea() throws IdeaNotFoundException {
    ideaRepository.save(idea);
    when(ideaRepository.existsById(1)).thenReturn(true);
    when(ideaRepository.findById(1)).thenReturn(idea);
    Idea getIdea = ideaServiceimplements.getIdeaById(1);
    Assert.assertEquals(idea, getIdea);
    verify(ideaRepository, Mockito.times(1)).findById(1);

  }


  @Test
  public void givenTitleShouldGetIdea() throws IdeaNotFoundException {
    ideaRepository.save(idea);
    when(ideaRepository.findByTitle("java project")).thenReturn(idea);
    Idea getByTitle = ideaServiceimplements.getIdeaByTitle("java project");
    Assert.assertEquals(idea, getByTitle);
    verify(ideaRepository, Mockito.times(1)).findByTitle("java project");

  }


  @Test
  public void getIdeaByLocation() throws IdeaNotFoundException {
    ideaRepository.save(idea);
    when(ideaRepository.findByLocation("banglore")).thenReturn(idea);
    Idea getByLocation = ideaServiceimplements.getIdeaByLocation("banglore");
    verify(ideaRepository, Mockito.times(1)).findByLocation("banglore");
  }


  @Test
  public void getAllIdeas() throws Exception {
    ideaRepository.save(idea);
    when(ideaRepository.findAll()).thenReturn(expectedIdeas);
    List<Idea> ideaList = ideaServiceimplements.getAllIdeas();
    Assert.assertEquals(expectedIdeas, ideaList);
    verify(ideaRepository, Mockito.times(1)).findAll();
  }


  @Test
  public void givenIdeaShouldUpdateIdea() throws IdeaNotFoundException {
    when(ideaRepository.existsById(idea.getId())).thenReturn(true);
    when(ideaRepository.findById(idea.getId())).thenReturn(idea);
    Idea idea1 = new Idea();
    List<String> list = new ArrayList<>();
    list.add("java");
    list.add("testing");
    idea1.setId(1);
    idea1.setTitle("online project");
    idea1.setDescription("It is an shopping project");
    idea1.setDomain("IT");
    idea1.setDuration("four month");
    idea1.setCost(50000);
    idea1.setSubDomain("software enginner");
    role = new Role();
    role.setExperience("4");
    role.setNoOfPeople("3");
    role.setRoleName("se");
    role.setSkills(list);
    List<Role> list1 = new ArrayList<>();
    list1.add(role);
    idea1.setRole(list1);
    idea1.setLocation("banglore");
    idea1.setStatus("open");
    Idea updatedIdea = ideaServiceimplements.updateIdea(idea1);
    Assert.assertEquals(updatedIdea, updatedIdea);
    //verify here verifies that ideaRepository existsById method is only called once
    verify(ideaRepository, times(1)).existsById(idea.getId());

    //verify here verifies that ideaRepository findById method is only called once
    verify(ideaRepository, times(1)).findById(idea1.getId());


  }


  @Test
  public void deleteIdeaById() throws IdeaNotFoundException {
    ideaRepository.save(idea);
    when(ideaRepository.existsById(1)).thenReturn(true);
    when(ideaRepository.findById(1)).thenReturn(idea);
    Idea deleteIdea = ideaServiceimplements.deleteIdeaById(1);
    Assert.assertEquals(idea, deleteIdea);
    verify(ideaRepository, Mockito.times(1)).findById(1);
  }


  @Test
  public void givenTitleShouldDeleteIdea() throws IdeaNotFoundException {
    ideaRepository.save(idea);
    when(ideaRepository.findByTitle("java project")).thenReturn(idea);
    Idea deleteIdeaByTitle = ideaServiceimplements.deleteIdeaByTitle("java project");
    Assert.assertEquals(idea, deleteIdeaByTitle);
    verify(ideaRepository, Mockito.times(1)).findByTitle("java project");
  }

  @Test
  public void givenDateShouldGetRecentIdeas() {
   ideaRepository.save(idea);
   when(ideaRepository.findById(1)).thenReturn(idea);
   List<Idea> recentIdeas=ideaServiceimplements.getRecentIdeas();
    Assert.assertEquals(expectedIdeas,recentIdeas );
    verify(ideaRepository, Mockito.times(1)).findAll(Sort.by(Sort.Direction.DESC, "postedOn"));
  }
}


