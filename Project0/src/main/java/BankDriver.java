import java.util.List;
import java.util.Scanner;

import com.example.Services.CustomerServices;
import com.example.Services.EmployeeServices;
import com.example.Services.UserServices;
import com.example.dao.AccountDao;
import com.example.dao.AccountDaoDB;
import com.example.dao.ApplicationDao;
import com.example.dao.ApplicationDaoDB;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.models.Account;
import com.example.models.User;

public class BankDriver {
	private static AccountDao acDao = new AccountDaoDB();
	private static ApplicationDao apDao = new ApplicationDaoDB();
	private static UserDao uDao = new UserDaoDB();
	private static UserServices uServ = new UserServices(uDao);
	private static EmployeeServices eServ = new EmployeeServices(acDao,apDao);
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Bank!");
		boolean done = false;
		User u = null;
		while(!done) {
			if(u==null) {
				System.out.print("Login or Signup? Prss 1 to Login, Press 2 to SignUp, or Press 3 to Exit the Bank");
				int choice = Integer.parseInt(in.nextLine());
				switch(choice) {
				case 1:
					System.out.print("Please enter your username: ");
					String username = in.nextLine();
					System.out.print("Please enter your password: ");
					String password = in.nextLine();
					try {
						u = uServ.signIn(username, password);
						System.out.println("Welcome "+ u.getFirstName());
					}catch(Exception e) {
						System.out.println("Username or Password was inccorcet. Please try again.");
					}
					break;
				case 2:
					System.out.print("Please enter you first name: ");
					String first = in.nextLine();
					System.out.print("Please enter your last name: ");
					String last = in.nextLine();
					System.out.print("Please enter your email: ");
					String email = in.nextLine();
					System.out.print("Please enter a password: ");
					String pass = in.nextLine();
					try {
						u = uServ.signUp(first, last, email, pass);
						System.out.println("You may now log in with the username: " + u.getUsername());
						u = null;
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						done = true;
					}
					break;
				case 3:
					done = true;
					break;
				default:
					System.out.println("invalid input.Please try again.");
					break;
				}
				
			}else if(u.getAccess().equals("customer")) {
				CustomerServices cServ = new CustomerServices(u.getId(),acDao,apDao);
				List<Account> accountList = cServ.getUserAccounts();
				if(accountList == null) {
					System.out.println("It appears you do not have any accounts. Would you like to apply for one or check the status of your application?");
					System.out.print("Press 1 to apply for an account, 2 to check the status of your applications or 3 to EXIT the bank :");
					int choice = Integer.parseInt(in.nextLine());
					switch(choice) {
					case 1:
						System.out.print("Please enter your Starting balance:");
						double start = Double.parseDouble(in.nextLine());
						cServ.apply(start);	
						break;
					case 2:
						cServ.checkApplications();
						break;
					case 3:
						done = true;
						break;
					default:
						System.out.println("invalid input.Please try again.");
						break;
					}
				}
				System.out.println("What would you like to do today?");
				System.out.print("Press 1 to view accounts,2 to change pin of an account, 3 to withdrawl, 4 to deposit, 5 to check your applications, 6 to apply for a new account or 7 to EXIT");
				int choice = Integer.parseInt(in.nextLine());
				switch (choice) {
				case 1:
					System.out.println(accountList);
					break;
				case 2:
					System.out.print("Enter the Account number for the Account you want to change: ");
					int acc = Integer.parseInt(in.nextLine());
					System.out.print("Enter your current PIN: ");
					int current = Integer.parseInt(in.nextLine());
					System.out.print("Enter your new PIN: ");
					int pin = Integer.parseInt(in.nextLine());
					cServ.changePin(acc, current, pin);
					break;
				case 3:
					System.out.println("Enter the Account number for the Account you want to withdrawl from: ");
					int account = Integer.parseInt(in.nextLine());
					System.out.println("Enter the ammount you want to withdrawl: ");
					double amnt = Double.parseDouble(in.nextLine());
					cServ.withdrawl(account, amnt);
					break;
				case 4:
					System.out.println("Enter the Account number for the Account you want to deposit into: ");
					int acc = Integer.parseInt(in.nextLine());
					System.out.println("Enter the ammount you want to deposit: ");
					double amount = Double.parseDouble(in.nextLine());
					cServ.deposit(acc, amount);
					break;
					;
				case 5:
					cServ.checkApplications();
					break;
				case 6:
					System.out.print("Please enter your Starting balance:");
					double start = Double.parseDouble(in.nextLine());
					cServ.apply(start);	
					break;
				case 7:
					done = true;
					break;
				default:
					System.out.println("invalid input.Please try again.");
					break;
				}
				
			}else {
				System.out.println("Valued employee, What do you need to do today?");
				System.out.print("Press 1 for view accounts, 2 to review applications or 3 to EXIT");
				int choice = Integer.parseInt(in.nextLine());
				switch(choice) {
				case 1:
					eServ.viewAccounts();
					break;
				case 2:
					eServ.reviewApplications(in);
					break;
				case 3:
					done = true;
					break;
				default:
					System.out.println("invalid input.Please try again.");
					break;
				}
			}
			//bellow closes the while loop
		}
		System.out.println("please come again!");
		//below closes main
	}
}
