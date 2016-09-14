package edu.uml.jingwen.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jingwen on 6/28/16.
 */
public class Property {

    protected Properties Properties = new Properties();

    protected String filePath = "";

    public Property(String path ){
        filePath = path;
    }

    public void loadProperty(){
        try {
            FileInputStream filepath = new FileInputStream(filePath);
            Properties.load(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * set the property file path
     * @param path
     */
    public void setFilePath(String path){
        filePath = path;
    }

    /**
     * get file path
     * @return path of the property
     */
    public String getFilePath(){
        return filePath;
    }


    /**
     * get value of given key
     * @param key
     * @return
     */
    public String getProperty(String key){
        return Properties.getProperty(key);
    }



    /**
     * get string value of key
     * @param  key
     * @return String
     */
    public String getString(String key) {
        return Properties.getProperty(key);
    }



    /**
     * get int value of key
     * @param key
     * @return int
     */
    public int getInt(String key) {
        return Integer.valueOf( Properties.getProperty(key) );
    }


    /**
     * get long value of key
     * @param key
     * @return int
     */
    public long getLong(String key){
        return Long.parseLong(Properties.getProperty(key));
    }


    /**
     * get double value of key
     * @param key
     * @return
     */
    public double getDouble(String key){
        return Double.parseDouble(Properties.getProperty(key));
    }


    /**
     * get boolean value of key
     * @param key
     * @return
     */
    public boolean getBoolean(String key){
        return Boolean.parseBoolean(Properties.getProperty(key));
    }




    /**
     * save properties in form of key = value into properties file
     * @param key
     * @param value
     */
    public void putIntoProperties(String key, String value){
        Properties.put(key, value);
    }



}
