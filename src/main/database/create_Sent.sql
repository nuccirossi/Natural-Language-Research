
DROP TABLE IF EXISTS Sentences;

CREATE TABLE Sentences (id SERIAL, internal_id VARCHAR(35), sentence VARCHAR(1000), PRIMARY KEY (id));


\copy Sentences(internal_id, sentence) FROM '/Users/fbbfnc/Downloads/corpora/VUA/FinalResults/AwkParsingFinalResult.txt' DELIMITER '|' ENCODING 'latin1'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/MOH-X/FinalResults/MOHAWK.txt'  DELIMITER '|' ENCODING 'UTF-8'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/FLA/FinalResults/1/Affective_with_metaphors_pos.txt'  DELIMITER '|' ENCODING 'UTF-8'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/FLA/FinalResults/2/Affective_with_metaphors_neg.txt'  DELIMITER '|' ENCODING 'UTF-8'