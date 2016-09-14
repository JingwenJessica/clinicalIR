package edu.uml.jingwen.database;

/**
 * Created by jingwen on 9/14/16.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

import java.util.List;
import com.mongodb.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import edu.uml.jingwen.property.Property;


public class MongoTrials extends MongoDB{
    private static final Logger logger = LogManager.getLogger(MongoTrials.class);

    private String collName;

    public MongoTrials(Property properties){
        super(properties.getString("mongo-ip"), properties.getInt("mongo-port"));
        setDatabaseName(properties.getString("database"));

        collName = properties.getString("nct-collName");
    }

    @Override
    public void load(){

    }


    @Override
    public void loadAll(){
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


    public void loadAllJSON(List<String> jsonList){
        BasicDBObject query = new BasicDBObject();
        BasicDBObject field = new BasicDBObject("id", 1);

        DBCursor c = mongoClient.getDB(getDatabaseName()).getCollection(collName).find(query,field);

        try {
            while (c.hasNext()) {
                BasicDBObject obj = (BasicDBObject) c.next();
                String json = obj.toString();

//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                gson.toJson(gson.fromJson(obj.toString(), Map.class));

                jsonList.add(json);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.info("MongoException [  " + e.getMessage()  +"]");
        }finally {
            logger.info("MongoInfo");
        }
    }

}
