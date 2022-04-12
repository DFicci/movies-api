package data;

public class Movie {
    private String title;
    private String rating;
    private String poster;
    private String year;
    private String genre;
    private String director;
    private String plot;
    private String actors;
    private int id;

    public Movie(int id, String title, String rating, String poster, String year, String genre, String director, String plot,
                 String actors){
        this.id = id;
        this.title = title;
        this.rating = String.valueOf(rating);
        this.poster = poster;
        this.year = String.valueOf(year);
        this.genre = genre;
        this.director = director;
        this.plot = plot;
        this.actors = actors;

    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", poster='" + poster + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                ", actors='" + actors + '\'' +
                ", id=" + id +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() { return rating; }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
