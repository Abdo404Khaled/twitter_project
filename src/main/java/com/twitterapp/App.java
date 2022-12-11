package com.twitterapp;

import java.io.*;
import java.util.*;

import com.twitterapp.DataManager.DataManager;
import com.twitterapp.Models.Graph;

public class App 
{
    private static String INPUTDATA = "";
    private static String SEPARATOR = "";
    private static String OUTPUTDATA = "";

    private static Scanner scanner = new Scanner(System.in);

    private static DataManager data;

    private static Graph dataBase = new Graph();
    private static void loadData() throws IOException{
        dataBase = data.LoadData();
    }
    private static void writeData() throws IOException{
        data.setData(OUTPUTDATA);
        data.writeData(dataBase.toString());
        data.openFile();
    }

    public static void main( String[] args ) throws IOException, InterruptedException
    {
            System.out.println("Welcome to Twitter Application!");
        do{
            System.out.print("Please Specify file name without .csv (ex: accounts): ");
            INPUTDATA = scanner.nextLine();
            System.out.print("Please Specify file sepearator (ex: , ; -): ");
            SEPARATOR = scanner.nextLine();
            System.out.print("Loading Data.");
            data = new DataManager(INPUTDATA.concat(".csv"), SEPARATOR);
            OUTPUTDATA = INPUTDATA.concat("_accounts.csv");
            loadData();
            System.out.print("\nWriting Data.");
            writeData();
            System.out.println();
        }while(true);
    }
}
