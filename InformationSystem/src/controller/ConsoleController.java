package controller;

import model.Genre;
import model.Link;
import model.MusicLibrary;
import model.Track;
import view.ViewModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleController {

    final String DELIMITER = " ";
    final String DELIMITER2 = " | ";

    private ViewModel view;
    private MusicLibrary musicLibrary;

    public ConsoleController(ViewModel view, MusicLibrary musicLibrary) {
        this.view = view;
        this.musicLibrary = musicLibrary;
        musicLibrary.initializeData();
    }


    public void launch() {
        Track track;
        Genre genre;
        view.showMenu();
        switch (view.getAction()) {
            case 1:
                view.alertNewTrack();
                track = MusicLibrary.initializeTrack(view.getString(), view.getString(), view.getString(), Double.parseDouble(view.getString()));
                getMusicLibrary().addTrack(track);
                break;
            case 2:
                genre = MusicLibrary.initializeGenre(view.getGenreName());
                getMusicLibrary().addGenre(genre);
                break;
            case 3:
                try {
                    track = musicLibrary.searchTrack(view.getTrackName());
                    genre = musicLibrary.searchGenre(view.getGenreName());
                    getMusicLibrary().addLink(genre, track);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 4:
                try {
                    track = musicLibrary.searchTrack(view.getTrackName());
                    genre = musicLibrary.searchGenre(view.getGenreName());
                    getMusicLibrary().removeLink(genre, track);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 5:
                try {
                    track = musicLibrary.searchTrack(view.getTrackName());
                    getMusicLibrary().removeTrack(track);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 6:
                try {
                    genre = musicLibrary.searchGenre(view.getGenreName());
                    getMusicLibrary().removeGenre(genre);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 7:
                try {
                    track = musicLibrary.searchTrack(view.getTrackName());
                    getMusicLibrary().removeTrack(track);
                    view.alertNewTrack();
                    Track newTrack = MusicLibrary.initializeTrack(view.getString(), view.getString(), view.getString(), Double.parseDouble(view.getString()));
                    getMusicLibrary().addTrack(newTrack);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 8:
                try {
                    genre = musicLibrary.searchGenre(view.getGenreName());
                    getMusicLibrary().removeGenre(genre);
                    Genre newGenre = MusicLibrary.initializeGenre(view.getGenreName());
                    getMusicLibrary().addGenre(newGenre);
                } catch (IllegalArgumentException e) {
                    view.printException(e);
                }
                break;
            case 9:
                printTrack();
                break;
            case 10:
                printGenre();
                break;
            case 11:
                printLink();
                break;
            case 12:
                musicLibrary.save();
                break;
            case 0:
                return;
        }
        launch();
    }

    public void printLink() {
        view.print(getStringLink());
    }

    public void printTrack() {
        view.print(getStringTracks());
    }

    public void printGenre() {
        view.print(getStringGenres());
    }


    public ArrayList<String> getStringTracks() {

        ArrayList<String> result = new ArrayList<>();
        for (Track currentTrack : musicLibrary.getTracks()) {
            StringBuilder str = new StringBuilder(currentTrack.getName()).append(DELIMITER)
                    .append(currentTrack.getArtist()).append(DELIMITER)
                    .append(currentTrack.getAlbum()).append(DELIMITER)
                    .append(currentTrack.getDuration()).append(DELIMITER);
            result.add(str.toString());
        }
        return result;
    }

    public ArrayList<String> getStringGenres() {
        ArrayList<String> result = new ArrayList<>();
        for (Genre currentGenre : musicLibrary.getGenres()) {
            StringBuilder str = new StringBuilder(currentGenre.getName()).append(DELIMITER);
            result.add(str.toString());
        }
        return result;
    }

    public ArrayList<String> getStringLink() {
        ArrayList<String> result = new ArrayList<>();
        for (Link link : musicLibrary.getObservableList()) {
            StringBuilder str = new StringBuilder(link.getGenreName()).append(DELIMITER)
                    .append(link.getTrackName()).append(DELIMITER2);
            result.add(str.toString());
        }
        return result;
    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

}
