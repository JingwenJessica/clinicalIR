package edu.uml.jingwen.database;

/**
 * Created by jingwen on 9/14/16.
 */
public interface Database {

    /**
     * connect mongo
     */
    public  void connect();


    public  void load();


    /**
     * close connection
     */
    public  void close();



}
