<%@ page session="false"%>
<%@ page import="org.apache.sling.api.resource.*"%>
<%@ taglib prefix="sling"
    uri="http://sling.apache.org/taglibs/sling/1.0"%>
<sling:defineObjects />

<html>
<head>
<title>Hello</title>
</head>
<body>
    <p>Hello world.</p>

    <p>
        My path is
        <%=resource.getPath()%></p>

    <p>Now adding all components below jcr:content</p>
    <%
        if(resource.getChild("jcr:content") != null) {
            for(final Resource rsrc : resource.getChild("jcr:content").getChildren()) {
                %><sling:include path="<%= rsrc.getPath() %>" />
    <%
            }
        }
        %>
</body>
</html>