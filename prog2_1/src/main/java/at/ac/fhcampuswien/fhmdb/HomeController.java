package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();
    public List<Genre> allGenres = new ArrayList<>(EnumSet.allOf(Genre.class));    /// Creates a List with all genres

    ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(allGenres);    /// adds genre-list to dropbox

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                observableMovies.sort(Comparator.comparing(Movie::getTitle)); /// sort list alphabetically

                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed()); /// sort list alphabetically reversed

                sortBtn.setText("Sort (asc)");
            }
        });

        searchBtn.setOnAction(actionEvent -> {
            observableMovies.removeAll(allMovies);
            for (Movie m : allMovies) {
                if (searchField != null && genreComboBox.getValue() != null) {
                    if (m.getGenres().contains(genreComboBox.getValue()) && (compareStrings(m.getTitle(),searchField.getText()) || compareStrings(m.getDescription(),searchField.getText())))
                        observableMovies.add(m);
                } else if (searchField == null){
                    if (m.getGenres().contains(genreComboBox.getValue()))
                        observableMovies.add(m);
                } else if (genreComboBox.getValue() == null){
                    if (compareStrings(m.getTitle(),searchField.getText()) || compareStrings(m.getDescription(),searchField.getText()))
                        observableMovies.add(m);
                }
            }
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });


    }
    public boolean compareStrings(String a, String b){
        String a1 = a;
        a1 = a1.toLowerCase();
        String b1 = b;
        b1 = b1.toLowerCase();
        if (a1.contains(b1))
            return true;

        return false;
    }
}