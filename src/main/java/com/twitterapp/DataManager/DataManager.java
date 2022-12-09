package com.twitterapp.DataManager;

import java.io.*;
import java.util.*;
import com.opencsv.*;

import com.twitterapp.Models.Graph;
import com.twitterapp.Models.User;

import java.awt.Desktop;


public class DataManager {
    private String FILE;
    private char SEPARATOR;

    FileReader filereader;
    FileWriter fileWriter;
    File file;

    Desktop desktop = Desktop.getDesktop();
    
    CSVParser parser;
    CSVReader reader;

    Graph dataBase;

    List<String[]> allData; 

    public DataManager(String FILE, char SEPARATOR){
        this.FILE = FILE;
        this.SEPARATOR = SEPARATOR;
        allData = new ArrayList<>();
    }

    public void setData(String FILE){
        this.FILE = FILE;
    }

    public void LoadData(){
        
        try{
            filereader = new FileReader(FILE);
            parser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
            reader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
            allData = reader.readAll();
            filereader.close();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return;
        }
    }

    public Graph ProcessData(){
        
        dataBase = new Graph();

        for(String[] row: allData){
            User user1 = new User(Integer.valueOf(row[0]));
            User user2 = new User(Integer.valueOf(row[1]));
            dataBase.follow(user1, user2);
        } 
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
