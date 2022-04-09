package main;

import com.google.gson.Gson;
import data.InMemoryMoviesDao;
import data.Movie;
import data.MoviesDao;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import static data.MoviesDaoFactory.DAOType.IN_MEMORY;
import static data.MoviesDaoFactory.getMoviesDao;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet {
    ArrayList<Movie> movies = new ArrayList<>();
    int nextId = 1;



    MoviesDao moviesDao = getMoviesDao(IN_MEMORY);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            String movieString = new Gson().toJson(moviesDao.all());
            out.println(movieString);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            Movie[] newMovies = new Gson().fromJson(request.getReader(), Movie[].class);

            for (int i = 0; i < newMovies.length; i++) {
                newMovies[i].setId(nextId++);
                movies.add(newMovies[i]);
            }

            out.println("movie added.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getTargetIdFromURI(String uri) {
        String[] uriParts = uri.split("/");
        int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);
        return targetId;
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int targetId = getTargetIdFromURI(request.getRequestURI());

        Movie newMovie = new Gson().fromJson(request.getReader(), Movie.class);

        for (Movie movie : movies) {
            if (movie.getId() == targetId) {
                if (newMovie.getTitle() != null) {
                    movie.setTitle(newMovie.getTitle());
                }
                if (newMovie.getPoster() != null) {
                    movie.setPoster(newMovie.getPoster());
                }
                if (newMovie.getActors() != null) {
                    movie.setActors(newMovie.getActors());
                }
                if (newMovie.getDirector() != null) {
                    movie.setDirector(newMovie.getDirector());
                }
                if (newMovie.getGenre() != null) {
                    movie.setGenre(newMovie.getGenre());
                }
                if (newMovie.getPlot() != null) {
                    movie.setPlot(newMovie.getPlot());
                }
                if (newMovie.getYear() != null) {
                    movie.setYear(newMovie.getYear());
                }
                if (newMovie.getRating() != null) {
                    movie.setRating(newMovie.getRating());
                }
            }
            PrintWriter out = response.getWriter();
            out.println("movie updated");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int targetId = getTargetIdFromURI(request.getRequestURI());
        System.out.println(" target id is " + targetId);

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if(movie.getId() == targetId){
                try {
                    moviesDao.delete(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        PrintWriter out = response.getWriter();
        out.println("movie deleted");

    }


}
