package view;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewModel {

    Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        System.out.println("1) Создать и добавить трек \n2) Создать и добавить жанр  \n3) Связать трек с жанром \n" +
                "4) Удалить связь \n5) Удалить трек \n6) Удалить жанр \n7) Изменить трек \n" +
                "8) Изменить жанр \n9) Печать треков \n10) Печать жанров \n11) Печать связей \n" +
                "12) Сохранить изменения \n0) Выход");
    }

    public void alertNewTrack(){
        System.out.println("Введите название, исполнителя, альбом и продолжительность трека:");
    }

    public String getString(){
        return scanner.next();
    }

    public int getAction(){
        return scanner.nextInt();
    }

    public void print(ArrayList<String> arrayList){
        for (String str: arrayList){
            System.out.println(str);
        }
        System.out.println();
    }

    public String getTrackName(){
        System.out.println("Введите название трека: ");
        return scanner.next();
    }

    public String getGenreName(){
        System.out.println("Введите название жанра: ");
        return scanner.next();
    }

    public void printException(Exception e){
        System.out.println(e.getMessage());
    }
}
