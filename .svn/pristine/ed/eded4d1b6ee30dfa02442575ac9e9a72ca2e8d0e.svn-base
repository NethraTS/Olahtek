
<%@ page import="java.io.*" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.json.JSONObject" %>

<html>
<head>
 <style>
    * { margin:0 padding:0 }
   body { margin:0; padding:0; text-align:center }  
   #hold_my_iframe { padding:0px; margin:0 auto; width: 100%; height: 100%  }
   </style>


</head>
<body>

 <%
 String urlhost;
	
	Properties serviceProp = new Properties();
			String home = System.getProperty("user.home");
			String propFile = "application.properties";
			if (new File(home + "/." + propFile).exists()) {
				FileInputStream inputStream = new FileInputStream(new File(home
						+ "/." + propFile));
				serviceProp.load(inputStream);
				System.out.println("Application is reading configuration for Register Handler from "
								+ home);
			} else {
				System.out.println("Application is reading configuration from resources for Register Handler");
			}
			
		 urlhost = (String) serviceProp.get("olahHost");
			
			System.out.println("urlhost:" + urlhost);
%>


<iframe
  src="home.jsp"
  frameborder="0"
  width="100%"
  height="100%"
  scrolling="yes" marginwidth=0 marginheight=0 frameborder=0 style="overflow:hidden;">

</iframe>
</body>
</html>