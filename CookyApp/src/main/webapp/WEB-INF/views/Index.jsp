<h1>Herzlich Willkommen auf der Cooky Webseite!</h1>
<%= session.getServletContext().getMajorVersion() %>
<%= session.getServletContext().getMinorVersion() %>

JSP Version :
<%= JspFactory.getDefaultFactory().getEngineInfo().
        getSpecificationVersion()%>

Server Version : <%= application.getServerInfo()%>
