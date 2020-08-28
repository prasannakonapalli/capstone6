package co.grandcircus.capstone;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


 
@Controller
public class UserController {
	@Autowired
	UserRepo userDao;
	@Autowired
	TaskRepo taskDao;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String showLoginOrWelcomeForm() {
		if (session.getAttribute("user") != null) {
			return "welcome";
		} else {
			return "login";
		}

	}

	@RequestMapping("/login")
	public String showLogin() {
		if (session.getAttribute("user") != null) {
			return "welcome";
		} else {
			return "login";
		}

	}

	@PostMapping("/login-submit")
// get the email and password from the form when it's submitted.
	public String submitLoginForm(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, RedirectAttributes redir) {
		// Find the matching user.
		User user = userDao.findByEmailAndPassword(email, password);

		// System.out.println(user);
		if (user == null) {
			// If the user or password don't match, display an error message.
			model.addAttribute("message", "Incorrect email or password.");
			return "login";
		}

		// On successful login, add the user to the session.
		session.setAttribute("user", user);

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Logged in.");
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(RedirectAttributes redir) {
		// invalidate clears the current user session and starts a new one.
		session.invalidate();

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Logged out.");
		return "redirect:/";
	}

	@RequestMapping("/signup")
	public String showSignupForm() {
		return "signup";
	}

	@PostMapping("/signup")
	public String submitSignupForm(User user, @RequestParam("confirm-password") String confirmPassword, Model model,
			RedirectAttributes redir) {
		// Find the matching user.
		User existingUser = userDao.findByEmail(user.getEmail());
		if (existingUser != null) {
			// If user already exists, display an error message.
			model.addAttribute("message", "A user with that email already exists.");
			return "signup";
		}

		if (!confirmPassword.equals(user.getPassword())) {
			// If the user or passwords don't match, display an error message.
			model.addAttribute("message", "Passwords do not match.");
			return "signup";
		}

		userDao.save(user);

		// On successful sign-up, add the user to the session.
		session.setAttribute("user", user);

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Thanks for signing up!");
		return "redirect:/";
	}
	
	
	@RequestMapping("/task-list")
	public String showTaskList( Model model,RedirectAttributes redir) {
		
		User user = (User) session.getAttribute("user");
		// For this URL, make sure there is a user on the session.
				if (user == null) {
					// if not, send them back to the login page with a message.
					redir.addFlashAttribute("message", "Wait! Please log in.");
					return "redirect:/login";
				}
				//System.out.println("UserID " + user.getId());
				// if the user is logged in, proceed as normal.
				List<Task> tasks = taskDao.findByUserId(user.getId());
				//Set<Task> tasks = user.getTasks();
				//System.out.println("Tasks " + tasks.size());
			
				model.addAttribute("tasks", tasks);
				return "task-list";
		 
	}
	
	@RequestMapping("/addtask")
	public String showAddTask(Model model,RedirectAttributes redir) {
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// if not, send them back to the login page with a message.
			redir.addFlashAttribute("message", "Wait! Please log in.");
			return "redirect:/login";
		}
		model.addAttribute("userId",user.getId());
		return "addtask";
	}
	
	@PostMapping("/addtask")
	public String addTask( Task task,
			//@RequestParam("description") String description, @RequestParam("dueDate") LocalDate dueDate,
			Model model,RedirectAttributes redir) {
		
		User user = (User) session.getAttribute("user");
		// For this URL, make sure there is a user on the session.
				if (user == null) {
					// if not, send them back to the login page with a message.
					redir.addFlashAttribute("message", "Wait! Please log in.");
					return "redirect:/login";
				}
				//System.out.println("UserID " + user.getId());
				// if the user is logged in, proceed as normal.
				//Task task=new Task();
//				task.setDescription(description);
//				task.setDueDate(dueDate);
				task.setComplete(false);
				task.setUser(user);
				taskDao.save(task);
				return "redirect:/task-list";
		 
	}
	
	@RequestMapping("/deleteTask")
	public String deleteTask(@RequestParam("id") Long id) { 
		
		taskDao.deleteById(id);
		return "redirect:/task-list";
	}
	
	@RequestMapping("/updatecompletetask")
	public String updateCompleteTask(@RequestParam("id") Task task) {
		//Task task= taskDao.findById(id).get();
		User user = (User) session.getAttribute("user");
		// For this URL, make sure there is a user on the session.
				if (user == null) {
					// if not, send them back to the login page with a message. 
					return "redirect:/login";
				}
				task.setComplete(!task.isComplete());
				System.out.println(!task.isComplete());
				task.setUser(user);
				taskDao.save(task); 
		return "redirect:/task-list";
	}

}

