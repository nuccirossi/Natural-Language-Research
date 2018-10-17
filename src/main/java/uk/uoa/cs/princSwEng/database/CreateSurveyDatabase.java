package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import uk.uoa.cs.princSwEng.resource.Survey;

public class CreateSurveyDatabase
{
private static final String STATEMENT5 = "INSERT INTO SURVEYS (corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5) VALUES (?,?,?,?,?,?,?,?,?);";
private final Connection con;
private final Survey surv;


public CreateSurveyDatabase(final Connection con, Survey survey)
{
		this.con = con;
		this.surv = survey
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public List<Sentence> createSurvey() throws SQLException
{
		PreparedStatement pstmt = null;

		ResultSet rs = null;


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
