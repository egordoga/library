<%@ page import="java.util.ArrayList" %>
<%@ page import="data.Book" %>
<%@ page import="enums.SearchType" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<jsp:useBean id="bookList" class="data.BookList" scope="page"/>


<div class="osnova">
    <%@include file="../WEB-INF/jspf/main_menu.jspf" %>
    <%@include file="../WEB-INF/jspf/letters.jspf" %>

    <%
        ArrayList<Book> list = null;

        if (request.getParameter("genre_id") != null) {
            int genreId = Integer.valueOf(request.getParameter("genre_id"));
            list = bookList.getBooksByGenre(genreId);
        } else if (request.getParameter("letter") != null) {
            String letter = request.getParameter("letter");
            list = bookList.getBooksByLetter(letter);
        } else if (request.getParameter("search_string") != null) {
            String searchStr = request.getParameter("search_string");
            SearchType type = SearchType.TITLE;
            if (request.getParameter("search_option").equals("Автор")) {
                type = SearchType.AUTHOR;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {
                list = bookList.getBooksBySearch(searchStr, type);
            }
        } else if (session.getAttribute("currentBookList") != null) {
            list = (ArrayList<Book>) session.getAttribute("currentBookList");
        } else {
            list = bookList.getAllBooks();
        }
        assert list != null;%>

    <h5 style="text-align: left; margin-top: 20px; margin-left: 10px;">Найдено книг: <%=list.size()%>
    </h5>
    <div class="book_list">
        <%
            session.setAttribute("currentBookList", list);
            for (Book book : list) {
        %>

        <div class="book_info">
            <div class="book_title">
                <p><a href="content.jsp?index=<%=list.indexOf(book)%>"><%=book.getName() %>
                </a></p>
            </div>

            <div class="book_image">
                <a href="content.jsp?index=<%=list.indexOf(book)%>"><img
                        src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book)%>" height="250"
                        width="180" alt="Обложка"/></a>
            </div>

            <div class="book_details">
                <br><strong>ISBN:</strong><%=book.getIsbn()%>
                <br><strong>Издательство:</strong><%=book.getPublisher()%>
                <br><strong>Количество страниц:</strong><%=book.getPageCount()%>
                <br><strong>Год издания:</strong><%=book.getPublishYear()%>
                <br><strong>Автор:</strong><%=book.getAuthor()%>
                <p style="margin: 10px"><a style="text-align: center; color: darkgrey"
                                           href="content.jsp?index=<%=list.indexOf(book)%>">Читать</a></p>
            </div>
        </div>
        <%}%>
    </div>
</div>