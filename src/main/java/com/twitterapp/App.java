package com.twitterapp;

import java.util.concurrent.*;


import com.twitterapp.DataManager.DataManager;
import com.twitterapp.Models.Graph;

public class App 
{
    private static final String INPUTDATA = "twitter.csv";
    private static final String OUTPUTDATA = "twitter_accounts.csv";
    private static final char SEPARATOR = ',';
    private static DataManager data = new DataManager(INPUTDATA, SEPARATOR);

    private static Graph dataBase = new Graph();
    private static Thread loadData = new Thread(){
        public void run(){
            data.LoadData();
            dataBase = data.ProcessData();
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
    public static void main( String[] args ) throws InterruptedException
    {
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
    }
}
