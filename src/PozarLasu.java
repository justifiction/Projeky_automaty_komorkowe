import java.util.Random;

public class PozarLasu {
    DataMenager dm;

    public PozarLasu(DataMenager dataMenager) {
        this.dm = dataMenager;
    }

    public Komorka[][] pozar_lasu_funkcja(Komorka[][] cells, int liczbaiteracji) {


        int czyzielone;


        Komorka[][] tabkopia = new Komorka[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                tabkopia[i][j] = new Komorka(cells[i][j].rozmiar, cells[i][j].wartosc);
                tabkopia[i][j].setX(i);
                tabkopia[i][j].setY(j);

            }
        }

        dm.czysazielone = true;
        boolean poczatekgaszenia = false;
        boolean poczatekdeszczu = false;
        if (dm.licznik_iteracji2 < liczbaiteracji && dm.czysazielone) {

            czyzielone = 0;
            for (int i = 1; i < cells.length - 1; i++) {
                for (int j = 1; j < cells[i].length - 1; j++) {

                    if (dm.deszczyk && dm.licznik_iteracji2 == liczbaiteracji / 8) {
                        if (cells[i][j].wartosc == 2 || cells[i][j].wartosc == 1) {
                            Random prawdopodobienstwowiatr = new Random();
                            int d = prawdopodobienstwowiatr.nextInt(100);
                            if (d <= 30)
                                tabkopia[i + dm.x][j + dm.y].wartosc = 6;
                        }


                        poczatekdeszczu = true;
                    }
                    if (cells[i][j].wartosc == 6) {
                        dm.roznicaiteracji2 = dm.licznik_iteracji2 - dm.roznicaiteracji2;
                        if (dm.roznicaiteracji2 > 20) {
                            if (cells[i][j].wartosc == 2 || cells[i][j].wartosc == 6) {
                                tabkopia[i][j].wartosc = 1;

                                czyzielone = 1;
                                break;
                            }
                        }

                    }


                    if (cells[i][j].wartosc == 2 && poczatekdeszczu) {
                        tabkopia[i][j].wartosc = 1;
                    }


                    if (cells[i][j].wartosc == 4) {
                        if (dm.licznik_iteracji2 < 40) {
                            for (int k = -1; k <= 1; k++) {
                                for (int l = -1; l <= 1; l++) {
                                    if (cells[i + k][j + l].wartosc == 2) {
                                        tabkopia[i + k][j + l].wartosc = 5;
                                        poczatekgaszenia = true;
                                    }

                                }
                            }
                        } else {
                            tabkopia[i][j].wartosc = 1;
                        }
                    }
                    if (cells[i][j].wartosc == 5) {
                        dm.roznicaiteracji = dm.licznik_iteracji2 - dm.roznicaiteracji;

                        if (dm.licznik_iteracji2 < 40) {
                            for (int k = -1; k <= 1; k++) {
                                for (int l = -1; l <= 1; l++) {
                                    if (cells[i + k][j + l].wartosc == 2) {
                                        Random prawdopodobienstwowiatr = new Random();
                                        int d = prawdopodobienstwowiatr.nextInt(100);
                                        if (d <= dm.jakmocnowiejewiatr)
                                            tabkopia[i + dm.x][j + dm.y].wartosc = 5;

                                    }

                                }
                            }

                        }
                        if (dm.roznicaiteracji > 10) {
                            tabkopia[i][j].wartosc = 1;
                        }

                    }


                    if (cells[i][j].wartosc == 2 && !poczatekdeszczu) {
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                if (cells[i + k][j + l].wartosc == 1) {
                                    Random ogolne = new Random();
                                    int w = ogolne.nextInt(100);
                                    if (w <= 10) {
                                        tabkopia[i + k][j + l].wartosc = 2;

                                    }

                                    Random prawdopodobienstwowiatr = new Random();
                                    int d = prawdopodobienstwowiatr.nextInt(100);
                                    if (d <= dm.jakmocnowiejewiatr) {
                                        tabkopia[i + dm.x][j + dm.y].wartosc = 2;
                                    }


                                }


                            }


                        }
                    }
                    if (cells[i][j].wartosc == 1)
                        czyzielone = 1;

                }
                if (i == cells.length - 2 && czyzielone == 0) {
                    dm.czysazielone = false;

                    for (int k = 1; k < cells.length - 1; k++) {
                        for (int l = 1; l < cells[i].length - 1; l++) {

                            if (cells[k][l].wartosc == 2) {
                                tabkopia[k][l].wartosc = 3;

                            }
                        }
                    }


                }


            }


            if (poczatekgaszenia)
                dm.roznicaiteracji = dm.licznik_iteracji2;
            if (poczatekdeszczu)
                dm.roznicaiteracji2 = dm.licznik_iteracji2;


            dm.licznik_iteracji2++;
            dm.listamacierzy2.add(tabkopia);
            if (dm.czysazielone)
                pozar_lasu_funkcja(tabkopia, liczbaiteracji);
        }


        return tabkopia;

    }


}


