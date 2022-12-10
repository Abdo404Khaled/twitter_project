package com.twitterapp;

import java.io.IOException;
import java.util.concurrent.*;


import com.twitterapp.DataManager.DataManager;
import com.twitterapp.Models.Graph;

public class App 
{
    private static final String INPUTDATA = "twitter.csv";
    private static final String OUTPUTDATA = "twitter_accounts.csv";
    private static final String SEPARATOR = ",";
    private static DataManager data = new DataManager(INPUTDATA, SEPARATOR);

    private static Graph dataBase = new Graph();
    private static Thread loadData = new Thread(){
        public void run(){
            try {
                dataBase = data.LoadData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private static Thread writeData = new Thread(){
        public void run(){
                try {
                    data.setData(OUTPUTDATA);
                    data.writeData(dataBase.toString());
                    data.openFile();
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };
    private static Thread runApp = new Thread()
    {
        public void run(){
            try {
                System.out.println("Welcome to Twitter Application!");
                System.out.print("Loading Data.");
                loadData.start();
                while(loadData.isAlive()){
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(10);     
                }
                System.out.print('\n');
                System.out.print("Writing Data.");
                writeData.start();
                while(writeData.isAlive()){
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(10);
                }    
                System.out.print('\n');
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    };
    public static void main( String[] args )
    {
        runApp.start();
    }
}
