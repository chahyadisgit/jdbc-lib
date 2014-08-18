/**
 * 
 */
package ch.surya.jdbc;

import ch.surya.jdbc.db.InterfaceParamDb;
import ch.surya.jdbc.model.ParamDb;

/**
 * @author SXCHAH
 * 
 */
public class SetupDb implements InterfaceParamDb {

	public ParamDb getSetupDB() {
		ParamDb paramDb = new ParamDb("172.24.232.23", "uatstr",
				InterfaceParamDb.PORT_ORACLE, "Concorde", "r1cky",
				InterfaceParamDb.DRIVER_ORACLE);

		return paramDb;
	}

}
