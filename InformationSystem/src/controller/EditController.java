package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Genre;
import model.MusicLibrary;
import model.Track;

public class EditController {
    @FXML
    private TextField trackName;
    @FXML
    private TextField genreName;
    @FXML
    private TextField artist;
    @FXML
    private TextField album;
    @FXML
    private TextField duration;

    private Genre genre;
    private Track track;

    public void addLink(Genre genre, Track track) {
        this.genre = genre;
        this.track = track;
    }

    public void setLink(Genre genre, Track track){
        this.genre = genre;
        this.track = track;
        trackName.setText(track.getName());
        artist.setText(track.getArtist());
        album.setText(track.getAlbum());
        duration.setText(Double.toString(track.getDuration()));
        genreName.setText(genre.getName());
    }

    public void closeStage(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        trackName.clear();
        artist.clear();
        album.clear();
        artist.clear();
        duration.clear();
        genreName.clear();
        stage.close();
    }


    public void actionSave(ActionEvent actionEvent) {
        try{
            Double d = Double.parseDouble(duration.getText());
        }catch (NumberFormatException e){
            showErrorMessage("Некорректное значение поля \"Длительность\"");
            return;
        }
        if (trackName.getText().isEmpty() || genreName.getText().isEmpty()
                || artist.getText().isEmpty() || album.getText().isEmpty()) {
            showErrorMessage("Заполните все поля");
        } else {
            track.setName(trackName.getText());
            track.setArtist(artist.getText());
            track.setAlbum(album.getText());
            track.setDuration(Double.parseDouble(duration.getText()));
            genre.setName(genreName.getText());
            trackName.clear();
            artist.clear();
            album.clear();
            artist.clear();
            duration.clear();
            genreName.clear();
            closeStage(actionEvent);
        }
    }

    protected void showErrorMessage(String string) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка!");
        alert.setContentText(string);
        alert.showAndWait();
    }
}
