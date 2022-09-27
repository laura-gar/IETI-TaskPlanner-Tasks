package com.taskplanner.tasks.repository;

import java.util.List;
import com.taskplanner.tasks.entities.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Laura Garcia
 */
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    public List<Task> findByAssignedTo(String userId);
}
