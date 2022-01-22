package io.swagger.api;

import io.swagger.entity.UserEntity;
import io.swagger.model.LoginData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-19T22:52:30.362Z[GMT]")
@RestController
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRespository taskRepository;
    
    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ApiResponseMessage> login(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginData body) {
        String accept = request.getHeader("Accept");
        log.info("loging in ......");
        log.info("user: " + body.getEmail());
        //List<ApiResponseMessage> entities = new ArrayList<ApiResponseMessage>();
        try {
        	UserEntity user = userRepository.findByEmail(body.getEmail()).orElseThrow();
        	log.info("user found");
        	//TODO: convert the password to hash
        	if(user.getPassword().equals(body.getPassword())) {
        		log.info("success");
        		//entities.add(new ApiResponseMessage());
    			ApiResponseMessage res = new ApiResponseMessage();
    			res.setMessage(user.getUsername());
        		return new ResponseEntity<ApiResponseMessage>(res, HttpStatus.OK);        	
        	}
        } catch (Exception ex) {
        	log.error("failed to login");
        	log.error(ex.getMessage());
        }
        log.error("user not found.");
        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(), HttpStatus.BAD_REQUEST);
    }

}
