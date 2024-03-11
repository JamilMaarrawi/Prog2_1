package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void test_if_search_by_title_works(){
        HomeController homeController = new HomeController();
        homeController.searchField.setText("Boots");
        homeController.genreComboBox.setValue(null); // Make sure genre filter is not applied for this test

        Robot robot;
        homeController.searchBtn.fire();

        List<Movie> filteredMovies = homeController.observableMovies;
        assertEquals(1, filteredMovies);
        assertEquals("Movie 1 Action", filteredMovies.get(0).getTitle());
    }
    @Test
    void test_if_search_discription_works(){

    }
}