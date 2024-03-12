package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.application.Platform;


import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private HomeController homeController;
    @BeforeAll
    static void setUp() {
        Platform.startup(() -> {
            //
        });
    }
    @BeforeEach
    void setupHomeController(){
        homeController = new HomeController();
        homeController.searchField = new TextField();
        homeController.searchBtn = new JFXButton();
        homeController.sortBtn = new JFXButton();
        homeController.genreComboBox = new JFXComboBox();
        homeController.movieListView = new JFXListView();
    }
    @Test
    void test_if_search_by_title_works(){
        homeController.searchField.setText("Boots");
        homeController.genreComboBox.setValue(null);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies.size());
        assertEquals("Puss in Boots", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_search_in_discription_works(){
        homeController.searchField.setText("librarian");
        homeController.genreComboBox.setValue(null);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies.size());
        assertEquals("Life is Beautiful", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_search_is_case_insensitive(){
        homeController.searchField.setText("BoOtS");
        homeController.genreComboBox.setValue(null);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies.size());
        assertEquals("Puss in Boots", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_list_is_empty_when_no_search_match(){
        homeController.searchField.setText("vkgvkzuvk");
        homeController.genreComboBox.setValue(null);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(0, filteredMovies.size());
    }
    @Test
    void test_if_filtering_by_genre_is_working(){
        homeController.searchField.setText(null);
        homeController.genreComboBox.setValue(Genre.CRIME);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies.size());
        assertEquals("The Usual Suspects", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_list_is_empty_when_no_genre_match(){
        homeController.searchField.setText(null);
        homeController.genreComboBox.setValue(Genre.DOCUMENTARY);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(0, filteredMovies.size());
    }
    @Test
    void test_if_filtering_by_search_and_genre_works(){
        homeController.searchField.setText("moon");
        homeController.genreComboBox.setValue(Genre.ANIMATION);

        homeController.searchBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies.size());
        assertEquals("Avatar", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_sort_ascending_works(){
        homeController.searchField.setText(null);
        homeController.genreComboBox.setValue(null);
        homeController.observableMovies.addAll(homeController.allMovies);
        homeController.sortBtn.setText("Sort (asc)");

        homeController.sortBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(5, filteredMovies.size());
        assertEquals("Avatar", filteredMovies.get(0).getTitle());
        assertEquals("The Wolf of Wall Street",filteredMovies.get(filteredMovies.size()-1).getTitle());
    }
    @Test
    void test_if_sort_descending_works(){
        homeController.searchField.setText(null);
        homeController.genreComboBox.setValue(null);
        homeController.observableMovies.addAll(homeController.allMovies);
        homeController.sortBtn.setText("Sort (desc)");

        homeController.sortBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(5, filteredMovies.size());
        assertEquals("The Wolf of Wall Street", filteredMovies.get(0).getTitle());
        assertEquals("Avatar",filteredMovies.get(filteredMovies.size()-1).getTitle());
    }
    @Test
    void test_if_unfilter_button_works(){
        homeController.searchField.setText("wolf");
        homeController.genreComboBox.setValue(Genre.DRAMA);

        homeController.searchBtnHandler();
        homeController.unFilterBtnHandler();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(5, filteredMovies.size());
        assertEquals(homeController.searchField.getText(),null);
        assertEquals(homeController.genreComboBox.getValue(),null);
    }
}