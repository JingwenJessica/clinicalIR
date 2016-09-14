package edu.uml.jingwen.database;


import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by jingwen on 9/14/16.
 */
public abstract class MongoDB implements Database{

    private static final Logger logger = LogManager.getLogger(MongoDB.class);

    private String ip;
    private int port;
    private String dbName;

    MongoClient mongoClient;

    MongoDB(String ip, int port) {
        this.ip = ip;
        this.port = port;

        connect();
    }

    @Override
    public void connect() {
        try {
            mongoClient = new MongoClient(ip, port);
        } catch (Exception e) {
            logger.warn("MongoException [" + e.getMessage() + "]");
        }
    }

    @Override
    public void close() {
        mongoClient.close();
    }


    // set
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setDatabaseName(String db) {
        this.dbName = db;
    }
    // get
    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }
    public String getDatabaseName() {
        return dbName;
    }

}
