package controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.Genre;
import model.Link;
import model.MusicLibrary;
import model.Track;

import java.io.IOException;


public class MainViewController extends Controller<EditController> {

    private MusicLibrary musicLibrary = new MusicLibrary();

    @FXML
    private TableView table;
    @FXML
    private TextField filterText;
    @FXML
    private TableColumn<Link, String> columnTrackName;
    @FXML
    private TableColumn<Link, String> columnGenre;


    @FXML
    private void initialize() throws IOException {
        musicLibrary.initializeData();
        columnTrackName.setCellValueFactory(new PropertyValueFactory<Link, String>("trackName"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<Link, String>("genreName"));
        table.setItems(musicLibrary.getObservableList());
        FilteredList<Link> filteredData = new FilteredList<>(musicLibrary.getObservableList(), p -> true);

        fxmlLoader.setLocation(getClass().getResource("../view/edit.fxml"));
        fxmlEdit = fxmlLoader.load();
        editController = fxmlLoader.getController();

        filterText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(link -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (link.getGenreName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (link.getTrackName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Link> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void showAddDialog(ActionEvent actionEvent) {
        Genre genre = new Genre();
        Track track = new Track();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editController.addLink(genre, track);
        showDialog(parentWindow);
        try {
            if (!track.getName().equals("") && !genre.getName().equals("") && !track.getArtist().equals("")
                    && !track.getAlbum().equals("") && !Double.toString(track.getDuration()).equals("")) {
                try {
                    musicLibrary.addGenre(genre);
                    musicLibrary.addTrack(track);
                    musicLibrary.addLink(genre, track);
                    musicLibrary.save();
                } catch (IllegalArgumentException e) {
                    showErrorMessage("Некорректное значение");
                }
            }
        } catch (NullPointerException e) {
        }
    }

    public void showEditDialog(ActionEvent actionEvent) {
        try {
            Link selectedLink = (Link) table.getSelectionModel().getSelectedItem();
            Genre genre = musicLibrary.searchGenre(selectedLink.getGenreName());
            Track track = musicLibrary.searchTrack(selectedLink.getTrackName());
            musicLibrary.removeLink(selectedLink);
            Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
            editController.setLink(genre, track);
            showDialog(parentWindow);
            musicLibrary.addGenre(genre);
            musicLibrary.addTrack(track);
            musicLibrary.addLink(genre, track);
            musicLibrary.save();
        } catch (NullPointerException e) {
            showErrorMessage("Не выбран объект для редактирования.");
        }
    }

    public void delete() {
        try {
            Link selectedLink = (Link) table.getSelectionModel().getSelectedItem();
            musicLibrary.removeLink(selectedLink);
            musicLibrary.save();
        } catch (NullPointerException e) {
            showErrorMessage("Не выбран объект для удаления.");
        }
    }
}
