/**
 * 
 */
package ch.surya.jdbc.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import ch.surya.jdbc.db.DbDirector;

/**
 * @author Surya Chahyadi
 * @since August 11,
 */
public class AbstractQueryPattern {
	protected DbDirector director = new DbDirector();
	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * inquiryPattern<br/>
	 * String query pattern.
	 * 
	 * @param fields
	 *            {@link String}
	 * @param table
	 *            {@link String}
	 * @param filter
	 *            {@link Map}
	 * @return
	 */
	protected String inquiryPattern(String fields, String table,
			Map<String, Object> filter, String orderBy) {
		String queryPattern = "SELECT {0} FROM {1} WHERE 1=1 {2} ORDER BY {3}";
		String whereSql = (null == filter ? "" : whereClauseKey(filter, false));

		String sql = MessageFormat.format(queryPattern, new Object[] { fields,
				table, whereSql, (null == orderBy || orderBy.equals("") ? "1" : orderBy)});
		log.info(">> sql: " + sql);

		return sql;
	}

	/**
	 * insertQueryPattern<br/>
	 * String insert query pattern.
	 * 
	 * @param table
	 *            {@link String}<br/>
	 *            table name.
	 * @param keyValue
	 *            {@link Map}<br/>
	 *            Key and Value data.
	 * @return {@link String}<br/>
	 *         String sql Insert.
	 */
	protected String insertQueryPattern(String table,
			Map<String, Object> keyValue) {
		String queryPattern = "INSERT INTO {0} ({1}) VALUES({2})";
		String fields = getFields(keyValue);
		String values = "";
		for (int i = 0; i < keyValue.size(); i++) {
			values += "?, ";
		}
		values = values.substring(0, values.length() - 2);

		String sql = MessageFormat.format(queryPattern, new Object[] { table, fields,
				values });
		log.info(">> sql: " + sql);
		
		return sql;
	}

	/**
	 * deleteQueryPattern<br/>
	 * String delete query pattern.
	 * 
	 * @param table
	 *            {@link String}<br/>
	 *            Table Name.
	 * @param keyValue
	 *            {@link Map}<br/>
	 *            Key and Value parameters.
	 * @return {@link String}<br/>
	 *         String sql delete.
	 */
	protected String deleteQueryPattern(String table,
			Map<String, Object> keyValue) {
		String queryPattern = "DELETE {0} WHERE 1=1 {1}";
		String whereSql = (null == keyValue ? "" : whereClauseKey(keyValue, true));
		String sql = MessageFormat.format(queryPattern, new Object[] { table,
				whereSql });
		log.info(">> sql: " + sql);
		
		return sql;
	}

	/**
	 * updateQueryPattern<br/>
	 * String Update query pattern.
	 * 
	 * @param table
	 * <br/>
	 *            Table name.
	 * @return {@link String}<br/>
	 *         String SQL update.
	 */
	protected String updateQueryPattern(String table,
			Map<String, Object> paramValue, Map<String, Object> filter) {
		String queryPattern = "UPDATE {0} SET {1} WHERE 1=1 {2}";
		String setSql = setParamKey(paramValue);
		String whereSql = (null == filter ? "" : whereClauseKey(filter, true));
		String sql = MessageFormat.format(queryPattern, new Object[] { table,
				setSql, whereSql });
		log.info(">> sql:" + sql);

		return sql;
	}

	private String whereClauseKey(Map<String, Object> filter, boolean isDML) {
		String where = "";

		for (Map.Entry<String, Object> map : filter.entrySet()) {
			if (map.getKey().indexOf(":x") < 0) {
				where += " AND "
						+ (map.getValue() instanceof Date ? map.getKey()
								.replace(":x", "") : map.getKey());
			}

			if (map.getValue() instanceof Integer
					|| map.getValue() instanceof Number) {
				where += " = ?";
			} else if (map.getValue() instanceof Date) {
				if (map.getKey().indexOf(":x") > 1) {
					where += " BETWEEN ?";
				} else {
					where += " AND ?";
				}
			} else {
				if (isDML) {
					where += " = ?";
				} else {
					where += " LIKE '%' || ? ||'%'";
				}
			}
		}

		return where;
	}

	private String setParamKey(Map<String, Object> paramValue) {
		String result = "";
		for (Map.Entry<String, Object> entry : paramValue.entrySet()) {
			result += entry.getKey() + " = ?, ";
		}
		result = result.substring(0, result.length() - 2);

		return result;
	}

	private String getFields(Map<String, Object> keyValue) {
		String result = "";

		for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
			result += entry.getKey() + ", ";
		}

		return result.substring(0, result.length() - 2);
	}

	/**
	 * getValue<br/>
	 * setup parameter value;
	 * 
	 * @param map
	 *            {@link Map}
	 * @param ps
	 *            {@link PreparedStatement}
	 * @return {@link PreparedStatement}
	 * @throws SQLException
	 */
	protected PreparedStatement getValue(Map<String, Object> map,
			PreparedStatement ps, int idx) throws SQLException {

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Date) {
				ps.setDate(idx++, (java.sql.Date) entry.getValue());
			} else if (entry.getValue() instanceof Integer) {
				ps.setInt(idx++, (Integer) entry.getValue());
			} else if (entry.getValue() instanceof Long) {
				ps.setLong(idx++, (Long) entry.getValue());
			} else {
				System.out.println(idx + " - " + entry.getValue());
				ps.setString(idx++, (String) entry.getValue());
			}
		}

		return ps;
	}
}
