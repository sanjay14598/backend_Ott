package com.gamotrance.OTT.Doa;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Todo;
@Repository
public interface TodoRepository extends MongoRepository<Todo, Long> {
    Todo findByTitle(String title);
}