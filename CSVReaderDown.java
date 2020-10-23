package com.programs.java;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.programs.java.MainWindow.codeOfUser;


// A class that downloads all the orders that have been placed and saves them

public class CSVReaderDown {
    public static ArrayList<String> name;
    public static ArrayList<String> tableID;
    public static ArrayList<String> numberOfItems;
    public static ArrayList<String> stillNedded;

    public static void refresh()
    {
        name = new ArrayList<String>();
        tableID = new ArrayList<String>();
        numberOfItems = new ArrayList<String>();
        stillNedded = new ArrayList<String>();
        try {
            File requests = new File("requests.csv");
            if (!requests.exists()) {
                requests.createNewFile();
            }
            downloadFile("http://localhost:8080/tests/" + codeOfUser + "/requests" + codeOfUser + ".csv", requests);


            FileReader fileReader = new FileReader(requests);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String now;
            String[] temp;
            int i = 0;
            while((now = bufferedReader.readLine()) != null)
            {
//                System.out.println(now + i);
                temp = now.split(",");
                name.add(temp[0]);
                tableID.add(i, temp[1]);
                numberOfItems.add(temp[2]);
                stillNedded.add(temp[3]);
                System.out.print(stillNedded.get(i));
                i++;
            }

            System.out.println("finished");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int BUFFER_SIZE = 4096;

    //Function taken from 
    //  https://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
    public static void downloadFile(String fileURL, File saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveDir);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}
