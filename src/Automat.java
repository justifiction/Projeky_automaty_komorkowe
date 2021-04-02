public class Automat {

    DataMenager dm;

    public Automat(DataMenager dataMenager) {
        this.dm = dataMenager;
    }

    public Komorka[][] automat(int rozmiarr, int liczbaiteracji, int rodzaj, int warunek, int rozmiarkomorki) {
        System.out.println(Integer.toBinaryString(rodzaj));
        char zero = '0';
        String binarnie = Integer.toBinaryString(rodzaj);
        int spr = 8 - binarnie.length();
        for (int j = 0; j < spr; j++)
            binarnie = zero + binarnie;
        System.out.println(binarnie);
        int[] binary = new int[8];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = Character.getNumericValue(binarnie.charAt(i));
            System.out.println(binary[i]);

        }
        Komorka[][] tab = new Komorka[liczbaiteracji][rozmiarr + 2];
        Komorka[][] tabkopia = new Komorka[liczbaiteracji][rozmiarr];

        for (int j = 0; j < liczbaiteracji; j++)
            for (int i = 0; i < tab[0].length; i++) {
                tab[j][i] = new Komorka(rozmiarkomorki, 0);
                if (i < tab[0].length - 2)
                    tabkopia[j][i] = new Komorka(rozmiarkomorki, 0);


                if (j == 0 && i == (tab[0].length / 2)) {

                    tab[0][i].wartosc = 1;

                }

            }


        for (int j = 1; j < liczbaiteracji; j++) {
            for (int i = 1; i < tab[j].length - 1; i++) {
                if (warunek == 0) {
                    if (i - 1 == 0)
                        tab[j - 1][i - 1].wartosc = tab[j - 1][rozmiarr].wartosc;
                    if (i == rozmiarr)
                        tab[j - 1][i + 1].wartosc = tab[j - 1][1].wartosc;
                } else {
                    if (i - 1 == 0)
                        tab[j - 1][i - 1].wartosc = 1;
                    if (i == rozmiarr)
                        tab[j - 1][i + 1].wartosc = 1;
                }

                if (tab[j - 1][i - 1].wartosc == 1 && tab[j - 1][i].wartosc == 1 & tab[j - 1][i + 1].wartosc == 1)
                    tab[j][i].wartosc = binary[0];
                if (tab[j - 1][i - 1].wartosc == 1 && tab[j - 1][i].wartosc == 1 & tab[j - 1][i + 1].wartosc == 0)
                    tab[j][i].wartosc = binary[1];
                if (tab[j - 1][i - 1].wartosc == 1 && tab[j - 1][i].wartosc == 0 & tab[j - 1][i + 1].wartosc == 1)
                    tab[j][i].wartosc = binary[2];
                if (tab[j - 1][i - 1].wartosc == 1 && tab[j - 1][i].wartosc == 0 & tab[j - 1][i + 1].wartosc == 0)
                    tab[j][i].wartosc = binary[3];
                if (tab[j - 1][i - 1].wartosc == 0 && tab[j - 1][i].wartosc == 1 & tab[j - 1][i + 1].wartosc == 1)
                    tab[j][i].wartosc = binary[4];
                if (tab[j - 1][i - 1].wartosc == 0 && tab[j - 1][i].wartosc == 1 & tab[j - 1][i + 1].wartosc == 0)
                    tab[j][i].wartosc = binary[5];
                if (tab[j - 1][i - 1].wartosc == 0 && tab[j - 1][i].wartosc == 0 & tab[j - 1][i + 1].wartosc == 1)
                    tab[j][i].wartosc = binary[6];
                if (tab[j - 1][i - 1].wartosc == 0 && tab[j - 1][i].wartosc == 0 & tab[j - 1][i + 1].wartosc == 0)
                    tab[j][i].wartosc = binary[7];


            }


        }
        for (int j = 0; j < tabkopia.length; j++) {
            for (int i = 0; i < tabkopia[j].length; i++) {
                tabkopia[j][i].wartosc = tab[j][i + 1].wartosc;
            }
            System.out.println();
        }


        return tabkopia;
    }
}
