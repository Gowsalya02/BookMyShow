package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bookmyshow.boot.bookmyshow.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	@Query("select u  from User u where u.userMail=?1")
	public User findByMail(String userMail);

}
