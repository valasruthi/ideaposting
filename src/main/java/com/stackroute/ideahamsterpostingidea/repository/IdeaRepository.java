package com.stackroute.ideahamsterpostingidea.repository;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/*@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval,
 search, update and delete operation on objects.*/
@Repository
public interface IdeaRepository extends MongoRepository<Idea, Integer> {
  public Idea findById(int id);

  public Idea findByTitle(String title);

  public Idea findByLocation(String location);


}
