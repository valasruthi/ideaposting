package com.stackroute.ideahamsterpostingidea.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.domain.Role;
import com.stackroute.ideahamsterpostingidea.exception.GlobalExceptions;
import com.stackroute.ideahamsterpostingidea.service.IdeaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
 */
@RunWith(SpringRunner.class)
@WebMvcTest

public class IdeaControllerTest {
  @Autowired
  private MockMvc mockMvc;
  private Idea idea;
  private Role role;
  /**
   * Create a mock for TrackService
   */
  @MockBean
  private IdeaService ideaService;
  /**
   * Inject the mocks as dependencies into TrackController
   */
  @InjectMocks
  private IdeaController ideaController;
  /**
   * Run this before each test case
   */
  private List<Idea> list = null;

  @Before
  public void setUp() {
    //Initialising the mock object
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(ideaController).setControllerAdvice(new GlobalExceptions()).build();
    //act
    List<String> list1 = new ArrayList<>();
    list1.add("java");
    list1.add("testing");
    idea = new Idea();
    idea.setId(1);
    idea.setTitle("online");
    idea.setDescription("It is an online shopping project");
    idea.setDomain("IT");
    idea.setDuration("six month");
    idea.setCost(5000);
    idea.setSubDomain("enginner");
    role = new Role();
    role.setExperience("6");
    role.setNoOfPeople("5");
    role.setRoleName("engineer");
    role.setSkills(list1);
    List<Role> list2 = new ArrayList<>();
    list2.add(role);
    idea.setRole(list2);
    idea.setLocation("banglore");
    idea.setStatus("open");
    DateFormat dft = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    idea.setPostedOn(dateobj);
    list = new ArrayList<>();

  }

  @After
  public void tearDown() {
    this.idea = null;
    this.role = null;
    this.list = null;
  }

  /*@Test
  public void givenPostMappingUrlShouldReturnTheSavedIdea() throws Exception {
    when(ideaService.save(any())).thenReturn(idea);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/idea")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(idea)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
    //verify here verifies that ideaService saveIdea method is only called once
    verify(ideaService, times(1)).save(idea);
  }*/

  private static String asJsonString(final Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);

    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }


}


