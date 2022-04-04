package main;

import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet {
ArrayList<Movie> movies = new ArrayList<>();
int nextId = 1;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        try{
            PrintWriter out = response.getWriter();

            String movieString = new Gson().toJson(movies.toArray());

            out.println(movieString);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try{
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
}
