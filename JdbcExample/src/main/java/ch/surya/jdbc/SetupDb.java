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
		ParamDb paramDb = new ParamDb("172.13.121.12", "testDb",
				InterfaceParamDb.PORT_ORACLE, "merahputih", "garuda",
				InterfaceParamDb.DRIVER_ORACLE);

		return paramDb;
	}

}
