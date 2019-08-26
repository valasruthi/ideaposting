package com.stackroute.ideahamsterpostingidea.service;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.exception.IdeaAlreadyExistException;
import com.stackroute.ideahamsterpostingidea.exception.IdeaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface IdeaService {
  /**
   * AbstractMethod to save a track
   */
  public Idea save(Idea idea) throws IdeaAlreadyExistException;

  /**
   * AbstractMethod to get Idea by Id
   */
  public Idea getIdeaById(int id) throws IdeaNotFoundException;

  /*AbstractMethod to get Idea by title*/
  public Idea getIdeaByTitle(String title) throws IdeaNotFoundException;

  /*AbstractMethod to get Idea by location*/
  public Idea getIdeaByLocation(String location) throws IdeaNotFoundException;

  /*AbstractMethod to get all ideas*/
  public List<Idea> getAllIdeas() throws Exception;

  /*AbstractMethod to update the idea*/
  public Idea updateIdea(Idea idea) throws IdeaNotFoundException;

  /*AbstractMethod to delete idea by id*/
  public Idea deleteIdeaById(int id) throws IdeaNotFoundException;

  /*AbstractMethod to delete Idea by title*/
  public Idea deleteIdeaByTitle(String title) throws IdeaNotFoundException;

  /*AbstractMethod to get receent Idea by date*/
  public List<Idea> getRecentIdeas() throws Exception;
}
