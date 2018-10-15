package uk.uoa.cs.princSwEng.resource;

import com.fasterxml.jackson.core.*;
import java.io.*;


public class Sentence extends Resource
{

private final int id;
private final String internal_id;
private final String text;


public Sentence(final int id, String internal_id, String text)
{
		this.id = id;
		this.internal_id = internal_id;
		this.text = text;
}

/**
 * Returns the id of the Sentence.
 *
 * @return the id of the Sentence.
 */

public final int getSentenceId()
{
		return id;
}


/**
 * Returns the internal_id of the Sentence.
 *
 * @return the internal_id of the Sentence.
 */

public final String getSentenceInternalId()
{
		return internal_id;
}



/**
 * Returns the text of the Sentence.
 *
 * @return the text of the Sentence.
 */

public final String getSentenceText()
{
		return text;
}

// @Override
// public final void toJSON(final OutputStream out) throws IOException
// {
// 		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

// 		jg.writeStartObject();
// 		jg.writeFieldName("Category");
// 		jg.writeStartObject();
// 		jg.writeStringField("Category name", categoryName);
// 		jg.writeEndObject();
// 		jg.writeEndObject();
// 		jg.flush();
// }

/**
 * Creates a {@code category} from its JSON representation.
 *
 * @param in the input stream containing the JSON document.
 *
 * @return the {@code category} created from the JSON representation.
 *
 * @throws IOException if something goes wrong while parsing.
 */

// public static Category fromJSON(final InputStream in) throws IOException
// {
// 		String jcategoryName = null;


// 		final JsonParser jp = JSON_FACTORY.createParser(in);

// 		while ("Category".equals(jp.currentName()) == false)
// 		{
// 				if(jp.nextToken() == null)
// 				{
// 						throw new IOException("Unable to parse JSON: no category object found");
// 				}
// 		}

// 		while (jp.nextToken() != JsonToken.END_OBJECT)
// 		{
// 				if (jp.currentToken() == JsonToken.FIELD_NAME)
// 				{
// 						switch(jp.currentName())
// 						{
// 						case "Category name":
// 								jp.nextToken();
// 								jcategoryName = jp.getText();
// 								break;

// 						}
// 				}

// 		}

// 		return new Category(jcategoryName);

// }
}
