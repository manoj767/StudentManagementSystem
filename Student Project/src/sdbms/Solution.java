package sdbms;
import java.util.*;
//import customException.*;


import customException.InvalidChoiceException;
public class Solution
{

	public static void main(String[] args) 
	{
		System.out.println("Welcome To Student Management System");
		System.out.println("------------------------------------");


		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		//achieving abstraction by using upcasting
		StudentManagementSystem sms=new StudentManagementSystemImpl();


		while(true)
		{
			System.out.println("1:Add Student ");
			System.out.println("2:Display Student ");
			System.out.println("3:DisplayAll Students ");
			System.out.println("4:Remove a Student ");
			System.out.println("5:Remove all Student ");
			System.out.println("6:Update Student ");
			System.out.println("7:Count Students ");
			System.out.println("8:sort Students");
			System.out.println("9:Student With Highest Marks");
			System.out.println("10:Student With Lowest Marks");
			System.out.println("11:EXIT\nEnter Your Choice");
			int choice=s.nextInt();

			switch(choice)
			{
			case 1:
				sms.addStudent();
				break;

			case 2:
				sms.displayStudent();
				break;

			case 3:
				sms.displayAllStudents();
				break;
			case 4:
				sms.removeStudent();
				break;
			case 5:
				sms.removeAllStudents();
				break;
			case 6:
				sms.updateStudent();
				break;
			case 7:
				sms.countStudents();
				break;
			case 8:
				sms.sortStudents();
				break;
			case 9:
				sms.getStudentWithHighestMarks();
				break;
			case 10:
				sms.getStudentWithLowestMarks();
				break;
			case 11:
				System.out.println("Thank You");
				System.exit(0);
			default:
				try {
					String message="Kindly Enter a valid choice";
					throw new InvalidChoiceException(message);
				}
				catch(InvalidChoiceException e) {
					System.out.println(e.getMessage());
				}

			}
			System.out.println("===================");

		}

	}

}
