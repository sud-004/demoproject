package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.domain.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

	@Query("SELECT u.name from User u WHERE u.email = :email AND u.password = :password")
	String findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query("SELECT u from User u WHERE u.name = :name")
	List<User> findByName(@Param("name") String name);

}
