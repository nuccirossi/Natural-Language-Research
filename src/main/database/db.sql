-- Here we need to insert all SQL files to generate the entire structure of the database and to populate it





DROP TABLE IF EXISTS Sentences;

CREATE TABLE Sentences (id SERIAL, internal_id VARCHAR(35), sentence VARCHAR(1000), PRIMARY KEY (id));


\copy Sentences(internal_id, sentence) FROM '/Users/fbbfnc/Downloads/corpora/VUA/FinalResults/AwkParsingFinalResult.txt' DELIMITER '|' ENCODING 'latin1'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/MOH-X/FinalResults/MOHAWK.txt'  DELIMITER '|' ENCODING 'UTF-8'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/FLA/FinalResults/1/Affective_with_metaphors_pos.txt'  DELIMITER '|' ENCODING 'UTF-8'

\copy Sentences(internal_id, sentence) FROM '~/Downloads/corpora/FLA/FinalResults/2/Affective_with_metaphors_neg.txt'  DELIMITER '|' ENCODING 'UTF-8'


DROP TABLE IF EXISTS Surveys;



CREATE TABLE SURVEYS (id SERIAL, corpora VARCHAR(3), translator VARCHAR(8), languages VARCHAR(15), num INTEGER, sent1 INTEGER, sent2 INTEGER, sent3 INTEGER, sent4 INTEGER, sent5 INTEGER, sent6 INTEGER, sent7 INTEGER, sent8 INTEGER, sent9 INTEGER, sent10 INTEGER, sent11 INTEGER, sent12 INTEGER, sent13 INTEGER, sent14 INTEGER, sent15 INTEGER, PRIMARY KEY (id), FOREIGN KEY (sent1) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,
 FOREIGN KEY (sent2) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent3) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent4) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent5) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent6) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent7) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent8) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent9) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent10) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent11) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent12) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE, FOREIGN KEY (sent13) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent14) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE,  FOREIGN KEY (sent15) REFERENCES Sentences.id ON DELETE RESTRICT ON UPDATE CASCADE);