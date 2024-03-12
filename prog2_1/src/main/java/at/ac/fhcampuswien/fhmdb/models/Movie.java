package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here
    private List<Genre> genres; /// adds genre property

    public Movie(String title, String description,List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres; /// Constructor with genres
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    } /// Getter method genres

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        /// Dummy Dara
        List<Genre> lifeIsBeautifulGenres = new ArrayList<>();
        lifeIsBeautifulGenres.add(Genre.DRAMA);
        lifeIsBeautifulGenres.add(Genre.ROMANCE);
        movies.add(new Movie("Life is Beautiful", "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp.", lifeIsBeautifulGenres));


        List<Genre> pussInBootsGenres = new ArrayList<>();
        pussInBootsGenres.add(Genre.COMEDY);
        pussInBootsGenres.add(Genre.FAMILY);
        pussInBootsGenres.add(Genre.ANIMATION);
        movies.add(new Movie("Puss in Boots", "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.", pussInBootsGenres));

        List<Genre> usualSuspectsGenres = new ArrayList<>();
        usualSuspectsGenres.add(Genre.CRIME);
        usualSuspectsGenres.add(Genre.DRAMA);
        usualSuspectsGenres.add(Genre.MYSTERY);
        movies.add(new Movie("The Usual Suspects", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.", usualSuspectsGenres));

        List<Genre> avatarGenres = new ArrayList<>();
        avatarGenres.add(Genre.ANIMATION);
        avatarGenres.add(Genre.DRAMA);
        avatarGenres.add(Genre.ACTION);
        movies.add(new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", avatarGenres));

        List<Genre> wolfOfWallStreetGenres = new ArrayList<>();
        wolfOfWallStreetGenres.add(Genre.DRAMA);
        wolfOfWallStreetGenres.add(Genre.ROMANCE);
        wolfOfWallStreetGenres.add(Genre.BIOGRAPHY);
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", wolfOfWallStreetGenres));

        return movies;
    }


}
