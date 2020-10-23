package com.programs.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DisplayTable extends JFrame {

    private JTable menu_table;

    public DisplayTable(HashMap<String, HashMap<ArrayList<String>,Double>> map)
    {
        super("Offerings");
        Dimension dimension = new Dimension(
                Toolkit.getDefaultToolkit().getScreenSize().width-200,
                Toolkit.getDefaultToolkit().getScreenSize().height-200
                );
        setSize(dimension);
        setLayout(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Object[] set =  map.keySet().toArray(); // The set of keys to identify each menu item
        Object[] coulumnNames = new String[]{"Name of offering","Ingredients","Price"};
        Object[][] lists = new Object[map.size()][3];

        for (int i = 0; i < set.length; i++) {

            lists[i] = new String[]{
                (String) set[i], 
                map.get(set[i]).keySet().toArray()[0].toString(), 
                map.get(set[i]).get(
                        map.get(set[i]).keySet().toArray()[0]
                        ).toString()
            };
        }

        menu_table = new JTable(lists, coulumnNames);


        menu_table.setCellSelectionEnabled(false);
        menu_table.setSize(dimension);


        menu_table.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(menu_table);
        jScrollPane.setSize(dimension);


        add(jScrollPane);
        setVisible(true);


    }

}
