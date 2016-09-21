package edu.uml.jingwen.database;

/**
 * Created by jingwen on 9/14/16.
 */

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.uml.jingwen.property.Property;
import org.bson.Document;


public class MongoTrials extends MongoDB{
    private static final Logger logger = LogManager.getLogger(MongoTrials.class);

    private String collName;
    private MongoCollection mongoCollection;
    private List<String> trialsJson;


    public MongoTrials(Property properties, List<String> trialsJson){
        super(properties.getString("mongo-ip"), properties.getInt("mongo-port"));
        setDatabaseName(properties.getString("database"));

        collName = properties.getString("nct-collName");
        this.mongoCollection = mongoClient.getDatabase(getDatabaseName()).getCollection(collName);

        this.trialsJson = trialsJson;
    }

    @Override
    public void load(){

    }

    public void loadPage(int pid, int num){

        MongoCursor<Document> cursor = mongoCollection
                .find(exists("id"))
                .sort(orderBy(descending("_id")))
                .projection(exclude("_id"))
                .iterator();

        try {
            while (cursor.hasNext()) {
                String json = cursor.next().toJson();
                trialsJson.add(json);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            logger.info("MongoException [  " + e.getMessage()  +"]");
        } finally {
            cursor.close();
        }




    }

}
