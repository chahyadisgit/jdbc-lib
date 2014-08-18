package ch.surya.jdbc;

import ch.surya.jdbc.db.DbBuilder;
import ch.surya.jdbc.db.DbDirector;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) throws Exception {
		DbDirector director = new DbDirector();
		DbBuilder builder = new ClientParamDb();
		director.getConnection(builder);
	}
}
