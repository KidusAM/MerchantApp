package com.programs.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;


public class RequestList extends JFrame {

    public RequestList()
    {
        super("Requests");
        setVisible(true);
        getContentPane().removeAll();




        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);




        double widthMiddle = getSize().width/2;
        double heightMiddle = getSize().height/2;

        JLabel name1 = new JLabel("Item ordered");
        JLabel tableID1 = new JLabel("Table number");
        JLabel numberOfItems1 = new JLabel("Number requested");

        name1.setVisible(true);
        tableID1.setVisible(true);
        numberOfItems1.setVisible(true);

        add(name1);
        add(tableID1);
        add(numberOfItems1);
        name1.setLocation(0, 1);
        tableID1.setLocation(400, 1);
        numberOfItems1.setLocation(800, 1);
        name1.setSize(500, 100);
        tableID1.setSize(500,100);
        numberOfItems1.setSize(500,100);
        int j = 0;
        for (int i = 0; i < CSVReaderDown.name.size(); i++) {
            if (Integer.parseInt(CSVReaderDown.stillNedded.get(i))!=1)
            {

                final int k = j+1;
                add(new JLabel(CSVReaderDown.name.get(i)){{
                    setSize(500, 100);
                    setLocation(0, 1 + k*100);
                }});
                add(new JLabel(CSVReaderDown.tableID.get(i)){{
                    setSize(500, 100);
                    setLocation( 400, 1 + k*100);
                }});
                add(new JLabel(CSVReaderDown.numberOfItems.get(i)){{
                    setSize(500, 100);
                    setLocation(800, 1 + k*100);
                }});
                final String now = String.valueOf(i);
                final String stringToBeRemoved = CSVReaderDown.name.get(i) + "SPLIT" + CSVReaderDown.tableID.get(i) + "SPLIT" + CSVReaderDown.numberOfItems.get(i);
                add(new JButton("resolve"){{
                    setSize(150, 50);
                    setLocation(1200, 1 + k*100+30);
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            resolve(stringToBeRemoved);
                            try {
                               TimeUnit.SECONDS.sleep(3);
                                System.out.print(CSVReaderDown.stillNedded);
//                                TimeUnit.SECONDS.sleep(2);
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                            refresh(now);
                        }
                    });
                }});


                j++;
            }
        }
        setVisible(true);
        revalidate();
        repaint();


    }

    public void resolve(String s)
    {
        String[] requests = s.split("SPLIT");
        String param = "?name=" + requests[0] + "&tableid=" + requests[1] + "&numberofitems=" + requests[2];
        param = "http://localhost:8080/tests/" + MainWindow.codeOfUser + "/resolveOrder.jsp" + param;
        try {
            URL url = new URL(param);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void refresh(String s)
    {

        this.dispose();
        MainWindow.manage.doClick();

//        this.getContentPane().removeAll();
//        this.removeAll();
//        this.revalidate();
//        this.repaint();
//        this.dispose();
//        MainWindow.requestList.revalidate();
//        MainWindow.requestList.repaint();
        //        int now = Integer.parseInt(s);
//        stillNedded.set(now,"1");
//        MainWindow.requestList.setVisible(false);
//        MainWindow.requestList = null;
//        MainWindow.requestList = new RequestList();
    }

}
