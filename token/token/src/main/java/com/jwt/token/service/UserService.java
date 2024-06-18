package com.jwt.token.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.token.Entity.User;
import com.jwt.token.Repository.UserRepository;
import com.jwt.token.dto.LoginDto;
import com.jwt.token.utils.ApiresponseUtils;
import com.jwt.token.utils.EncryptionUtils;
import com.jwt.token.utils.JwtUtils;
import com.jwt.token.utils.Role;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
    EncryptionUtils encryptionUtils;

    public ApiresponseUtils LogIn(LoginDto logInDto) {
        ApiresponseUtils apiResponse = new ApiresponseUtils();

        User login = repository.findByUsername(logInDto.getUsername());

        if (login == null || login.getId() == null) {
            apiResponse.setData("Can't Login");
            return apiResponse;
        }

        try {
            String decryptedPassword = encryptionUtils.decrypt(login.getPassword());
            if (!decryptedPassword.equals(logInDto.getPassword())) {
                apiResponse.setData("Invalid credentials");
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse.setData("Error decrypting password: " + e.getMessage());
            return apiResponse;
        }

        if (login.getRole().equals(Role.admin)) {
            List<User> entireData = repository.findAll();
            apiResponse.setData1(entireData);
        }

        String token = jwtUtils.generateJwt(login);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        return apiResponse;
    }
}
