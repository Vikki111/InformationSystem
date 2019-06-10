package model;


import java.io.Serializable;

public class Track implements Serializable {

    private String name;
    private String artist;
    private String album;
    private double duration;

    public Track() {
    }

    public Track(String name, String artist, String album, double duration) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public Track(Track another){
        this.name = another.name;
        this.artist = another.artist;
        this.album = another.album;
        this.duration = another.duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Track( "+"name: "+name+" artist: "+artist+" album: "+album+" duration: "+duration+" )";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj.getClass().equals(this.getClass())){
            Track track = (Track) obj;
            if(track.getName().equals(this.getName()) && track.getArtist().equals(this.getArtist())
                    && track.getAlbum().equals(this.getAlbum()) && track.getDuration() == this.getDuration()){
                return true;
            }
        }
        return false;
    }
}
