package edu.utdallas.codered.halloDoc.model;

import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserDetails {

	private static String[] user_names = new String[] {"himanshu", "vinamra", "parth", "devendra", "batman", "superman", "ironman", "spiderman", "shaktiman"};
	private static String[] user_emails = new String[] {"user1@utdallas.edu", "user2@utdallas.edu", "user3@utdallas.edu"};
	private static String[] locations = new String[] {"Dallas, TX", "Houstan, TX", "Austin, TX", "Bentonville, AR", "seattle, WA", "New york, NY", "Boston, MA", "Jersey City, NJ"};
	private static String[] age_groups = new String[] {"18-24", "25-30", "31-37", "38-45", "46-58", "59-75", "75-90"};
	private static String[] genders = new String[] {"M", "F"};
	
	private String user_name;
	private String user_email;
	private String age_group;
	private String gender;
	private String location;
	
	public static DBObject getRandomDetails () {
		DBObject userDetails = new BasicDBObject();
		userDetails.put("user_name", user_names[new Random().nextInt(user_names.length)]);
		userDetails.put("user_email", user_emails[new Random().nextInt(user_emails.length)]);
		userDetails.put("age_group", age_groups[new Random().nextInt(age_groups.length)]);
		userDetails.put("gender", genders[new Random().nextInt(genders.length)]);
		userDetails.put("location", locations[new Random().nextInt(locations.length)]);
		return userDetails;
	}
	
	public static DBObject getUserDetails (UserDetails userDetails) {
		DBObject userDetailsDb = new BasicDBObject();
		userDetailsDb.put("user_name", userDetails.getUser_name());
		userDetailsDb.put("user_email", userDetails.getUser_email());
		userDetailsDb.put("age_group", userDetails.getAge_group());
		userDetailsDb.put("gender", userDetails.getGender());
		userDetailsDb.put("location", userDetails.getLocation());
		
		return userDetailsDb;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public String getAge_group() {
		return age_group;
	}
	
	public void setAge_group(String age_group) {
		this.age_group = age_group;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
