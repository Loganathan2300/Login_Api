package com.jwt.token.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.token.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);

}
