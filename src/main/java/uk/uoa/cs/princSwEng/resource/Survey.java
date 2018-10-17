package uk.uoa.cs.princSwEng.resource;

import com.fasterxml.jackson.core.*;
import java.io.*;


public class Survey extends Resource
{

private final String corpora;
private final String translator;
private final String languages;
private final int num; 
private final int[] sent;



public Survey(final String corpora,final String translator,final String languages,final int num, final int[] sent)
{
		this.corpora = corpora;
		this.translator = translator;
		this.languages = languages;
		this.num = num;
		this.sent = sent;
}



/**
 * Returns the corpora of the Survey.
 *
 * @return the corpora of the Survey.
 */

public final String getSurveyCorpora()
{
	return corpora;
}


/**
 * Returns the translator of the Survey.
 *
 * @return the translator of the Survey.
 */

public final String getSurveyTranslator()
{
	return translator;
}



/**
 * Returns the languages of the Survey.
 *
 * @return the languages of the Survey.
 */

public final String getSurveyLanguages()
{
		return languages;
}

public final int getSurveyNum()
{
	return num;
}

public final int[] getSurveyId()
{
	return sent;
}

// @Override
 public final void toJSON(final OutputStream out) throws IOException
{
// 		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

// 		jg.writeStartObject();
// 		jg.writeFieldName("Category");
// 		jg.writeStartObject();
// 		jg.writeStringField("Category name", categoryName);
// 		jg.writeEndObject();
// 		jg.writeEndObject();
// 		jg.flush();
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
