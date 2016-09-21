/**
 * Created by jingwen on 9/14/16.
 */

import edu.uml.jingwen.database.MongoTrials;
import edu.uml.jingwen.lucene.IndexTrials;
import edu.uml.jingwen.lucene.SearchTrials;
import edu.uml.jingwen.property.Property;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


public class Main implements Runnable{

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Property properties = new Property("properties.properties");

    Analyzer analyzer = new StandardAnalyzer();
    Directory directory = new RAMDirectory();   // Store the index in memory
    //Directory directory = FSDirectory.open("/tmp/testindex"); // Store an index on disk

    IndexTrials indexTrials = new IndexTrials(analyzer, directory);

    SearchTrials searchTrials = new SearchTrials(analyzer, directory);



    public static void main(String[] args){

        properties.loadProperty();

        // start
        Main mainObj = new Main();
        mainObj.run();

    }

    public void run(){
        long trialsSize = 0;
        int pid = 1, num=2;
        while( true ){
            // load mongodb clinical data
            Map<String, String> trials = new HashMap();
            MongoTrials mongoTrials = new MongoTrials(properties, trials);
            mongoTrials.loadPage(pid, num);
            logger.info("[+] " + pid*num + " data ....");

            if( trials.size() == 0  ) break; // no more new data

            // index
            indexTrials.indexDocs( trials );

            // search
            searchTrials.searchById("");


        }

    }
}
