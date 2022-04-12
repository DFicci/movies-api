package data;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {
    private Connection connection;

    public MySqlMoviesDao() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + Config.DB_HOST + ":3306/daniel?allowPublicKeyRetrieval=true&useSSL=false",
                    Config.DB_USER,
                    Config.DB_PW);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM movies");

        List<Movie> movies = new ArrayList<>();

        while (rs.next()) {
            movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("rating"),
                    rs.getString("poster"),
                    rs.getString("year"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getString("plot"),
                    rs.getString("actors")

            ));
        }

        return movies;
    }

    @Override
    public Movie findOne(int id) {
        Movie findMovie = null;

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = null;
            rs = statement.executeQuery("SELECT * FROM  movies WHERE  id = " + id);
            rs.next();

            findMovie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("rating"),
                    rs.getString("poster"),
                    rs.getString("year"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getString("plot"),
                    rs.getString("actors")
            );
        } catch (SQLException e){
            e.printStackTrace();
        }
        return findMovie;
    }

    @Override
    public void insert(Movie movie) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO movies" +
                    "(title, year, director, actors, rating, poster, genre, plot) ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "title, rating, poster, year, genre, director, plot, actors) " +
                "VALUES");

        sql.append("(?, ?, ?, ?, ?, ?, ?, ?), ".repeat(movies.length));

        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        PreparedStatement statement = connection.prepareStatement(sql.toString());

        int counter = 0;
        for (Movie movie : movies) {
            statement.setString((counter * 8) + 1, movie.getTitle());
            statement.setString((counter * 8) + 2, movie.getRating());
            statement.setString((counter * 8) + 3, movie.getPoster());
            statement.setString((counter * 8) + 4, movie.getYear());
            statement.setString((counter * 8) + 5, movie.getGenre());
            statement.setString((counter * 8) + 6, movie.getDirector());
            statement.setString((counter * 8) + 7, movie.getPlot());
            statement.setString((counter * 8) + 8, movie.getActors());
            counter++;
        }
        statement.executeUpdate();
    }


    @Override
    public void update(Movie movie) throws SQLException {
        Movie changeMovie = findOne(movie.getId());

        if (movie.getTitle() != null) {
            changeMovie.setTitle(movie.getTitle());
        }
        if (movie.getYear() != null) {
            changeMovie.setTitle(movie.getYear());
        }
        if (movie.getPoster() != null) {
            changeMovie.setTitle(movie.getPoster());
        }
        if (movie.getRating() != null) {
            changeMovie.setRating(movie.getRating());
        }
        if (movie.getGenre() != null) {
            changeMovie.setGenre(movie.getGenre());
        }
        if (movie.getDirector() != null) {
            changeMovie.setDirector(movie.getDirector());
        }
        if (movie.getActors() != null) {
            changeMovie.setActors(movie.getActors());
        }
        if (movie.getPlot() != null) {
            changeMovie.setPlot(movie.getPlot());
        }

        StringBuilder sql = new StringBuilder("UPDATE movies SET title =?, rating = ?, poster = ?, year = ?, genre = ?, director = ?, plot = ? WHERE id = ? ");

        PreparedStatement statement = connection.prepareStatement((sql.toString()));
        statement.setString(1, changeMovie.getTitle());
        statement.setString(2, changeMovie.getRating());
        statement.setString(3, changeMovie.getPoster());
        statement.setString(4, changeMovie.getYear());
        statement.setString(5, changeMovie.getGenre());
        statement.setString(6, changeMovie.getDirector());
        statement.setString(7, changeMovie.getActors());
        statement.setString(8, changeMovie.getPlot());
        statement.setInt(9, changeMovie.getId());

        statement.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql =
                "DELETE FROM movies " +
                        "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.execute();
    }

    public void cleanUp() throws SQLException {
        System.out.println("Calling cleanup...");
    }
}
