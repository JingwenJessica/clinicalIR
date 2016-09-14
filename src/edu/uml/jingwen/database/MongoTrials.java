package edu.uml.jingwen.database;

/**
 * Created by jingwen on 9/14/16.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import edu.uml.jingwen.property.Property;


public class MongoTrials extends MongoDB{
    private static final Logger logger = LogManager.getLogger(MongoTrials.class);

    private String collName;

    private List<String> tiralsJson;

    public MongoTrials(Property properties, List<String> tiralsJson){
        super(properties.getString("mongo-ip"), properties.getInt("mongo-port"));
        setDatabaseName(properties.getString("database"));

        collName = properties.getString("nct-collName");

        this.tiralsJson = tiralsJson;

    }

    @Override
    public void load(){
        BasicDBObject query = new BasicDBObject();
        BasicDBObject field = new BasicDBObject("id", 1);

        DBCursor c = mongoClient.getDB(getDatabaseName()).getCollection(collName).find(query,field);

        try {
            while (c.hasNext()) {
                BasicDBObject obj = (BasicDBObject) c.next();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.info("MongoException [  " + e.getMessage()  +"]");
        }finally {
            logger.info("MongoInfo");
        }
    }


    public void loadAllJSON(){
        BasicDBObject query = new BasicDBObject();
        BasicDBObject field = new BasicDBObject("id", 1);

        DBCursor c = mongoClient.getDB(getDatabaseName()).getCollection(collName).find(query,field);

        try {
            while (c.hasNext()) {
                BasicDBObject obj = (BasicDBObject) c.next();
                String json = obj.toString();

                tiralsJson.add(json);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.info("MongoException [  " + e.getMessage()  +"]");
        }finally {
            logger.info("MongoInfo");
        }
    }

}
