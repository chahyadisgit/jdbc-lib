/**
 * 
 */
package ch.surya.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ch.surya.jdbc.model.ParamDb;

/**
 * @author Surya Chahyadi
 * @since august 08, 2014
 */
public class DbDirector {
	private Logger log = Logger.getLogger(this.getClass());

	public Connection getConnection(DbBuilder builder) {
		try {
			ParamDb paramDb = builder.getIntParamDb().getSetupDB();
			Class.forName(paramDb.getDriver()).newInstance();
			Connection conn = DriverManager.getConnection(setupURL(paramDb),
					paramDb.getUsername(), paramDb.getPassword());

			log.info("Success connect to database");

			return conn;
		} catch (ClassNotFoundException ex) {
			log.error("Error: unable to load driver class!");
		} catch (IllegalAccessException ex) {
			log.error("Error: access problem while loading!");
		} catch (InstantiationException ex) {
			log.error("Error: unable to instantiate driver!");
		} catch (SQLException e) {
			log.error("Error database connection");
		}

		return null;
	}

	private String setupURL(ParamDb paramDb) {
		String result = "";
		String[] separator = new String[] { ":", "/" };
		String driver = paramDb.getDriver().toLowerCase();

		if (driver.indexOf("mysql") > 0 || driver.indexOf("postgresql") > 0) {
			result = (driver.indexOf("mysql") > 0 ? InterfaceParamDb.DM_MYSQL
					: InterfaceParamDb.DM_POSTGRE)
					+ paramDb.getHost()
					+ separator[1] + paramDb.getHostName();
		} else if (driver.indexOf("oracle") > -1) {
			result = InterfaceParamDb.DM_ORACLE + paramDb.getHost()
					+ separator[0] + paramDb.getPort() + separator[0]
					+ paramDb.getHostName();
		}

		System.out.println(">> result: " + result);
		return result;
	}

	/**
	 * Close Connection.
	 * 
	 * @param conn
	 *            {@link Connection}
	 * @param ps
	 *            {@link PreparedStatement}
	 * @param rs
	 *            {@link ResultSet}
	 * @throws Exception
	 */
	public void closeConnection(Connection conn, PreparedStatement ps,
			ResultSet rs) throws Exception {
		if (null != rs) {
			rs.close();
		}

		if (null != ps) {
			ps.close();
		}

		if (null != conn) {
			conn.close();
		}
	}
}
