/**
 * 
 */
package ch.surya.jdbc.model;

/**
 * ParamDb<br/>
 * Model Parameter for database.
 * 
 * @author SXCHAH
 * @since august 08, 2014
 */
public class ParamDb extends BaseEntity<ParamDb> {
	private String host;
	private String hostName;
	private String port;
	private String username;
	private String password;
	private String driver;

	/**
	 * Constructor.
	 */
	public ParamDb() {
	}

	/**
	 * Constructor with parameter.
	 * 
	 * @param host
	 *            {@link String}<br/>
	 *            IP database.
	 * @param hostName
	 *            {@link String}<br/>
	 *            SID database.
	 * @param port
	 *            {@link String}<br/>
	 *            Port Database.
	 * @param username
	 *            {@link String}<br/>
	 *            Username for login into database.
	 * @param password
	 *            {@link String}<br/>
	 *            Password for login into database.
	 */
	public ParamDb(String host, String hostName, String port, String username,
			String password, String driver) {
		super();
		this.host = host;
		this.hostName = hostName;
		this.port = port;
		this.username = username;
		this.password = password;
		this.driver = driver;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

}
