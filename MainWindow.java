package com.programs.java;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainWindow extends JFrame {

    final static String codeOfUser = "821275";
    public static RequestList requestList;
    public static JButton manage;

    CSVReader readerClass = new CSVReader();
    HashMap<String, HashMap<ArrayList<String>,Double>> reader;
    DisplayTable displayTable;

    public MainWindow() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        JButton edit = new JButton("Edit offerings");
        JButton display = new JButton("Display offerings");
        manage = new JButton("Manage purchases");



        edit.setSize(200,50);
        display.setSize(200,50);
        manage.setSize(200,50);
        add(edit);
        add(display);
        add(manage);

        double middle = getSize().getWidth()/2;
        double length = getSize().getHeight()/3;
        int xpos = (int)middle/10;


        JLabel info = new JLabel();
        ImageIcon editImg = new ImageIcon("edit.png");
        ImageIcon displayImg = new ImageIcon("display.png");


        info.setSize(1000,1000);
        add(info);
        info.setLocation((int) middle-300, -150);
        info.setVisible(false);


        edit.setLocation((int) xpos,0+40);
        display.setLocation((int) xpos, (int) length+40);
        manage.setLocation((int) xpos, (int) (2*length+40));

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JarWrite write = new JarWrite();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        edit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                info.removeAll();
//                jLabel.setIcon(editImg);
                info.setText("Use this button to edit the menu of the restaurant. It will open a text file in the default editor that you have available.");
                info.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            info.setVisible(false);
            }
        });


        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reader = readerClass.read();


                    String parameters = "?";
                    Object[] array = reader.keySet().toArray();

                    for (int i = 0; i < reader.size(); i++) {
                        parameters = parameters + (array[i].toString()) + "=" +
                                ((ArrayList) reader.get(array[i].toString()).keySet().toArray()[0]).toString() +
                                "&" + array[i].toString() + "=" + reader.get(array[i]).get(reader.get(array[i]).keySet().toArray()[0]).toString() + "&";
                    }
                    parameters = parameters.replaceAll("\\s", "");
                    parameters = parameters.replaceAll("\\.", "(");
                    parameters = parameters.replaceAll("\\[", "OPEN");
                    parameters = parameters.replaceAll("\\]", "CLOSE");


                    parameters = parameters.substring(0, parameters.length() - 1);
                    System.out.println(parameters);

                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    try {
                        desktop.browse(new URL("http://localhost:8080/tests/"+ MainWindow.codeOfUser +"/req_params.jsp" + parameters).toURI());


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                catch (Exception et)
                {
                    JOptionPane.showMessageDialog(getParent(), "File not found, please edit first.");
                }
                displayTable = new DisplayTable(reader);

            }
        });
        display.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                info.removeAll();
//                jLabel.setIcon(displayImg);
                info.setText("Use this button to display the menu that you have entered. This button will also update your menu on the web, so press it after you edit the menu.");

                info.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                info.setVisible(false);
            }
        });

        manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CSVReaderDown.refresh();
                    requestList = new RequestList();


                }
                catch (Exception et)
                {
                    et.printStackTrace();
                }
                }
        });
        manage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            info.removeAll();
            info.setText("Use this button to mange the orders of your customers. Once the order has been delivered and the money is received, press the resolve button for that order.");
            info.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                info.setVisible(false);

            }
        });

        setVisible(true);

    }
}