import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DataMenager {

    BufferedImage bgImg;
    BufferedImage dopozaru;
    int progg;
    int value;
    Komorka[][] automacierz;
    Komorka[][] automacierz2;
    int s;
    Boolean running;
    Boolean running2;
    Boolean czysazielone;
    Boolean przegrana = false;
    Boolean wygrana = false;
    Boolean zniszczone = false;


    int x = 7, y = 7;
    int jakmocnowiejewiatr;
    int roznicaiteracji;
    int roznicaiteracji2;
    Boolean deszczyk = false;


    ArrayList<Komorka[][]> listamacierzy;
    ArrayList<Komorka[][]> listamacierzy2;

    int iteracjeteraz = 0;
    int licznik = 0;
    int licznik_iteracji = 0;
    int iteracjeteraz2 = 0;
    int licznik2 = 0;
    int licznik_iteracji2 = 0;


    public DataMenager() {
        listamacierzy = new ArrayList<>();
        listamacierzy2 = new ArrayList<>();

    }


}

