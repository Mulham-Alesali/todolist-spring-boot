package io.swagger.api;

import io.swagger.entity.UserEntity;
import io.swagger.model.User;
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
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-19T22:52:30.362Z[GMT]")
@RestController
public class RegisterApiController implements RegisterApi {

	private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRespository taskRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public RegisterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ApiResponseMessage> registerUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
		String accept = request.getHeader("Accept");
		
		try {
			UserEntity userEntity = UserEntity.builder()
					.email(body.getEmail())
					.username(body.getUsername())
					.password(body.getPassword()) //TODO: password should be hashed
					.build();
			userRepository.save(userEntity);
			log.info("a new user added: ");
			log.info(userEntity.toString());

			
			return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.CREATED);		
		}catch (Exception ex) {
			log.error("error occures while adding a new User");
			log.error(ex.getMessage());
		}
        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(),HttpStatus.NOT_IMPLEMENTED);
    }

}
