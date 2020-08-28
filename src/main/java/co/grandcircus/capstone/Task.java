package co.grandcircus.capstone;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private LocalDate dueDate;
	private boolean complete;
	@ManyToOne
private User user;
	public Task() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
