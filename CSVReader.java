package com.programs.java;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.programs.java.JarWrite.myDocuments;

public class CSVReader
{
    FileReader fileReader;
    BufferedReader restaurant;
    public HashMap read()
    {
        try {
            if (JarWrite.exists) {
                fileReader = new FileReader(myDocuments+"/CafeOfferings/offerings" + MainWindow.codeOfUser +".csv");
                restaurant = new BufferedReader(fileReader);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"File does not exist. Click on Edit first");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            restaurant.readLine();
            ArrayList<String> arnow = null;
            HashMap<String, HashMap<ArrayList<String>,Double>> file = new HashMap<>();
            String now = null;
            while((now = restaurant.readLine()) != null)
            {
                arnow = new ArrayList<>( Arrays.asList(now.split(",")));
                ArrayList<String> finalArnow = arnow;
                file.put(arnow.get(0), new HashMap<ArrayList<String>, Double>()
                {{

                    put((new ArrayList<String>(Arrays.asList(finalArnow.get(1).split(";")))),Double.parseDouble(finalArnow.get(2)));
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