package io.swagger.api;

import io.swagger.entity.TaskEntity;
import io.swagger.entity.UserEntity;
import io.swagger.model.TaskItem;
import io.swagger.repository.TaskRespository;
import io.swagger.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-19T22:52:30.362Z[GMT]")
@RestController
public class TasksApiController implements TasksApi {

	private static final Logger log = LoggerFactory.getLogger(TasksApiController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRespository taskRepository;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public TasksApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ApiResponseMessage> addTask(
			@Parameter(in = ParameterIn.DEFAULT, description = "Inventory item to add", schema = @Schema()) @Valid @RequestBody TaskItem body) {
		String accept = request.getHeader("Accept");
		
		try {
			UserEntity userEntity = userRepository.findByEmail(body.getUserEmail()).orElseThrow();
			
			TaskEntity te = TaskEntity.builder()
					.task(body.getTask())
					.description(body.getDescription())
					.priority(body.getPriority().toBigIntegerExact().intValue())
					.status(body.getStatus().toBigInteger().intValue())
					.user(userEntity)
					.build();
			taskRepository.save(te);					
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.ACCEPTED);
		}catch(Exception ex) {
			
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ApiResponseMessage> deleteTask(
			@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "email", required = true) String email,
			@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "taskId", required = true) String taskId) {
		
		//String accept = request.getHeader("Accept");
		try {
			log.info("deleting the task with the Id: " + taskId);
			log.info("user email: " + email);
			taskRepository.deleteById(Long.parseLong(taskId));	
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.OK);
		}catch(Exception ex) {
			log.info("failed to delete the task");
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<ApiResponseMessage> editTask(
			@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "email", required = true) String email,
			@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody TaskItem body) {
		String accept = request.getHeader("Accept");
		log.info("editing task");
		log.info("user: " + email);
		log.info("task: " + body.toString());
		try {
			UserEntity userEntity = userRepository.findByEmail(body.getUserEmail()).orElseThrow();
			
			TaskEntity te = TaskEntity.builder()
					.taskId(body.getTaskId().toBigIntegerExact().longValue())
					.task(body.getTask())
					.description(body.getDescription())
					.priority(body.getPriority().toBigIntegerExact().intValue())
					.status(body.getStatus().toBigIntegerExact().intValue())
					.user(userEntity)
					.build();
			taskRepository.save(te);					
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.ACCEPTED);
		}catch(Exception ex) {
			
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<TaskItem>> getTasks(
			@NotNull @Parameter(in = ParameterIn.QUERY, description = "pass the user Email to get his tasks", required = true, schema = @Schema()) @Valid @RequestParam(value = "user", required = true) String user) {

		String accept = request.getHeader("Accept");

		try {
			if (accept != null && accept.contains("application/json")) {

				UserEntity userE = userRepository.findByEmail(user).orElseThrow();
				List<TaskEntity> tasks = taskRepository.findByUser(userE);
				List<TaskItem> taskItems = new LinkedList<TaskItem>();
				tasks.forEach(te -> {
					TaskItem ti = new TaskItem();
					ti.setTaskId(BigDecimal.valueOf(te.getTaskId()));
					ti.setTask(te.getTask());
					ti.setDescription(te.getDescription());
					ti.setPriority(BigDecimal.valueOf(te.getPriority()));
					ti.setStatus(BigDecimal.valueOf(te.getStatus()));
					ti.setUserEmail(te.getUser().getEmail());
					taskItems.add(ti);
				});

//				log.info("tasks size: " + tasks.size());
//				log.info(tasks.toString());
				return new ResponseEntity<List<TaskItem>>(taskItems, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<TaskItem>>(new LinkedList<TaskItem>(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<TaskItem>>(new LinkedList<TaskItem>(),HttpStatus.BAD_REQUEST);
	}

}
