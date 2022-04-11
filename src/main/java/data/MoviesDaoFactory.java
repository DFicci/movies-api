package data;

import data.InMemoryMoviesDao;
import data.MoviesDao;
import data.MySqlMoviesDao;

public class MoviesDaoFactory {

    // private static data.Config config = new data.Config();

    public enum DAOType {MYSQL, IN_MEMORY}

    ; //Notice we have two values here

    public static MoviesDao getMoviesDao(DAOType daoType) {

        switch (daoType) {
            case IN_MEMORY: { //yet we have one switch case. We'll get to that!
                return new InMemoryMoviesDao();
            }
        }
        return null;
    }
}
