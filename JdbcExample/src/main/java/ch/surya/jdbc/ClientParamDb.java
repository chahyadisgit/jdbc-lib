/**
 * 
 */
package ch.surya.jdbc;

import ch.surya.jdbc.db.DbBuilder;
import ch.surya.jdbc.db.InterfaceParamDb;

/**
 * @author SXCHAH
 * 
 */
public class ClientParamDb extends DbBuilder {

	@Override
	public InterfaceParamDb getIntParamDb() {
		intParamDb = new SetupDb();
		
		return intParamDb;
	}

}
