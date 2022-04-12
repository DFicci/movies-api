package data;

public class Config {
    public static final String DB_USER = "daniel";
    public static final String DB_PW = "nRjp2H4SFYVD37xQ";
    public static final String DB_HOST = "emp.fulgentcorp.com";

    public String getUrl(){
        return "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC&useSSL=false";
    }

    public String getUser(){
        return "root";
    }

    public String getPassword(){
        return "codeup";
    }
}
