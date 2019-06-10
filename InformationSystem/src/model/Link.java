package model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Link implements Serializable {

    private SimpleStringProperty genreName = new SimpleStringProperty("");
    private SimpleStringProperty trackName = new SimpleStringProperty("");

    public Link() {
    }

    public Link(String genreName, String trackName) {
        this.genreName.set(genreName);
        this.trackName.set(trackName);
    }

    public String getGenreName() {
        return genreName.get();
    }

    public void setGenreName(String genreName) {
        this.genreName.set(genreName);
    }

    public String getTrackName() {
        return trackName.get();
    }

    public void setTrackName(String trackName) {
        this.trackName.set(trackName);
    }
}
