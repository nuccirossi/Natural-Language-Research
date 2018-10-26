package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Survey;

public class SearchSurveyDatabase
{
	private static final String STATEMENT = "SELECT * FROM SURVEYS WHERE id=?";
	private final Connection con;
	private final int idsurv;

	public SearchSurveyDatabase(final Connection con, int survey)
	{
		this.con = con;
		this.idsurv = survey;
	}

	public Survey SearchSurvey() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Survey sur = null;

		try
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1,idsurv);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				int num = rs.getInt("num");
				int[] sent = new int[num];
				for (int i=0; i<num;i++)
					sent[i] = rs.getInt("sent" + (i+1));
				sur = new Survey(rs.getString("corpora"), rs.getString("translator"), rs.getString("languages"), num, sent);
			}
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in SearchSurveyDatabase");
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return sur;

	}
}