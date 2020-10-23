package com.programs.java;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Documents folder of user
import static com.programs.java.JarWrite.myDocuments;

// A function class that converts a csv file into a HashMap structure containing three entree as follows:
//    String -- name of the item on the menu
//    HashMap<ArrayList<String, Double> -- a hashmap of a list of ingredients and the price of the item
public class CSVReader
{
    FileReader fileReader;
    BufferedReader restaurant;
    public HashMap read()
    {
        try {
            if (JarWrite.exists) {
                fileReader = new FileReader(myDocuments + "/CafeOfferings/offerings" + MainWindow.codeOfUser +".csv");
                restaurant = new BufferedReader(fileReader);
            }
            else
                JOptionPane.showMessageDialog(null,"File does not exist. Click on Edit first");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            restaurant.readLine();
            ArrayList<String> menuItem = null;

            HashMap<String, HashMap<ArrayList<String>,Double>> file = new HashMap<>();
            String line = null;
            
            while((line = restaurant.readLine()) != null)
            {
                menuItem = new ArrayList<>( Arrays.asList(line.split(",")));
                ArrayList<String> finalMenuItem = menuItem;

                file.put(menuItem.get(0),
                        new HashMap<ArrayList<String>, Double>() {{ put((
                           new ArrayList<String>(
                                   Arrays.asList(finalMenuItem.get(1).split(";")))),
                           Double.parseDouble(finalArnow.get(2)));
                    }
                    });

            }
            restaurant.close();
            fileReader.close();

            return file;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }
}
