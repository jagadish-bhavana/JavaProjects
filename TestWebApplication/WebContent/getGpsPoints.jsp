<%@page import="com.ampolt.ascada.database.Database"%>
<%@page import="java.sql.*"%>

<%
	Database database = Database.getInstance();
	ResultSet rs = null;
	String sql = "SELECT latitude, longitude FROM ASCADA.node limit 0,12";
	rs = database.executeQuery(sql);
	String xml = "<?xml version=1.0 encoding=UTF-8?>" + "<markers>"+ "<marker>" ;
	while (rs.next()) {
		//Write out XML using values returned by the query
		xml = xml + "<lat>" + rs.getString("latitude") + "</lat>" 
				+ "<lng>" + rs.getString("longitude") + "</lng>";
	}
	xml = xml + "</marker>" + "<markers>";
	out.print(xml);
%>

