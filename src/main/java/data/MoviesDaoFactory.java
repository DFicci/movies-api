package data;

import data.InMemoryMoviesDao;
import data.MoviesDao;
import data.MySqlMoviesDao;

public class MoviesDaoFactory {

    public enum DAOType {MYSQL, IN_MEMORY}

    public static MoviesDao getMoviesDao(DAOType daoType) {

        switch (daoType) {
            case IN_MEMORY: {
                return new InMemoryMoviesDao();
            }
            case MYSQL:
                return new MySqlMoviesDao();
        }
        return null;
    }
}
