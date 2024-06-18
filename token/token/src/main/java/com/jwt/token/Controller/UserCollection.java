package com.jwt.token.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.token.dto.LoginDto;
import com.jwt.token.dto.SignupDto;
import com.jwt.token.service.SignupService;
import com.jwt.token.service.UserService;
import com.jwt.token.utils.ApiresponseUtils;
import com.jwt.token.utils.JwtUtils;
//import com.jwt.token.utils.JwtUtils;

@RestController
@RequestMapping("/api/v1")
public class UserCollection {

	@Autowired
	UserService service;
	
	@Autowired
	SignupService signupService;
	
//	private final SignupService signupService;
//
//    @Autowired
//    public UserCollection(SignupService signupService) {
//        this.signupService = signupService;
//    }
	
	@PostMapping("/signup")
	public ResponseEntity<ApiresponseUtils> signUp(@RequestBody SignupDto signUpDto) {
		ApiresponseUtils apiResponse = signupService.SignIn(signUpDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiresponseUtils> logIn(@RequestBody LoginDto logInDto) {
		ApiresponseUtils apiResponse = service.LogIn(logInDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("/verify")
	public String tokenVerify(@RequestHeader String token) {
		JwtUtils jwtUtils = new JwtUtils();
		return jwtUtils.verifyToken(token);
	}
}
