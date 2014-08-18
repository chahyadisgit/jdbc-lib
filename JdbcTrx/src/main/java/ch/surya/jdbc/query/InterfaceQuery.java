/**
 * 
 */
package ch.surya.jdbc.query;

import java.sql.ResultSet;
import java.util.TreeMap;

import ch.surya.jdbc.db.DbBuilder;

/**
 * @author Surya Chahyadi
 * @since August 11, 2014
 */
public interface InterfaceQuery {
	public ResultSet inquiry(DbBuilder builder, String fields,
			String table, TreeMap<String, Object> filter, String orderBy);

	public int updateSQL(DbBuilder builder, String table, TreeMap<String, Object> paramValue,
			TreeMap<String, Object> filter);

	public int insertSQL(DbBuilder builder, String table,
			TreeMap<String, Object> keyValue);
	
	public int deleteSQL(DbBuilder builder, String table,
			TreeMap<String, Object> keyValue);
}
