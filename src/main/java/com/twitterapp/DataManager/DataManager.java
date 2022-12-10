package com.twitterapp.DataManager;

import java.io.*;
import java.util.*;

import com.twitterapp.Models.Graph;
import com.twitterapp.Models.User;

import java.awt.Desktop;


public class DataManager {
    private String FILE;
    private String SEPARATOR;

    FileReader filereader;
    BufferedReader bufferedreader;
    FileWriter fileWriter;
    File file;

    String line = "";
    String[] tempArr;

    Desktop desktop = Desktop.getDesktop();

    Graph dataBase = new Graph();;

    List<String[]> allData; 

    public DataManager(String FILE, String SEPARATOR){
        this.FILE = FILE;
        this.SEPARATOR = SEPARATOR;
        allData = new ArrayList<>();
    }

    public void setData(String FILE){
        this.FILE = FILE;
    }

    public Graph LoadData() throws IOException{
            file = new File(FILE);
            filereader = new FileReader(file);
            bufferedreader = new BufferedReader(filereader);
              while((line = bufferedreader.readLine()) != null) {
                tempArr = line.split(SEPARATOR);
                User user1 = new User(Integer.valueOf(tempArr[0]));
                User user2 = new User(Integer.valueOf(tempArr[1]));
                dataBase.follow(user1, user2);
              }
            bufferedreader.close();
            dataBase.processDB();
            return dataBase;
    }

    public void writeData(String data) throws IOException{
        try{
            fileWriter = new FileWriter(FILE);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return;
        }

    }

    public void openFile(){
        try {
            file = new File(FILE);
            desktop.open(file);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
            
    }
}
