<%@ page session="false"%>
<%@page import="adobe.ags.datalayer.core.HelloService"%>
<%@ taglib prefix="sling"
	uri="http://sling.apache.org/taglibs/sling/1.0"%>
<sling:defineObjects />

<%
HelloService helloService = sling.getService(adobe.ags.datalayer.core.HelloService.class);
%>

<div>component 2 uses the HelloService OSGI Service:</div>
<div>
	HelloService says:
	<%=helloService.getMessage()%></div>