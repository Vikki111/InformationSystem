package model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MusicLibrary implements Serializable {

    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private File fileTracks = new File("src\\model\\store\\tracks.txt");
    private File fileGenres = new File("src\\model\\store\\genres.txt");
    private File fileLinks = new File("src\\model\\store\\links.txt");

    private ObservableList<Link> observableList = FXCollections.observableArrayList();


    public void serializeTrack() {
        try (FileOutputStream out = new FileOutputStream(fileTracks);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            for (Track track : tracks) {
                oos.writeObject(track);
            }
        } catch (IOException e) {
        }
    }


    public void deserializeTrack(InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            Track track;
            while ((track = (Track) ois.readObject()) != null) {
                tracks.add(track);
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public void serializeGenre() {
        try (FileOutputStream out = new FileOutputStream(fileGenres);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            for (Genre genre : genres) {
                oos.writeObject(genre);
            }
        } catch (IOException e) {
        }
    }

    public void deserializeGenre(InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            Genre genre;
            while ((genre = (Genre) ois.readObject()) != null) {
                genres.add(genre);
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public void serializeLink() {
        try (FileOutputStream out = new FileOutputStream(fileLinks);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            for (Link link : observableList) {
                oos.writeObject(link.getGenreName());
                oos.writeObject(link.getTrackName());
            }
        } catch (IOException e) {
        }
    }

    public void deserializeLink(InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            String genreName;
            String trackName;
            while ((genreName = (String) ois.readObject()) != null) {
                trackName = (String) ois.readObject();
                observableList.add(new Link(genreName, trackName));
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public void save() {
        serializeTrack();
        serializeGenre();
        serializeLink();
    }

    public void initializeData() {
        try (FileInputStream fisGenre = new FileInputStream(getFileGenres())) {
            deserializeGenre(fisGenre);
        } catch (IOException e) {
        }
        try (FileInputStream fisGenre = new FileInputStream(getFileGenres())) {
            deserializeGenre(fisGenre);
        } catch (IOException e) {
        }
        try (FileInputStream fisTrack = new FileInputStream(getFileTracks())) {
            deserializeTrack(fisTrack);
        } catch (IOException e) {
        }
        try (FileInputStream fisLink = new FileInputStream(getFileLinks());) {
            deserializeLink(fisLink);
        } catch (IOException e) {
        }
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addLink(Genre genre, Track track) {
        observableList.add(new Link(genre.getName(), track.getName()));
    }

    public void removeLink(Genre genre, Track track) {
        for (Link link : observableList) {
            if (genre.getName().equals(link.getGenreName()) || track.getName().equals(link.getTrackName())) {
                observableList.remove(link);
                return;
            }
        }
    }

    public void removeLink(Link link) {
        if (link == null) {
            throw new NullPointerException();
        } else {
            observableList.remove(link);
        }
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
        for (Link link : observableList) {
            if (link.getTrackName().equalsIgnoreCase(track.getName())) {
                observableList.remove(link);
                return;
            }
        }
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        for (Link link : observableList) {
            if (link.getGenreName().equalsIgnoreCase(genre.getName())) {
                observableList.remove(link);
                return;
            }
        }
    }


    public Track searchTrack(String trackName) {
        for (Track trackIterator : getTracks()) {
            if (trackIterator.getName().equalsIgnoreCase(trackName)) {
                return trackIterator;
            }
        }
        throw new IllegalArgumentException("Трек не найден");
    }


    public Genre searchGenre(String genreName) {
        for (Genre genreIterator : getGenres()) {
            if (genreIterator.getName().equalsIgnoreCase(genreName)) {
                return genreIterator;
            }
        }
        throw new IllegalArgumentException("Жанр не найден");
    }

    public static Track initializeTrack(String name, String artist, String album, double duration) {
        return new Track(name, artist, album, duration);
    }

    public static Genre initializeGenre(String name) {
        return new Genre(name);
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public File getFileTracks() {
        return fileTracks;
    }

    public File getFileGenres() {
        return fileGenres;
    }

    public File getFileLinks() {
        return fileLinks;
    }

    public ObservableList<Link> getObservableList() {
        return observableList;
    }
}
