package uk.uoa.cs.princSwEng.database;

import uk.uoa.cs.princSwEng.resource.Sentence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Search10RandomSentenceDatabase
{
private static final String STATEMENT = "SELECT id FROM SENTENCES WHERE internal_id LIKE ? ORDER BY random() LIMIT 10;";
private final Connection con;
private final Sentence sentence;

public Search10RandomSentenceDatabase(final Connection con, final Sentence sentence)
{
		this.con = con;
		this.sentence = sentence;
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public void search10RandomSentece() throws SQLException
{
		PreparedStatement pstmt = null;

		try
		{
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, sentence.getSentenceInternalId());
				pstmt.execute();
		}
		finally
		{
				if (pstmt != null)
				{
						pstmt.close();
				}

				con.close();
		}
}
}
