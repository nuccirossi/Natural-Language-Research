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
private static final String STATEMENT5 = "INSERT INTO SURVEYS (corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5) VALUES (?,?,?,?,?,?,?,?,?) RETURNING *";
private static final String STATEMENT10 = "INSERT INTO SURVEYS (corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
private static final String STATEMENT15 = "INSERT INTO SURVEYS (corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10, sent11, sent12, sent13, sent14, sent15) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
private final Connection con;
private final Survey surv;


public CreateSurveyDatabase(final Connection con, Survey survey)
{
		this.con = con;
		this.surv = survey;
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public int createSurvey() throws SQLException
{
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int result = -1;


		try 
		{
				switch (surv.getSurveyNum())
				{
					case 5:
					pstmt = con.prepareStatement(STATEMENT5);
					pstmt.setString(1, surv.getSurveyCorpora());
					pstmt.setString(2, surv.getSurveyTranslator());
					pstmt.setString(3, surv.getSurveyLanguages());
					pstmt.setInt(4, surv.getSurveyNum());
					for (int i=0; i<5; i++)
						pstmt.setInt(i+5, surv.getSurveyId()[i]);

					break;
					case 10:
					pstmt = con.prepareStatement(STATEMENT10);
					pstmt.setString(1, surv.getSurveyCorpora());
					pstmt.setString(2, surv.getSurveyTranslator());
					pstmt.setString(3, surv.getSurveyLanguages());
					pstmt.setInt(4, surv.getSurveyNum());
					for (int i=0; i<10; i++)
						pstmt.setInt(i+5, surv.getSurveyId()[i]);
					break;
					case 15:
					pstmt = con.prepareStatement(STATEMENT15);
					pstmt.setString(1, surv.getSurveyCorpora());
					pstmt.setString(2, surv.getSurveyTranslator());
					pstmt.setString(3, surv.getSurveyLanguages());
					pstmt.setInt(4, surv.getSurveyNum());
					for (int i=0; i<15; i++)
						pstmt.setInt(i+5, surv.getSurveyId()[i]);	
					break;
					default: 
					System.err.println("Error, switch case default value in CreateSurveyDatabase");
				}
				System.err.println("Before executing executeQuery on CreateSurveyDatabase");
				rs = pstmt.executeQuery();

				if (rs.next())
				{
					result = rs.getInt("id");
				}

		}
		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in CreateSurveyDatabase");
			System.err.println(ex.getMessage());
		}
		finally
		{

			if (pstmt != null)
			{
					pstmt.close();
			}

			con.close();
		}
		return result;
}
}
