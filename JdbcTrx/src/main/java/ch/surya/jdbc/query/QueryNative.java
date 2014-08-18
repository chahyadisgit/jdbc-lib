/**
 * 
 */
package ch.surya.jdbc.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import ch.surya.jdbc.db.DbBuilder;

/**
 * @author Surya Chahyadi
 * @since August 11, 2014
 */
public class QueryNative extends AbstractQueryPattern implements InterfaceQuery {

	/**
	 * createInquiry<br/>
	 * Generate standard query.
	 * 
	 * @param builder
	 *            {@link DbBuilder}
	 * @param fields
	 *            {@link String}
	 * @param table
	 *            {@link String}
	 * @param filter
	 *            {@link TreeMap} <br/>
	 *            where condition.
	 * @return
	 */
	public ResultSet inquiry(DbBuilder builder, String fields, String table,
			TreeMap<String, Object> filter, String orderBy) {

		Connection conn = director.getConnection(builder);
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(inquiryPattern(fields, table, filter,
					orderBy));
			if (null != filter) {
				ps = getValue(filter, ps, 1);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * insertSQL<br/>
	 * insert data into table.
	 * 
	 * @param builder
	 *            {@link DbBuilder}
	 * @param table
	 *            {@link String} table name.
	 * @param keyValue
	 *            {@link TreeMap}<br/>
	 *            key and value.
	 * @return
	 */
	public int insertSQL(DbBuilder builder, String table,
			TreeMap<String, Object> keyValue) {
		int result = 0;

		if (keyValue.size() > 0) {
			Connection conn = director.getConnection(builder);
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(insertQueryPattern(table, keyValue));
				ps = getValue(keyValue, ps, 1);
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					director.closeConnection(conn, ps, null);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * deleteSQL<br/>
	 * delete transaction.
	 * 
	 * @param builder
	 *            {@link DbBuilder}
	 * @param table
	 *            {@link String} table name.
	 * @param keyValue
	 *            {@link TreeMap} key and value.
	 * @return
	 */
	public int deleteSQL(DbBuilder builder, String table,
			TreeMap<String, Object> keyValue) {
		int result = 0;
		Connection conn = director.getConnection(builder);
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(deleteQueryPattern(table, keyValue));
			if (null != keyValue) {
				ps = getValue(keyValue, ps, 1);
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				director.closeConnection(conn, ps, null);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * updateSQL<br/>
	 * update query.
	 * 
	 * @param builder
	 *            {@link DbBuilder}
	 * @param table
	 *            {@link String}<br/>
	 *            table name.
	 * @param paramValue
	 *            {@link TreeMap}<br/>
	 *            SET key and value.
	 * @param filter
	 *            {@link TreeMap}<br/>
	 *            parameter filter.
	 */
	public int updateSQL(DbBuilder builder, String table,
			TreeMap<String, Object> paramValue, TreeMap<String, Object> filter) {
		int result = 0;

		if (filter.size() > 0) {
			Connection conn = director.getConnection(builder);
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(updateQueryPattern(table,
						paramValue, filter));
				ps = getValue(paramValue, ps, 1); // set parameter
				ps = getValue(filter, ps, paramValue.size() + 1); // where
																	// parameter

				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					director.closeConnection(conn, ps, null);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return result;
	}

}
