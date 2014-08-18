package ch.surya.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import ch.surya.jdbc.db.DbBuilder;
import ch.surya.jdbc.db.DbDirector;
import ch.surya.jdbc.query.QueryNative;
import junit.framework.TestCase;

public class QueryTest extends TestCase {
	DbDirector director = new DbDirector();
	DbBuilder builder = new ClientParamDb();
	QueryNative query = new QueryNative();
	String table = "M_SURYA M";

	public void testInquiry() {
		String fields = "M.TEST_ID, M.R_NAME, R_SEX";
		TreeMap<String, Object> filter = new TreeMap<String, Object>();
		// filter.put("M.R_NAME", "Surya");
		ResultSet rs = query.inquiry(builder, fields, table, filter, "");
		try {
			int i = 0;
			while (rs.next()) {
				i++;
				System.out.println(">> Result: " + rs.getString(1) + " - "
						+ rs.getString(2));
			}
			if (i == 0) {
				System.out.println("message: No Data Found!");
			}
			assertTrue(true);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				director.closeConnection(null, null, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testInsertSQL() {
		String fields = "NVL(MAX(M.TEST_ID), 0) + 1";
		ResultSet rs = query.inquiry(builder, fields, table, null, null);
		try {
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			// close connection
			try {
				director.closeConnection(null, null, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// insert new data
			TreeMap<String, Object> keyValue = new TreeMap<String, Object>();
			keyValue.put("M.TEST_ID", id);
			keyValue.put("M.R_NAME", "Surya");
			keyValue.put("M.R_SEX", "Pria");
			int result = query.insertSQL(builder, table, keyValue);
			if (result > 0) {
				System.out
						.println("=============== after insert ====================");
				testInquiry();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	public void testUpdateSQL() {
		try {
			TreeMap<String, Object> paramValue = new TreeMap<String, Object>();
			paramValue.put("M.R_NAME", new String("Surya"));

			TreeMap<String, Object> filter = new TreeMap<String, Object>();
			filter.put("M.TEST_ID", new Integer(1));
			int result = query.updateSQL(builder, table, paramValue, filter);
			if (result > 0) {
				System.out.println("========== After Update =================");
				testInquiry();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDeleteSQL() {
		try {
			System.out
					.println("=============== try to deleted ===========================");
			int result = query.deleteSQL(builder, table, null);
			if (result > 0) {
				System.out.println("deleted success");
			} else {
				System.out.println("deleted failed!");
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
