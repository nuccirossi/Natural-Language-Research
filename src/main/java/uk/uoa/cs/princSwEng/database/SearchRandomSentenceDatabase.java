package uk.uoa.cs.princSwEng.database;

import uk.uoa.cs.princSwEng.resource.Sentence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class SearchRandomSentenceDatabase
{
private static final String STATEMENT = "SELECT id FROM SENTENCES WHERE internal_id LIKE ? ORDER BY random() LIMIT ?;";
private final Connection con;
private final int number;
private final String internal_id;

public SearchRandomSentenceDatabase(final Connection con, String internal_id, int i)
{
		this.con = con;
		this.number = i;
		this.internal_id = internal_id;
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public List<Sentence> searchRandomSentence() throws SQLException
{
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		final List<Sentence> sentence = new ArrayList<Sentence>();

		try
		{
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, internal_id);
				pstmt.setInt(2, number);
				rs = pstmt.executeQuery();

				while (rs.next())
				{
					sentence.add(new Sentence(rs.getInt("id")));
				}
		}
		finally
		{
			if (rs != null)
					rs.close();

			if (pstmt != null)
			{
					pstmt.close();
			}

			con.close();
		}
		return sentence;
}
}
