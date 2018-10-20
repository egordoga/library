package data;

import enums.SearchType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookList {

    private ArrayList<Book> bookList = new ArrayList<>();


    private ArrayList<Book> getBooks(String str) {
        Statement stmn = null;
        ResultSet res = null;
        Connection conn = null;

        try {
            conn = Database.getConnection();
            assert conn != null;
            stmn = conn.createStatement();
            res = stmn.executeQuery(str);

            while (res.next()) {
                Book book = new Book();
                book.setId(res.getInt("id"));
                book.setName(res.getString("name"));
                book.setGenre(res.getString("genre"));
                book.setIsbn(res.getString("isbn"));
                book.setAuthor(res.getString("author"));
                book.setPageCount(res.getInt("page_count"));
                book.setPublishYear(res.getInt("publish_year"));
                book.setPublisher(res.getString("publisher"));
                book.setImage(res.getBytes("image"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stmn != null) {
                    stmn.close();
                }
                if (res != null) {
                    res.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }


    public ArrayList<Book> getBooksByGenre(int genreId) {
        return getBooks("select b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name as publisher, " +
                "a.fio as author, g.name as genre, b.image from library.book b " +
                "inner join library.author a on b.author_id=a.id " +
                "inner join library.genre g on b.genre_id=g.id " +
                "inner join library.publisher p on b.publisher_id=p.id " +
                "where genre_id=" + genreId + " order by b.name limit 0,6");
    }

    public ArrayList<Book> getBooksByLetter(String letter) {
        return getBooks("select b.id, b.name, b.isbn, b.publish_year, b.page_count, " +
                "g.name as genre, a.fio as author, p.name as publisher, b.image from library.book b " +
                "inner join library.genre g on b.genre_id=g.id " +
                "inner join library.author a on b.author_id=a.id " +
                "inner join library.publisher p on b.publisher_id=p.id " +
                "where substr(b.name,1,1)='" + letter + "' order by name limit 0,6");
    }

    public ArrayList<Book> getBooksBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.page_count, b.isbn, " +
                "b.publish_year, g.name as genre, a.fio as author, p.name as publisher, b.image from library.book b " +
                "inner join library.genre g on b.genre_id=g.id " +
                "inner join library.author a on b.author_id=a.id " +
                "inner join library.publisher p on b.publisher_id=p.id ");

        if (type == SearchType.AUTHOR) {
            sql.append("where lower(a.fio) like'%" + searchStr.toLowerCase() + "%' order by b.name ");
        } else if (type == SearchType.TITLE) {
            sql.append("where lower(b.name) like '%").append(searchStr.toLowerCase()).append("%' order by name ");
        }
        sql.append("limit 0,6");
        return getBooks(sql.toString());
    }

    public ArrayList<Book> getAllBooks() {
        return getBooks("select b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name as publisher, " +
                "a.fio as author, g.name as genre, b.image from library.book b " +
                "inner join library.author a on b.author_id=a.id " +
                "inner join library.genre g on b.genre_id=g.id " +
                "inner join library.publisher p on b.publisher_id=p.id " +
                " order by b.name limit 0,6");
    }
}
