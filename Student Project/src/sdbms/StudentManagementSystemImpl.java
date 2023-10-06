package sdbms;
import java.util.*;
import customsorting.*;

import customException.InvalidChoiceException;
import customException.StudentNotFoundException;

//implementation layer/class
public class StudentManagementSystemImpl implements StudentManagementSystem
{
	Scanner s=new Scanner(System.in);


	/**
	 * we are using collection as our database
	 * key is String -> Student id & value is Student -> Student Object
	 */

	Map<String,Student> db=new LinkedHashMap<String,Student>();

	@Override
	public void addStudent()
	{	
		//accept age
		System.out.println("Enter the Age:");
		int age=s.nextInt();

		//accept the marks
		System.out.println("Enter the Marks");
		int marks=s.nextInt();

		//accept name
		System.out.println("Enter the Name");
		String name=s.next();


		//creating Student Instance
		Student std=new Student(age,name,marks);
		//adding student into database
		db.put(std.getId(), std);
		//Printing the Student Id
		System.out.println("Student Record Added Succesfully");
		System.out.println("Student Id is:"+std.getId());
	}
	@Override
	public void displayStudent()
	{
		//accepting id from the user
		System.out.println("Enter the Id:");
		String std=s.next();
		std=std.toUpperCase();//converting it to uppercase coz we have uppercase in db

		if(db.containsKey(std))
		{
			System.out.println("Student Record Found");
			Student st=db.get(std);

			System.out.println("Id :"+st.getId());
			System.out.println("Age:"+st.getName());
			System.out.println("Name:"+st.getName());
			System.out.println("Marks"+st.getMarks());
		}
		else {
			try {
				String message="Student Record with "+std+"is not Found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void displayAllStudents()
	{
		/**
		 * checking if the database is Empty or not
		 * if it is Empty we will be Throwing an Exception
		 */
		if(!db.isEmpty())
		{
			System.out.println("Student Records Are as Follows");
			System.out.println("==============================");
			//converting map(DataBase) into Set
			Set<String> keys =db.keySet();
			for(String key:keys)//printing the reference variable as toString() is Overridden
				System.out.println(db.get(key));
		}
		else
		{	try {
			String message="No Student Records to Display";
			throw new StudentNotFoundException(message);
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}

		}

	}
	@Override
	public void removeStudent()
	{
		System.out.println("Enter the Id to be Removed");
		String id=s.next();
		id=id.toUpperCase();

		if(db.containsKey(id))
		{
			db.remove(id);
			System.out.println("Student Record with "+id+" has been Deleted");
		}
		else
		{
			try {
				String message="Student record not found with id:"+id;
				throw new StudentNotFoundException(message);

			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void removeAllStudents()
	{
		if(!db.isEmpty()) 
		{
			System.out.println("No of Student Records Before Deleting"+db.size());
			db.clear();
			System.out.println("No of Student Records After Deleting "+db.size());
			System.out.println("Student Records Deleted Succesfully");
		}
		else 
		{
			try {
				String message="No Student Records to Delete";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}
	@Override
	public void updateStudent()
	{
		System.out.println("Enter the Student Id!");
		String id=s.next().toUpperCase();

		if(db.containsKey(id))
		{
			Student std=db.get(id);
			//menu Driven Program
			System.out.println("1:Update Age \n2:Update Name\n3:UpdateMarks");
			System.out.println("Enter your choice");
			int choice=s.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter the Age");
				int age=s.nextInt();
				std.setAge(age);
				System.out.println("Age updated Successfully");
				break;
			case 2:
				System.out.println("Enter the Name");
				String name=s.next();
				std.setName(name);
				System.out.println("Name Updated Successfully");
				break;
			case 3:
				System.out.println("Enter the Marks");
				int marks=s.nextInt();
				std.setMarks(marks);
				System.out.println("Marks Updated succesfully");
				break;
			default:
				try {
					String message="Invalid Option, Kinldy Enter a valid Option";
					throw new InvalidChoiceException(message);
				}
				catch(InvalidChoiceException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else {
			try {
				String message="Student with id: "+id+" is not found";
				throw new StudentNotFoundException(message);

			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void countStudents()
	{
		System.out.println("Number of Student Records :"+db.size());
	}
	@Override
	public void sortStudents()
	{
		if(db.size()>=2) {
			//reference of list storing object of Arraylist->
			List<Student> li=new ArrayList<Student>();

			//converting db(map) into set using keySet()
			Set<String> keys=db.keySet();


			//traversing keys(IDs)
			for(String key:keys)
			{
				Student std=db.get(key);//get the value from map 
				li.add(std);//store it into list
			}

			System.out.println("1:Sort Student By Id");
			System.out.println("2:Sort Student By Age");
			System.out.println("3:Sort Student By Name");
			System.out.println("4:Sort Student By Marks");
			System.out.println("Enter Your Choice");

			int choice=s.nextInt();

			switch(choice) {
			case 1:
				Collections.sort(li, new SortStudentById());

				for(Student std:li) {
					System.out.println(std);
				}
				break;
			case 2:
				Collections.sort(li, new SortStudentByAge());
				for(Student std:li)
				{
					System.out.println(std);
				}
				break;

			case 3:
				Collections.sort(li,new SortStudentByName());
				for(Student std:li)
				{
					System.out.println(std);
				}
				break;
			case 4:
				Collections.sort(li,new SortStudentByMarks());
				for(Student std:li)
				{
					System.out.println(std);
				}
				break;
			default:
				try {
					String message="You Have Entered Wrong Choice please Enter Valid Choice";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}

			}
		}
		else {
			try {
				String message="No Sufficient Records To Sort";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}



	}
	@Override
	public void getStudentWithHighestMarks()
	{
		if(db.size()>=2)
		{
			//converting map into list
			List<Student> li=new ArrayList<Student>();
			
			Set<String> keys=db.keySet();
			
			for(String key:keys)
			{
				Student std=db.get(key);
				li.add(std);
			}
			Collections.sort(li,new SortStudentByMarks());
			System.out.println("Student With Highest Marks");
			System.out.println(li.get(li.size()-1));
		}
		else {
			try {
				String message="No Student records Found";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}

	}
	@Override
	public void getStudentWithLowestMarks()
	{
		if(db.size()>=2)
		{
			//converting map into list
			List<Student> li=new ArrayList<Student>();
			
			Set<String> keys=db.keySet();
			
			for(String key:keys)
			{
				Student std=db.get(key);
				li.add(std);
			}
			Collections.sort(li,new SortStudentByMarks());
			System.out.println("Student With Highest Marks");
			System.out.println(li.get(0));
		}
		else {
			try {
				String message="No Student records Found";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
