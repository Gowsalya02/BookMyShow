package com.bookmyshow.boot.bookmyshow.project.contoller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookmyshow.boot.bookmyshow.project.entity.User;
import com.bookmyshow.boot.bookmyshow.project.service.UserService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@RestController
@RequestMapping("user")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) 
	{
		return userService.saveUser(user);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int userId) 
	{
		return userService.findUser(userId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int userId) 
	{
		return userService.deleteUser(userId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@RequestParam int userId) 
	{
		return userService.updateUser(user,userId);	
	}
	
	@GetMapping("all")
	public List<User> findAlluser()
	{
		return userService.findAllUsers();
	}

}
