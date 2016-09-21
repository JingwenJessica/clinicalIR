package edu.uml.jingwen.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;

/**
 * Created by jingwen on 9/21/16.
 */
public class IndexTrials {

    private Analyzer analyzer = new StandardAnalyzer();
    private Directory directory = new RAMDirectory();   // Store the index in memory
    //Directory directory = FSDirectory.open("/tmp/testindex"); // Store an index on disk

    public IndexTrials(Analyzer analyzer, Directory directory) {
        this.analyzer = analyzer;
        this.directory = directory;
    }

    public void indexDocs(Map<String, String> trials) {
        for(Map.Entry<String, String> trial : trials.entrySet()){
            indexDoc( trial.getKey(), trial.getValue() );
        }
    }

    public void indexDoc(String id, String trialJSON){
        try {
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            Document doc = new Document();
            doc.add(new Field(id, trialJSON, TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            iwriter.close();

        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }
}
