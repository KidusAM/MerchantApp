<%@page language="java" contentType="text/html"%>
<%@page import="java.util.*, java.io.*"%>
<%@ page import="java.lang.reflect.Array" %>
<%
    final String error = null;
    String code = "821275";
  Map      map = request.getParameterMap();
  Object[] keys = map.keySet().toArray();
  Map<String, HashMap<ArrayList<String>,Double>> map2 = new HashMap<String, HashMap<ArrayList<String>,Double>>();
  ArrayList<String> arlist;
    for (int i = 0; i < map.size() ; i++) {
        map2.put((String) keys[i], getHashMap(new ArrayList<String>(Arrays.asList(
                ((String[]) map.get(keys[i]))[0].toString().replaceAll("OPEN", "").replaceAll("CLOSE", "").split(",")
                ))
                , new Double(Double.parseDouble(((String[])map.get(keys[i]))[1].replaceAll("\\(", "\\.")))));
       }
  %>
<%

    try {
        File strFile = new File("../Tomcat 7.0/webapps/ROOT/tests/821275/offerings" + code + ".csv");
        File strFile2 = new File("../Tomcat 7.0/webapps/ROOT/tests/821275/offerings" + code + ".ser");
        if (!strFile.exists())
        {
            boolean bool = strFile.createNewFile();
            System.out.println(strFile.getCanonicalPath());
        }
        if (!strFile2.exists())
        {
            boolean bool = strFile2.createNewFile();
            System.out.println(strFile2.getCanonicalPath());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(strFile2));
        objectOutputStream.writeObject(map2);
        objectOutputStream.close();
        writeToFile(map2, strFile);
        out.println("done");
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
%>
<html><head><title>Request Parameters</title></head><body>
  <table border="1">
    <tr>
        <th>Name of offering</th>
        <th>Ingredients</th>
        <th>Price</th>
    </tr>
<%

    for (int i = 0; i < map2.size(); i++) {
        out.println("<tr>");

            out.println("<td>");
                out.println(keys[i] );
            out.println("</td>");
            out.println("<td>");
                out.println(map2.get(keys[i]).keySet().toArray()[0]);
            out.println("</td>");
            out.println("<td>");
                out.println(map2.get(keys[i]).get(map2.get(keys[i]).keySet().toArray()[0]));
            out.println("</td>");
            //Lines 28 through 39, genius piece of code, iterate over the HashMap that was produced earlier on to produce a tablular data set

        out.println("</tr>");
    }

  %>
    </table>
  <%!

      public void writeToFile(Map<String, HashMap<ArrayList<String>, Double>> map, File file)
      {
          Object[] keys = map.keySet().toArray();
          Object[] keys2;

          try {
              PrintWriter writer = new PrintWriter(file);
              writer.print("Name of Offering,Ingredients (Separated by commas),Price");
              for (int i = 0; i < map.size(); i++) {
                  writer.println();
                  writer.print(keys[i] + ",");
                  keys2 = map.get(keys[i]).keySet().toArray();
                  for (int j = 0; j < ((ArrayList<String>)map.get(keys[i]).keySet().toArray()[i]).size(); j++) {
                      if (!(j == ((ArrayList<String>)map.get(keys[i]).keySet().toArray()[i]).size() - 1)) {
                          writer.print(" " + ((ArrayList<String>) map.get(keys[i]).keySet().toArray()[i]).get(j) + ";");
                      }
                      else
                      {

                          writer.print(" " + ((ArrayList<String>) map.get(keys[i]).keySet().toArray()[i]).get(j));

                      }

                  }

                  writer.print("," + map.get(keys[i]).get(keys2[i]));
              }
              writer.close();

          }
          catch (Exception e)
          {
          }

      }

      public HashMap getHashMap(ArrayList ingredients, Double price)
      {
          Map<ArrayList<String>, Double> map = new HashMap<ArrayList<String>, Double>();
          map.put(ingredients, price);
          return (HashMap) map;
      }
      public int[] instancesOf(String s, char i){
          int[] intarray= new int[4];
          int counter = 0;
          for (int j = 0; j < s.length() ; j++) {
              if (s.charAt(j) == i)
              {
                  intarray[i] = j;
                  counter++;
              }
          }
          return intarray;

      }

  %>
</body></html>
