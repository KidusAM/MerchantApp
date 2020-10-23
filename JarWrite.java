package com.programs.java;


import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

public class JarWrite {
    static String myDocuments = myDocuments();
    static boolean exists = true;
    public JarWrite()
    {
        try {
            if (!(new File(myDocuments + "/CafeOfferings").exists()))
            {
                new File(myDocuments + "/CafeOfferings").mkdirs();

            }
            File newFile = new File(myDocuments+"/CafeOfferings/offerings" + MainWindow.codeOfUser +".csv");
            exists = newFile.exists();

            if(!(exists))
            {

                PrintWriter writer = new PrintWriter(myDocuments + "/CafeOfferings/offerings" + MainWindow.codeOfUser + ".csv", "UTF-8");
                writer.println("Name of Offering,Ingredients (Separated by semicolons),Price");
                writer.close();
                Desktop.getDesktop().open(newFile);
            }

            else
            {
                Desktop.getDesktop().open(newFile);
            }
            exists = true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Gets the location of My Documents folder on Windows
    public static String myDocuments()
    {
        String myDocuments = null;
        try {
            Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            InputStream in = p.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            myDocuments = new String(b);
            myDocuments = myDocuments.split("\\s\\s+")[4];

        } catch(Throwable t) {
            t.printStackTrace();
        }

        return myDocuments;
    }

}
