package com.wyuu;

import co.aikar.idb.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    private final Database userDb;
    public DatabaseManager(String username, String password, String host, int port){
        String address = host+":"+port;
        DatabaseOptions userDb_options=DatabaseOptions.builder().mysql(username, password, "chat", address).build();
        userDb = PooledDatabaseOptions.builder().options(userDb_options).createHikariDatabase();
    }

    public Database GetUserDb (){
        return userDb;
    }

    public void createForm_TempPermission() {
        try {
            userDb.executeUpdate("CREATE TABLE IF NOT EXISTS TempPermission (" +
                    "uuid VARCHAR(255) PRIMARY KEY NOT NULL," +
                    "username VARCHAR(255) NOT NULL" +
                    ")");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addTempPermission(String uuid, String username){
        try{
            userDb.executeUpdate("INSERT IGNORE INTO TempPermission (uuid, username) VALUES (?, ?)",uuid,username);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTempPermission(String username){
        try{
            userDb.executeUpdate("DELETE FROM TempPermission WHERE username = ?",username);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTempUsers(){
        List<String> tempUsers=new ArrayList<>();
        try{
            tempUsers= userDb.getFirstColumnResults("SELECT username FROM TempPermission");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tempUsers;
    }


    public void closeDataBase(){
        if(userDb!= null){
            userDb.close();
        }
        DB.close();
    }



}
