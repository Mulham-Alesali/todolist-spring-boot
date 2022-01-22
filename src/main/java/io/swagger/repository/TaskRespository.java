package io.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swagger.entity.TaskEntity;
import io.swagger.entity.UserEntity;


public interface TaskRespository extends JpaRepository<TaskEntity, Long>{
	List<TaskEntity> findByUser(UserEntity user);
}
