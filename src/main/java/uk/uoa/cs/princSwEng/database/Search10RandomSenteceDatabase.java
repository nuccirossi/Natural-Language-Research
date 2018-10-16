package uk.uoa.cs.princSwEng.database;

import uk.uoa.cs.princSwEng.resource.Sentence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Search10RandomSentenceDatabase
{
private static final String STATEMENT = "SELECT id FROM SENTENCES WHERE internal_id LIKE ? ORDER BY random() LIMIT ?;";
private final Connection con;
private final int number;

public Search10RandomSentenceDatabase(final Connection con, int i)
{
		this.con = con;
		this.number = i;
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public List<Sentence> search10RandomSentece() throws SQLException
{
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		final List<Sentence> sentence = new ArrayList<Sentence>();

		try
		{
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, sentence.getSentenceInternalId());
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
