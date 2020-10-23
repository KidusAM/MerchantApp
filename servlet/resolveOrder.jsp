<%@page language="java" contentType="text/html"%>
<%@page import="java.util.*, java.io.*"%>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%
    String code = "821275";
    try {
        File requests = new File("../Tomcat 7.0/webapps/ROOT/tests/821275/requests" + code + ".csv");
//        FileWriter fileWriter = new FileWriter(requests, true);
        FileReader fileReader = new FileReader(requests);
        BufferedReader reader = new BufferedReader(fileReader);
        String name = (String) request.getParameter("name");
        String tableID = (String) request.getParameter("tableid");
        String numberOfItems = (String) request.getParameter("numberofitems");

        int i = 0;
        String now = null;
        while((now = reader.readLine()) != null)
        {
            if (now.equals(name + "," + tableID + "," + numberOfItems + "," + 0))
            {
                ArrayList<String> lines = (ArrayList) Files.readAllLines(requests.toPath(), StandardCharsets.UTF_8);
                lines.set(i, name + "," + tableID + "," + numberOfItems + "," + 1);
                Files.write(requests.toPath(),lines, StandardCharsets.UTF_8);
            }
            i++;

        }

    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

%>
</body></html>
