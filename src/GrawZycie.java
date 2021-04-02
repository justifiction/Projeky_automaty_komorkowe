public class GrawZycie {


    DataMenager dm;

    public GrawZycie(DataMenager dataMenager) {
        this.dm = dataMenager;
    }

    public Komorka[][] gra_w_zycie(Komorka[][] cells, int liczbaiteracji) {

        int licznik_zywych = 0;

        Komorka[][] tabkopia = new Komorka[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                tabkopia[i][j] = new Komorka(cells[i][j].rozmiar, cells[i][j].wartosc);
                tabkopia[i][j].setX(i);
                tabkopia[i][j].setY(j);

            }
        }
        if (dm.licznik_iteracji < liczbaiteracji) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {

                    licznik_zywych = 0;
                    if (cells[i][j].wartosc == 0) { //jest martwa
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                if (i + k < 0) {
                                    if (j + l < 0) {
                                        if (cells[cells.length - 1][cells[i].length - 1].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (j + l > cells[i].length - 1) {
                                        if (cells[cells.length - 1][0].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (cells[cells.length - 1][j + l].wartosc == 1)
                                        licznik_zywych++;
                                } else if (i + k > cells.length - 1) {
                                    if (j + l < 0) {
                                        if (cells[0][cells[i].length - 1].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (j + l > cells[i].length - 1) {
                                        if (cells[0][0].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (cells[0][j + l].wartosc == 1)
                                        licznik_zywych++;
                                } else if (j + l < 0) {
                                    if (cells[i + k][cells[i].length - 1].wartosc == 1)
                                        licznik_zywych++;
                                } else if (j + l > cells[i].length - 1) {
                                    if (cells[i + k][0].wartosc == 1)
                                        licznik_zywych++;
                                } else if (cells[i + k][j + l].wartosc == 1) {
                                    licznik_zywych++;
                                }

                            }


                        }

                        if (licznik_zywych == 3) {
                            tabkopia[i][j].wartosc = 1;
                        }
                    } else if (cells[i][j].wartosc == 1) { //jest zywa
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                if (i + k < 0) {
                                    if (j + l < 0) {
                                        if (cells[cells.length - 1][cells[i].length - 1].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (j + l > cells[i].length - 1) {
                                        if (cells[cells.length - 1][0].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (cells[cells.length - 1][j + l].wartosc == 1)
                                        licznik_zywych++;
                                } else if (i + k > cells.length - 1) {
                                    if (j + l < 0) {
                                        if (cells[0][cells[i].length - 1].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (j + l > cells[i].length - 1) {
                                        if (cells[0][0].wartosc == 1)
                                            licznik_zywych++;
                                    } else if (cells[0][j + l].wartosc == 1)
                                        licznik_zywych++;
                                } else if (j + l < 0) {
                                    if (cells[i + k][cells[i].length - 1].wartosc == 1)
                                        licznik_zywych++;
                                } else if (j + l > cells[i].length - 1) {
                                    if (cells[i + k][0].wartosc == 1)
                                        licznik_zywych++;
                                } else if (cells[i + k][j + l].wartosc == 1) {
                                    licznik_zywych++;
                                }
                            }
                        }
                        if (licznik_zywych - 1 == 3 || licznik_zywych - 1 == 2)
                            tabkopia[i][j].wartosc = 1;
                        if (licznik_zywych - 1 > 3 || licznik_zywych - 1 < 2)
                            tabkopia[i][j].wartosc = 0;
                    }
                }
            }
            dm.licznik_iteracji++;
            dm.listamacierzy.add(tabkopia);
            gra_w_zycie(tabkopia, liczbaiteracji);

        }
        return tabkopia;

    }


}
