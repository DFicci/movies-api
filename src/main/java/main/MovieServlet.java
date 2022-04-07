package main;

import com.google.gson.Gson;
import data.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet {
    ArrayList<Movie> movies = new ArrayList<>();
    int nextId = 1;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            String movieString = new Gson().toJson(movies.toArray());
            out.println(movieString);
        } catch (IOException e) {
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
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
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int targetId = getTargetIdFromURI(request.getRequestURI());
        System.out.println(" target id is " + targetId);

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getId() == targetId) {
                movies.remove(i);
                break;
            }
        }
        PrintWriter out = response.getWriter();
        out.println("movie deleted");

    }


}
