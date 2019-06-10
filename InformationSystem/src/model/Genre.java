package model;


import java.io.Serializable;

public class Genre implements Serializable {

    private String name;

    public Genre() {
    }

    public Genre(Genre another){
        this.name = another.name;
    }
    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{ " + "name= " + name + " " + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj.getClass() == this.getClass()){
            Genre genre = (Genre) obj;
            if(genre.getName().equals(this.getName())){
                return true;
            }
        }
        return false;
    }
}
