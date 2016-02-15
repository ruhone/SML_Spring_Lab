package se.sml.sdj;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.sml.sdj.model.Issue;
import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.TeamService;
import se.sml.sdj.service.UserService;
import se.sml.sdj.service.WorkItemService;
import se.sml.sdj.service.exception.ServiceException;

public final class Main {

	public static void main(String[] args) throws ServiceException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.sml.sdj");
			context.refresh();

			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);

			// Initiate and create work-items
			WorkItem workItem1 = workItemService.save(new WorkItem("App", "An app tha will make me god", "2001", "Done"));
			WorkItem workItem2 = workItemService.save(new WorkItem("App", "An app tha will make me bad", "2002", "Started"));
			WorkItem workItem3 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2003", "Done"));
			WorkItem workItem4 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2004", "Started"));
			WorkItem workItem5 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2005", "Started"));
			WorkItem workItem6 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2006", "Started"));

			// Initiate users
			User user1 = new User("CG16-12345", "Carl Gustav", "Bernadotte", "1001", "Active");
			User user2 = new User("SiBe-12345", "Silvia", "Bernadotte", "1002", "Active");
			User user3 = new User("ViCotte-12", "Victoria", "Bernadotte", "1003", "Active");
			User user4 = new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "1004", "Active");

			// Add work-items to users
			user1.addWorkItem(workItem1);
			user1.addWorkItem(workItem2);
			user1.addWorkItem(workItem3);
			user1.addWorkItem(workItem4);
			user2.addWorkItem(workItem5);

			// Initiate teams
			Team team1 = new Team("Hovet");
			Team team2 = new Team("PR");
		
			// add users to a team
			team1.addUser(user1);
			team1.addUser(user2);
			team1.addUser(user3);
			team2.addUser(user4);

			// Initiate issues
			Issue issue1 = new Issue("Appen suger, knugen är missnöjd");
			Issue issue2 = new Issue("Appen funkar, knugen är nöjd");
		
			// Create users
			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);

			// Create teams
			teamService.save(team1);
			teamService.save(team2);

			// Create work-items
			workItemService.save(workItem1);
			
			
			// User queries:

			// Update Status for a user or make a user inactive
			System.out.println(userService.updateStatus("CG16-12345", "Inactive"));
			// Update: way 2
//			userService.findByUserNumber("1001").setStatus("Inactive");
//			userService.save(userService.findByUserNumber("1001"));
//			
			// Find user by user number
//			System.out.println("\nFind user by user number:\n" + userRepository.findByUserNumber("1"));

			// Find user by first name OR/AND last name OR/AND user name
//			userRepository.findByLastNameContaining("Bern").forEach(System.out::println);
//			userRepository.findByFirstNameAndLastName("Silvia", "Bernadotte").forEach(System.out::println);
//			userRepository.findByFirstNameAndLastNameAndUsername("Carl Gustav", "Bernadotte", "CG16-12345").forEach(System.out::println);
					
			// Get all users from a team
//			System.out.println("\nAll Users from a Team:");
//			teamRepository.findUsersByTeam("Hovet").forEach(System.out::println);
			
			
			// Team queries:

			// Update Status for a team or make a team inactive
//			team2.setName("Pride");
//			teamService.save(team2);
			
			// Get all teams
//			teamRepository.findAll().forEach(System.out::println);	

			
			// WorkItem queries:

			// Change status of an work-item
//			System.out.println("\nUpdate work-items:");
//			System.out.println(workItemService.updateStatus("2005", "Unstarted"));

			// Delete an work-item: No actual delete takes from in the Database. Instead the status of the work-item is changed 
			
			// Get all work-items for specified Status:
//			System.out.println("\nAll WorkItem for specified Status:");
//			workItemService.findByStatus("Unstarted").forEach(System.out::println);
//
			// Get all work-items for a specified team:
//			System.out.println("\nAll WorkItems for a specified Team:");
//			workItemService.findWorkItemsByTeam("Hovet").forEach(System.out::println);
//
			// Get all work-items for a specified user:
//			System.out.println("\nAll WorkItems from a User:");
//			userService.findWorkItemsByUser("CG16-12345").forEach(System.out::println);
//
			// Get all work-items containing a specified text-string:
//			System.out.println("\nAll WorkItem containing specified text-string:");
//			workItemService.findByDescriptionContaining("ugly").forEach(System.out::println);

			
			// Issue queries:
			
			// Add an issue to a work item
//			System.out.println("Add an issue:\n" + workItem1);
//			System.out.println(workItemService.addIssue(workItem1, issue1));
			
			// Update an issue
//			issue2.setIssue("Ny issue");
//			System.out.println("\nUpdate an issue:\n" + workItemService.addIssue(workItem3, issue2) +"\n");
			
			// Get all work-items that has issues
//			System.out.println("Get all work-items that have issues:");
//			workItemService.getAllByIssue().forEach(System.out::println);
		}
	}
}
