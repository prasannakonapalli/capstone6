package co.grandcircus.capstone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
 
public interface TaskRepo extends JpaRepository<Task, Long>{
	List<Task> findByUserId(Long userId);

}
