package co.grandcircus.capstone;
 

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
