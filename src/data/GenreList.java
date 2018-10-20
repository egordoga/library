package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Master on 25.08.2017.
 */
public class GenreList {

    public ArrayList<Genre> getGenreList() {
        ArrayList<Genre> genreList = new ArrayList<>();

        Connection conn = Database.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM library.genre");

            while (res.next()) {
                Genre genre = new Genre();
                genre.setId(res.getInt("id"));
                genre.setName(res.getString("name"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreList;
    }
}
