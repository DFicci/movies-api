package data;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySqlMoviesDao implements  MoviesDao{
    private Connection connection;

    public MySqlMoviesDao(){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                  "jdbc:mysql://" + Config.DB_HOST + "3306/daniel?allowPublicKeyRetreival=true&useSSL=false",
                    Config.DB_USER,
                    Config.DB_PW
        );
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        return null;
    }

    @Override
    public Movie findOne(int id) {
        return null;
    }

    @Override
    public void insert(Movie movie) {

    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

    }

    @Override
    public void update(Movie movie) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
