package uk.uoa.cs.princSwEng.resource;

import com.fasterxml.jackson.core.*;
import java.io.*;


public class Category extends Resource
{

private final String categoryName;


public Category(final String categoryName)
{
		this.categoryName = categoryName;
}

/**
 * Returns the name of the category.
 *
 * @return the name of the category.
 */

public final String getCategoryName()
{
		return categoryName;
}

@Override
public final void toJSON(final OutputStream out) throws IOException
{
		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();
		jg.writeFieldName("Category");
		jg.writeStartObject();
		jg.writeStringField("Category name", categoryName);
		jg.writeEndObject();
		jg.writeEndObject();
		jg.flush();
}

/**
 * Creates a {@code category} from its JSON representation.
 *
 * @param in the input stream containing the JSON document.
 *
 * @return the {@code category} created from the JSON representation.
 *
 * @throws IOException if something goes wrong while parsing.
 */

public static Category fromJSON(final InputStream in) throws IOException
{
		String jcategoryName = null;


		final JsonParser jp = JSON_FACTORY.createParser(in);

		while ("Category".equals(jp.currentName()) == false)
		{
				if(jp.nextToken() == null)
				{
						throw new IOException("Unable to parse JSON: no category object found");
				}
		}

		while (jp.nextToken() != JsonToken.END_OBJECT)
		{
				if (jp.currentToken() == JsonToken.FIELD_NAME)
				{
						switch(jp.currentName())
						{
						case "Category name":
								jp.nextToken();
								jcategoryName = jp.getText();
								break;

						}
				}

		}

		return new Category(jcategoryName);

}
}
