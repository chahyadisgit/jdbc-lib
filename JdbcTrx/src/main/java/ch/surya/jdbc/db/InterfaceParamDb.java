/**
 * 
 */
package ch.surya.jdbc.db;

import ch.surya.jdbc.model.ParamDb;

/**
 * @author SXCHAH
 * 
 */
public interface InterfaceParamDb {
	// PORT
	public final static String PORT_ORACLE = "1521";
	public final static String PORT_MYSQL = "3306";
	public final static String PORT_POSTGRE = "5432";

	// Driver
	public final static String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	public final static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	public final static String DRIVER_POSTGRE = "org.postgresql.Driver";
	
	public final static String DM_ORACLE = "jdbc:oracle:thin:@";
	public final static String DM_MYSQL = "jdbc:mysql://";
	public final static String DM_POSTGRE = "jdbc:postgresql://";

	public ParamDb getSetupDB();
}
