<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 07.09.2017
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<body>

<div style="margin: 30px">
    <a href="books.jsp"><
        <вернуться
    </a>
</div>

<div class="pdf_viewer">


    <embed src="<%=request.getContextPath()%>/PdfContent?index=<%=request.getParameter("index")%>&session_id=<%=request.getSession().getId()%>"
           width="900" height="700"/>

</div>
</body>
