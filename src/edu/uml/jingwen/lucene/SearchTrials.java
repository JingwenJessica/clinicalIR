package edu.uml.jingwen.lucene;

import edu.uml.jingwen.database.MongoTrials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by jingwen on 9/21/16.
 */
public class SearchTrials {

    private static final Logger logger = LogManager.getLogger(MongoTrials.class);

    private Analyzer analyzer = new StandardAnalyzer();
    private Directory directory = new RAMDirectory();   // Store the index in memory
    //Directory directory = FSDirectory.open("/tmp/testindex"); // Store an index on disk


    public SearchTrials(Analyzer analyzer, Directory directory) {
        this.analyzer = analyzer;
        this.directory = directory;
    }


    public void searchById(String id){
        try {
            DirectoryReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);

            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser("NCT", analyzer);
            Query query = parser.parse(id);
            ScoreDoc[] hits = searcher.search(query, 1000).scoreDocs;
//                assertEquals(1, hits.length);
            // Iterate through the results:
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = searcher.doc(hits[i].doc);
                logger.info("search result: " + hitDoc.get("NCT"));
//                    assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            }
            reader.close();
            directory.close();

        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        } catch (ParseException e ){
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }
}
