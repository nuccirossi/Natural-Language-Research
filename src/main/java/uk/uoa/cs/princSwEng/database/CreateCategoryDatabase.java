package uk.uoa.cs.princSwEng.database;

import uk.uoa.cs.princSwEng.resource.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CreateCategoryDatabase
{
private static final String STATEMENT = "INSERT INTO DB.Category (categoryName) VALUES (?)";
private final Connection con;
private final Category category;

public CreateCategoryDatabase(final Connection con, final Category category)
{
		this.con = con;
		this.category = category;
}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
public void createCategory() throws SQLException
{
		PreparedStatement pstmt = null;

		try
		{
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, category.getCategoryName());
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
