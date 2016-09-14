/**
 * Created by jingwen on 9/14/16.
 */

import edu.uml.jingwen.database.MongoTrials;
import edu.uml.jingwen.property.Property;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class Main implements Runnable{

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Property properties = new Property("properties.properties");

    public static void main(String[] args){

        properties.loadProperty();

        // start
        Main mainObj = new Main();
        mainObj.run();

    }

    public void run(){

        // load mongodb clinical data
        List<String> tiralsJson = new ArrayList();
        MongoTrials mongoTrials = new MongoTrials(properties, tiralsJson);
        mongoTrials.loadAll();
    }
}
