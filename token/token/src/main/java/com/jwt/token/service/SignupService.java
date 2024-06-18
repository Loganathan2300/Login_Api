package com.jwt.token.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.token.Entity.User;
import com.jwt.token.Repository.UserRepository;
import com.jwt.token.dto.SignupDto;
import com.jwt.token.utils.ApiresponseUtils;
import com.jwt.token.utils.EncryptionUtils;
import com.jwt.token.utils.Role;

@Service
public class SignupService {

	@Autowired
	UserRepository repository;
	
	@Autowired
    EncryptionUtils encryptionUtils;

    public ApiresponseUtils SignIn(SignupDto signUpDto) {
        ApiresponseUtils apiResponse = new ApiresponseUtils();
        User signUp = new User();
        signUp.setUsername(signUpDto.getUsername());
        signUp.setEmail(signUpDto.getEmail());
        signUp.setGender(signUpDto.getGender());
        try {
            signUp.setPassword(encryptionUtils.encrypt(signUpDto.getPassword()));
        } catch (Exception e) {
            apiResponse.setError("Error encrypting password: " + e.getMessage());
            return apiResponse;
        }
        signUp.setPhoneNumber(signUpDto.getPhoneNumber());
        signUp.setRole(Role.user);

        repository.save(signUp);
        apiResponse.setData(signUp);
        return apiResponse;
    }
}
