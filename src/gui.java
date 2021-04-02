import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class gui extends JFrame implements MouseListener {
    private JPanel mainPanel;
    private JCanvasPanel canvas;
    private JPanel buttonPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;
    private JPanel buttonPanel3;
    private JPanel buttonPanel4;


    private JButton start;
    private JButton stop;
    private JButton wyczysc;
    private JTextField rozmiarrkom;
    private JComboBox stany;
    private JComboBox czas;


    private JButton loadphoto;
    private JButton loadfromtxt;
    private JButton darker;
    private JButton lighter;
    private JButton blackandwhite;
    private JTextField prog;
    private JTextField wartosc;
    private JButton filtr;
    private JButton filtr1;
    private JButton filtr2;
    private JButton filtr3;
    private JButton dylatacja;
    private JButton erozja;
    private JButton otwarcie;
    private JButton zamknięcie;
    private JButton automat;
    private JButton grawzycie;
    private JButton pozarlasuobrazek;
    private JButton palisie;
    private JTextField silawiatru;
    private JComboBox wiatr;
    private JButton strazak;
    private JButton deszcz;
    private JButton pausa;
    private JButton nowa_gra;
    private JComboBox czas_pozar;
    private JProgressBar progressBar;


    private int globalTimer = 0;
    private int limitTimer = 10;


    private JTabbedPane tp;


    DataMenager dm;
    Funkcje f;
    GrawZycie gra;
    Automat auto;
    PozarLasu pl;
    int size = 0;
    Timer timer;
    Timer timer2;
    Timer timer3;
    Timer timer4;
    int cos = 0;
    int cos2 = 0;
    int t = 125;
    int t1 = 125;


    public gui(String title) {

        super(title);


        dm = new DataMenager();
        f = new Funkcje(dm);
        pl = new PozarLasu(dm);
        auto = new Automat(dm);
        gra = new GrawZycie(dm);

        dm.running = false;
        addMouseListener(this);


        loadphoto = new JButton("Wczytaj obraz");
        loadfromtxt = new JButton("Wczytaj obraz z txt");
        darker = new JButton("Przyciemnij");
        lighter = new JButton("Rozjaśnij");
        wartosc = new JTextField("50");
        wartosc.setBorder(new TitledBorder("Wartość rozjaśnienia/przyciemnienia"));
        blackandwhite = new JButton("B&W");
        prog = new JTextField("228");
        prog.setBorder(new TitledBorder("Próg binaryzacji"));
        filtr = new JButton("Konwolucja");
        filtr1 = new JButton("Filtr dolnoprzepustowy");
        filtr2 = new JButton("Filtr górnoprzepustowy");
        filtr3 = new JButton("Filtr Gaussa");
        dylatacja = new JButton("Dylatacja");
        erozja = new JButton("Erozja");
        otwarcie = new JButton("Otwarcie morfologiczne");
        zamknięcie = new JButton("Zamknięcie morfologiczne");

        automat = new JButton("Automat");

        grawzycie = new JButton("Gra w życie");
        start = new JButton("Rozpocznij");
        stop = new JButton("Stop");
        wyczysc = new JButton("Wyczyść");
        rozmiarrkom = new JTextField();
        rozmiarrkom.setBorder(new TitledBorder("Rozmiar komórki:"));
        rozmiarrkom.setText("2");
        stany = new JComboBox(new String[]{"Niezmienny", "Glider", "Losowy", "Oscylator", "Ręczna definicja"});
        stany.setBorder(new TitledBorder("Stan początkowy:"));
        czas = new JComboBox(new String[]{"1/8 sekundy", "1/4 sekundy", "1/2 sekundy", "1 sekunda", "2 sekundy"});
        czas.setBorder(new TitledBorder("Interwał między krokami:"));
        czas.setSelectedItem(new String[]{"1/8 sekundy"});

        pozarlasuobrazek = new JButton("Graj");
        palisie = new JButton("Start");
        palisie.setBackground(new Color(100, 215, 40));
        silawiatru = new JTextField();
        silawiatru.setBorder(new TitledBorder("Siła wiatru:"));
        silawiatru.setText("80");
        wiatr = new JComboBox(new String[]{"północny", "północno-wschodni", "wschodni",
                "południowo-wschodni", "południowy", "południowo-zachodni", "zachodni", "północno-zachodni"});
        wiatr.setBorder(new TitledBorder("Rodzaj wiatru:"));
        wiatr.setSelectedItem(new String[]{"północny"});
        strazak = new JButton("Dodaj strażaka");
        deszcz = new JButton("Deszcz!");
        pausa = new JButton("Pauza");
        pausa.setBackground(new Color(245, 50, 80));
        nowa_gra = new JButton("Nowa gra");
        nowa_gra.setBackground(new Color(100, 195, 40));
        czas_pozar = new JComboBox(new String[]{"1/8 sekundy", "1/4 sekundy", "1/2 sekundy", "1 sekunda", "2 sekundy"});
        czas_pozar.setBorder(new TitledBorder("Predkosc gry:"));
        czas_pozar.setSelectedItem(new String[]{"1/8 sekundy"});
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        pane.setText("ZASADY GRY!\n" + "Gra polega na tym, żeby ugasić pożar.\n" +
                "Zanim zaczniesz, wybierz rodzaj i siłę wiatru oraz prędkość gry.\n Możesz także dodać strażaków (kliknij 'Dodaj strażaka', \n" +
                "a następnie klikaj na obrazku na zielony teren). \nZanim klikniesz 'Start' będziesz miał również chwilę " +
                "na znalezienie przycisku 'Deszcz!'. \nJest on ukryty gdzieś w aplikacji. \nJeśli uda Ci się go znależć i kliknąć przed upływem " +
                "czasu,\n z pewnością uratujesz swój las. \nPowodzenia!");
        JScrollPane editorScrollPane = new JScrollPane(pane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        editorScrollPane.setPreferredSize(new Dimension(100, 90));
        progressBar = new JProgressBar();
        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        darker.setEnabled(false);
        lighter.setEnabled(false);
        blackandwhite.setEnabled(false);
        prog.setEnabled(false);
        wartosc.setEnabled(false);
        filtr.setEnabled(false);
        filtr1.setEnabled(false);
        filtr2.setEnabled(false);
        filtr3.setEnabled(false);
        dylatacja.setEnabled(false);
        erozja.setEnabled(false);
        otwarcie.setEnabled(false);
        zamknięcie.setEnabled(false);

        loadphoto.addActionListener(actionEvent -> {
            canvas.repaint();
            darker.setEnabled(true);
            lighter.setEnabled(true);
            blackandwhite.setEnabled(true);
            prog.setEnabled(true);
            wartosc.setEnabled(true);
            filtr.setEnabled(true);
            filtr1.setEnabled(true);
            filtr2.setEnabled(true);
            filtr3.setEnabled(true);
            dylatacja.setEnabled(true);
            erozja.setEnabled(true);
            otwarcie.setEnabled(true);
            zamknięcie.setEnabled(true);
            try {
                BufferedImage bg = ImageIO.read(new File("Mapa_MD_no_terrain_low_res_dark_Gray.bmp"));
                dm.bgImg = bg;

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadfromtxt.addActionListener(actionEvent -> {
            File fileLoad = new File("Mapa_MD_no_terrain_low_res_dark_Gray.txt");
            darker.setEnabled(true);
            lighter.setEnabled(true);
            blackandwhite.setEnabled(true);
            prog.setEnabled(true);
            wartosc.setEnabled(true);
            filtr.setEnabled(true);
            filtr1.setEnabled(true);
            filtr2.setEnabled(true);
            filtr3.setEnabled(true);
            dylatacja.setEnabled(true);
            erozja.setEnabled(true);
            otwarcie.setEnabled(true);
            zamknięcie.setEnabled(true);
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileLoad));
                String buffer = br.readLine();

                int j = 0;
                BufferedImage img = new BufferedImage(600, 330, BufferedImage.TYPE_INT_RGB);

                while (buffer != null) {

                    String[] bufferSpli = buffer.split("\t");
                    for (int i = 0; i < bufferSpli.length; i++) {
                        Color c = new Color(Integer.parseInt(bufferSpli[i]), Integer.parseInt(bufferSpli[i]), Integer.parseInt(bufferSpli[i]));

                        img.setRGB(i, j, c.getRGB());
                    }
                    j++;
                    buffer = br.readLine();
                }
                dm.bgImg = img;

                canvas.repaint();
                br.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        lighter.addActionListener(actionEvent -> {
            int factor = dm.value = Integer.parseInt(wartosc.getText());

            dm.bgImg = f.rozjasnij(dm.bgImg, factor);
            canvas.repaint();


        });

        darker.addActionListener(actionEvent -> {

            int factor = dm.value = Integer.parseInt(wartosc.getText());


            dm.bgImg = f.przyciemnij(dm.bgImg, factor);
            canvas.repaint();

        });

        blackandwhite.addActionListener(actionEvent -> {

            dm.progg = Integer.parseInt(prog.getText());

            dm.bgImg = f.binaryzacja(dm.bgImg, dm.progg);
            canvas.repaint();


        });


        filtr.addActionListener(actionEvent -> {

            int[][] macierzkonwolucja = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
            dm.bgImg = f.filtr(macierzkonwolucja, dm.bgImg);
            canvas.repaint();
        });

        filtr1.addActionListener(actionEvent -> {
            int[][] macierzdolnoprzepustowy = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
            dm.bgImg = f.filtr(macierzdolnoprzepustowy, dm.bgImg);
            canvas.repaint();

        });
        filtr2.addActionListener(actionEvent -> {

            int[][] macierzgornoprzepustowy = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
            dm.bgImg = f.filtr(macierzgornoprzepustowy, dm.bgImg);
            canvas.repaint();
        });
        filtr3.addActionListener(actionEvent -> {

            int[][] macierzGauss = {{1, 4, 1}, {4, 32, 4}, {1, 4, 1}};

            dm.bgImg = f.filtr(macierzGauss, dm.bgImg);
            canvas.repaint();
        });


        dylatacja.addActionListener(actionEvent -> {

            dm.bgImg = f.dylatacja(dm.bgImg);
            canvas.repaint();
        });

        erozja.addActionListener(actionEvent -> {

            dm.bgImg = f.erozja(dm.bgImg);
            canvas.repaint();
        });

        otwarcie.addActionListener(actionEvent -> {

            dm.bgImg = f.otwarcie_morf(dm.bgImg);
            canvas.repaint();
        });

        zamknięcie.addActionListener(actionEvent -> {

            dm.bgImg = f.zamkniecie_morf(dm.bgImg);
            canvas.repaint();
        });


        //AUTOMAT


        automat.addActionListener(e -> {
            int szerokosc, wysokosc, skala = 5;


            JTextField rozmiar = new JTextField();
            JTextField literacji = new JTextField();
            JTextField scale = new JTextField();
            JTextField rozmiarkomorki = new JTextField();
            JComboBox rodzaj = new JComboBox(new String[]{"30", "60", "90", "120", "225"});
            JComboBox warunek = new JComboBox(new String[]{"Periodyczne", "Jedynki"});

            Object[] punkty = {"Podaj rozmiar", rozmiar, "Podaj liczbe iteracji",
                    literacji, "Podaj skale", scale, "Podaj rozmiar komorki", rozmiarkomorki,
                    "Wybierz zasade", rodzaj, "Wybierz warunek", warunek};
            JOptionPane.showConfirmDialog(null, punkty, "Wybierz warunki", JOptionPane.OK_CANCEL_OPTION);
            if (rozmiar.getText().equals("") || literacji.getText().equals("") || scale.getText().equals("") || rozmiarkomorki.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nie podano danych",
                        null, JOptionPane.ERROR_MESSAGE);
            } else {
                int size = 0;
                int iteration = 0;
                int rozmiarkom = 0;
                int type = Integer.parseInt(rodzaj.getSelectedItem().toString());
                int condition;
                if (warunek.getSelectedItem().toString().equals("Periodyczne"))
                    condition = 0;
                else condition = 1;
                try {
                    size = Integer.parseInt(rozmiar.getText());
                    iteration = Integer.parseInt(literacji.getText());
                    skala = Integer.parseInt(scale.getText());
                    rozmiarkom = Integer.parseInt(rozmiarkomorki.getText());


                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Proszę podać wartość numeryczną",
                            null, JOptionPane.ERROR_MESSAGE);

                }
                dm.automacierz = auto.automat(size, iteration, type, condition, rozmiarkom);

            }

            szerokosc = dm.automacierz[0].length * skala * dm.automacierz[0][0].rozmiar;
            wysokosc = dm.automacierz.length * skala * dm.automacierz[0][0].rozmiar;
            BufferedImage img = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = img.createGraphics();
            g.setPaint(Color.BLACK);
            g.fillRect(0, 0, szerokosc, wysokosc);
            for (int szer = 0; szer < dm.automacierz.length; szer++) {
                for (int wys = 0; wys < dm.automacierz[szer].length; wys++) {
                    if (dm.automacierz[szer][wys].wartosc == 1) {
                        g.setPaint(Color.PINK);
                        g.fillRect(wys * skala * dm.automacierz[0][0].rozmiar, szer * skala * dm.automacierz[0][0].rozmiar, dm.automacierz[0][0].rozmiar * skala, dm.automacierz[0][0].rozmiar * skala);
                    }
                }
            }


            dm.bgImg = img;
            canvas.repaint();

        });

        ActionListener timerFunction = evt -> {
            if (dm.iteracjeteraz < dm.listamacierzy.size()) {

                BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = img.createGraphics();
                g.setPaint(Color.BLACK);
                g.fillRect(0, 0, dm.bgImg.getWidth(), dm.bgImg.getHeight());
                dm.iteracjeteraz++;
                for (int i = dm.s - 1; i < dm.iteracjeteraz; i++) {
                    for (int y = 0; y < dm.listamacierzy.get(i).length; y++) {
                        for (int x = 0; x < dm.listamacierzy.get(i)[y].length; x++) {

                            if (i != 0 && dm.listamacierzy.get(i - 1)[y][x].wartosc == 1) {
                                g.setPaint(Color.BLACK);
                                g.fillRect(dm.listamacierzy.get(i - 1)[y][x].getX() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar,
                                        dm.listamacierzy.get(i - 1)[y][x].getY() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar, dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6, dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6);
                            }
                            if (dm.listamacierzy.get(i)[y][x].wartosc == 1) {
                                g.setPaint(Color.PINK);
                                g.fillRect(dm.listamacierzy.get(i)[y][x].getX() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar, dm.listamacierzy.get(i)[y][x].getY() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar, dm.listamacierzy.get(i)[y][x].rozmiar * 6, dm.listamacierzy.get(i)[y][x].rozmiar * 6);
                            }

                        }

                    }
                    dm.licznik = i;

                }
                dm.bgImg = img;
                canvas.repaint();

            }

        };


        ActionListener timerFunction2 = evt -> {
            if (dm.iteracjeteraz < dm.listamacierzy.size()) {
                BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                img = dm.bgImg;
                Graphics2D g = img.createGraphics();

                dm.iteracjeteraz++;
                for (int i = dm.licznik; i < dm.iteracjeteraz; i++) {
                    for (int y = 0; y < dm.listamacierzy.get(i).length; y++) {
                        for (int x = 0; x < dm.listamacierzy.get(i)[y].length; x++) {

                            if (i != 0 && dm.listamacierzy.get(i - 1)[y][x].wartosc == 1) {
                                g.setPaint(Color.BLACK);
                                g.fillRect(dm.listamacierzy.get(i - 1)[y][x].getX() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar, dm.listamacierzy.get(i - 1)[y][x].getY() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar, dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6, dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6);
                            }
                            if (dm.listamacierzy.get(i)[y][x].wartosc == 1) {
                                g.setPaint(Color.PINK);
                                g.fillRect(dm.listamacierzy.get(i)[y][x].getX() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar, dm.listamacierzy.get(i)[y][x].getY() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar, dm.listamacierzy.get(i)[y][x].rozmiar * 6, dm.listamacierzy.get(i)[y][x].rozmiar * 6);
                            }

                        }

                    }

                }
                dm.bgImg = img;
                canvas.repaint();

            }

        };


        stop.setEnabled(false);
        wyczysc.setEnabled(false);


        start.addActionListener(e -> {

            if (dm.listamacierzy.size() != 0) {
                switch (e.getActionCommand()) {
                    case "Rozpocznij":
                        dm.s = dm.listamacierzy.size();
                        timer = new Timer(t, timerFunction);

                        gra.gra_w_zycie(dm.listamacierzy.get(dm.s - 1), 400);
                        timer.start();
                        dm.running = true;
                        wyczysc.setEnabled(false);
                        start.setText("Start");
                        start.setEnabled(false);
                        stop.setEnabled(true);
                        rozmiarrkom.setEnabled(false);
                        stany.setEnabled(false);
                        czas.setEnabled(false);

                        break;
                    case "Start":
                        if (!dm.running) {
                            timer2 = new Timer(t, timerFunction2);

                            timer2.start();
                            cos++;
                            dm.running = true;
                            wyczysc.setEnabled(false);
                            start.setEnabled(false);
                            stop.setEnabled(true);
                        } else
                            dm.running = true;
                        break;
                    default:
                        break;


                }

            } else {
                JOptionPane.showMessageDialog(null, "Należy najpierw wybrać rozmiar komórki i stan początkowy",
                        null, JOptionPane.ERROR_MESSAGE);
            }

        });

        stop.addActionListener(e -> {
            wyczysc.setEnabled(true);
            start.setEnabled(true);

            dm.running = false;
            if (cos != 0)
                timer2.stop();
            else
                timer.stop();


        });


        wyczysc.addActionListener(e -> {
            stop.setEnabled(false);
            rozmiarrkom.setEnabled(true);
            stany.setEnabled(true);
            czas.setEnabled(true);
            start.setText("Rozpocznij");
            dm.listamacierzy.clear();
            dm.iteracjeteraz = 0;
            cos = 0;
            dm.licznik = 0;
            dm.licznik_iteracji = 0;
            BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = img.createGraphics();
            g.setPaint(Color.BLACK);
            g.fillRect(0, 0, dm.bgImg.getWidth(), dm.bgImg.getHeight());
            dm.bgImg = img;
            canvas.repaint();

        });


        stany.addActionListener(e -> {
            if (rozmiarrkom.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nie podano rozmiaru komórki",
                        null, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    size = Integer.parseInt(rozmiarrkom.getText());


                    Komorka[][] plansza = new Komorka[100][100];
                    for (int j = 0; j < plansza.length; j++) {
                        for (int i = 0; i < plansza[j].length; i++) {
                            plansza[j][i] = new Komorka(size, 0);
                            plansza[j][i].setX(j);
                            plansza[j][i].setY(i);

                        }

                    }
                    dm.automacierz = plansza;
                    int szerokosc = dm.automacierz[0].length * dm.automacierz[0][0].rozmiar * 6;
                    int wysokosc = dm.automacierz.length * dm.automacierz[0][0].rozmiar * 6;
                    BufferedImage img = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = img.createGraphics();
                    g.setPaint(Color.BLACK);
                    g.fillRect(0, 0, szerokosc, wysokosc);
                    switch (stany.getSelectedItem().toString()) {
                        case "Niezmienny":
                            plansza[49][49].wartosc = 1;
                            plansza[50][49].wartosc = 1;
                            plansza[48][50].wartosc = 1;
                            plansza[51][50].wartosc = 1;
                            plansza[49][51].wartosc = 1;
                            plansza[50][51].wartosc = 1;
                            canvas.repaint();

                            break;
                        case "Glider":
                            plansza[49][49].wartosc = 1;
                            plansza[50][49].wartosc = 1;
                            plansza[48][50].wartosc = 1;
                            plansza[49][50].wartosc = 1;
                            plansza[50][51].wartosc = 1;
                            canvas.repaint();

                            break;
                        case "Losowy":

                            Random generatorwspx = new Random();
                            Random generatorwspy = new Random();
                            Random generatorliczbypkt = new Random();
                            int d = generatorliczbypkt.nextInt(700);


                            for (int i = 0; i < d; i++) {
                                int x = generatorwspx.nextInt(80);
                                int y = generatorwspy.nextInt(80);
                                plansza[x][y].wartosc = 1;


                            }
                            canvas.repaint();

                            break;
                        case "Oscylator":
                            plansza[50][51].wartosc = 1;
                            plansza[50][50].wartosc = 1;
                            plansza[50][49].wartosc = 1;

                            canvas.repaint();

                            break;
                        case "Ręczna definicja":

                            canvas.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    int x = e.getX() / (size * 6);
                                    int y = e.getY() / (size * 6);
                                    plansza[x][y].wartosc = 1;
                                    g.setPaint(Color.pink);
                                    g.fillRect(x * size * 6, y * size * 6, size * 6, size * 6);
                                    canvas.repaint();
                                }

                                @Override
                                public void mousePressed(MouseEvent e) {

                                }

                                @Override
                                public void mouseReleased(MouseEvent e) {

                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {

                                }

                                @Override
                                public void mouseExited(MouseEvent e) {

                                }
                            });


                            canvas.repaint();

                            break;


                    }

                    dm.listamacierzy.add(plansza);
                    for (int i = 0; i < dm.listamacierzy.size(); i++) {
                        for (int y = 0; y < dm.listamacierzy.get(i).length; y++) {
                            for (int x = 0; x < dm.listamacierzy.get(i)[y].length; x++) {
                                if (i != 0 && dm.listamacierzy.get(i - 1)[y][x].wartosc == 1) {
                                    g.setPaint(Color.BLACK);
                                    g.fillRect(dm.listamacierzy.get(i - 1)[y][x].getX() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar,
                                            dm.listamacierzy.get(i - 1)[y][x].getY() * 6 * dm.listamacierzy.get(i - 1)[y][x].rozmiar,
                                            dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6, dm.listamacierzy.get(i - 1)[y][x].rozmiar * 6);
                                }
                                if (dm.listamacierzy.get(i)[y][x].wartosc == 1) {
                                    g.setPaint(Color.PINK);
                                    g.fillRect(dm.listamacierzy.get(i)[y][x].getX() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar,
                                            dm.listamacierzy.get(i)[y][x].getY() * 6 * dm.listamacierzy.get(i)[y][x].rozmiar, dm.listamacierzy.get(i)[y][x].rozmiar * 6,
                                            dm.listamacierzy.get(i)[y][x].rozmiar * 6);
                                }
                            }
                        }
                    }

                    dm.bgImg = img;
                    canvas.repaint();


                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Proszę podać wartość numeryczną",
                            null, JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        czas.addActionListener(e -> {
            switch (czas.getSelectedItem().toString()) {
                case "1/8 sekundy":
                    t = 125;

                    break;
                case "1/4 sekundy":
                    t = 250;

                    break;
                case "1/2 sekundy":
                    t = 500;

                    break;
                case "1 sekunda":
                    t = 1000;

                    break;
                case "2 sekundy":
                    t = 2000;


                    break;


            }
        });


        // POZAR LASU


        wiatr.addActionListener(e -> {
            if (silawiatru.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nie podano siły wiatru",
                        null, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int moc = Integer.parseInt(silawiatru.getText());
                    if (moc > 100 || moc < 0) {
                        JOptionPane.showMessageDialog(null, "Siła wiatru musi być liczbą z zakresu 0-100",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {

                        dm.jakmocnowiejewiatr = moc;

                        switch (wiatr.getSelectedItem().toString()) {
                            case "północny":
                                dm.x = 0;
                                dm.y = 1;
                                break;
                            case "północno-wschodni":
                                dm.x = -1;
                                dm.y = 1;
                                break;
                            case "wschodni":
                                dm.x = -1;
                                dm.y = 0;
                                break;
                            case "południowo-wschodni":
                                dm.x = -1;
                                dm.y = -1;
                                break;
                            case "południowy":
                                dm.x = 0;
                                dm.y = -1;
                                break;
                            case "południowo-zachodni":
                                dm.x = 1;
                                dm.y = -1;
                                break;
                            case "zachodni":
                                dm.x = 1;
                                dm.y = 0;
                                break;
                            case "północno-zachodni":
                                dm.x = 1;
                                dm.y = 1;
                                break;


                        }
                    }
                } catch (NumberFormatException g) {
                    JOptionPane.showMessageDialog(null, "Proszę podać wartość numeryczną",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        czas_pozar.addActionListener(e -> {
            switch (czas_pozar.getSelectedItem().toString()) {
                case "1/8 sekundy":
                    t1 = 125;

                    break;
                case "1/4 sekundy":
                    t1 = 250;

                    break;
                case "1/2 sekundy":
                    t1 = 500;

                    break;
                case "1 sekunda":
                    t1 = 1000;

                    break;
                case "2 sekundy":
                    t1 = 2000;


                    break;


            }
        });


        pausa.addActionListener(e -> {
            nowa_gra.setEnabled(true);
            palisie.setEnabled(true);

            dm.running2 = false;
            timer3.stop();


        });
        ActionListener timerFunction4 = e -> {
            globalTimer++;

            deszcz.setVisible(true);
            progressBar.setValue(globalTimer * 10);
            progressBar.setVisible(true);
            if (globalTimer == limitTimer) {
                timer4.stop();
                deszcz.setVisible(false);
                progressBar.setVisible(false);


            }
        };

        nowa_gra.addActionListener(e -> {
            pausa.setEnabled(false);
            strazak.setEnabled(true);
            palisie.setEnabled(true);
            palisie.setText("Start");
            dm.listamacierzy2.clear();
            dm.iteracjeteraz2 = 0;
            cos2 = 0;
            dm.licznik2 = 0;
            dm.licznik_iteracji2 = 0;
            dm.przegrana = false;
            dm.wygrana = false;
            dm.zniszczone = false;
            timer4 = new Timer(1000, timerFunction4);
            timer4.start();

            dm.bgImg = dm.dopozaru;
            canvas.repaint();

        });


        palisie.setEnabled(false);
        strazak.setEnabled(false);
        nowa_gra.setEnabled(false);
        pausa.setEnabled(false);


        pozarlasuobrazek.addActionListener(actionEvent -> {
            if (dm.listamacierzy2.size() == 0) {
                palisie.setEnabled(true);
                strazak.setEnabled(true);


                try {
                    BufferedImage bg = ImageIO.read(new File("Mapa_MD_no_terrain_low_res_dark_Gray.bmp"));
                    dm.bgImg = bg;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Komorka[][] plansza = new Komorka[dm.bgImg.getWidth()][dm.bgImg.getHeight()];
                for (int j = 0; j < plansza.length; j++) {
                    for (int i = 0; i < plansza[j].length; i++) {
                        plansza[j][i] = new Komorka(size, 0);
                        plansza[j][i].setX(j);
                        plansza[j][i].setY(i);

                    }

                }


                BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = img.createGraphics();

                dm.bgImg = f.binaryzacja(dm.bgImg, 150);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);
                dm.bgImg = f.dylatacja(dm.bgImg);


                dm.bgImg = f.erozja(dm.bgImg);
                dm.bgImg = f.erozja(dm.bgImg);
                for (int wi = 0; wi < dm.bgImg.getWidth(); wi++) {
                    for (int hi = 0; hi < dm.bgImg.getHeight(); hi++) {

                        Color c = new Color(dm.bgImg.getRGB(wi, hi));

                        int r = c.getRed();
                        if (r == 0) {
                            g.setPaint(Color.BLUE);
                            g.fillRect(wi, hi, 1, 1);
                            plansza[wi][hi].wartosc = 0;


                        } else {

                            g.setPaint(Color.GREEN);
                            g.fillRect(wi, hi, 1, 1);
                            plansza[wi][hi].wartosc = 1;

                        }
                    }
                }

                dm.automacierz2 = plansza;
                dm.dopozaru = img;
                dm.bgImg = img;
                canvas.repaint();

                int n = JOptionPane.showOptionDialog(null, "Witaj w grze POŻAR LASU!\n" + "Gra polega na tym, żeby ugasić pożar.\n" +
                                "Zanim zaczniesz, wybierz rodzaj i siłę wiatru oraz prędkość gry.\n Możesz także dodać strażaków (kliknij 'Dodaj strażaka', \n" +
                                "a następnie klikaj na obrazku na zielony teren). \nZanim klikniesz 'Start' będziesz miał również chwilę " +
                                "na znalezienie przycisku 'Deszcz!'. \nJest on ukryty gdzieś w aplikacji. \nJeśli uda Ci się go znależć i kliknąć przed upływem " +
                                "czasu,\n z pewnością uratujesz swój las. \nPowodzenia!",
                        "ZASADY GRY", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (n == JOptionPane.OK_OPTION || n == JOptionPane.CANCEL_OPTION) {


                    timer4 = new Timer(1000, timerFunction4);
                    timer4.start();


                    pozarlasuobrazek.setEnabled(false);
                    buttonPanel3.add(editorScrollPane, BorderLayout.CENTER);

                }


            }


        });
        deszcz.setVisible(false);


        ActionListener timerFunction3 = evt -> {
            if (dm.iteracjeteraz2 < dm.listamacierzy2.size()) {

                BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = img.createGraphics();
                dm.iteracjeteraz2++;
                int i = dm.iteracjeteraz2 - 1;

                for (int y = 0; y < dm.listamacierzy2.get(i).length; y++) {
                    for (int x = 0; x < dm.listamacierzy2.get(i)[y].length; x++) {


                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 0) {
                            g.setPaint(Color.BLUE);
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                        }
                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 1) {
                            g.setPaint(Color.GREEN);
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                            if (dm.iteracjeteraz2 == dm.listamacierzy2.size()) { //nwm czy -1
                                dm.wygrana = true;
                                pausa.setEnabled(false);
                                dm.deszczyk = false;

                            }
                        }
                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 2) {
                            g.setPaint(Color.red);
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                        }
                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 3) {
                            g.setPaint(Color.YELLOW);
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                            if (dm.iteracjeteraz2 == dm.listamacierzy2.size()) { //nwm czy -1
                                dm.przegrana = true;
                                pausa.setEnabled(false);
                            }
                        }

                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 4) {
                            g.setPaint(Color.white);
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                        }
                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 5) {
                            g.setPaint(new Color(79, 38, 229));
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                        }
                        if (dm.listamacierzy2.get(i)[y][x].wartosc == 6) {
                            g.setPaint(new Color(120, 150, 229));
                            g.fillRect(dm.listamacierzy2.get(i)[y][x].getX(), dm.listamacierzy2.get(i)[y][x].getY(), 1, 1);
                        }
                        if (dm.iteracjeteraz2 == dm.listamacierzy2.size() && dm.wygrana) {
                            if (dm.listamacierzy2.get(i)[y][x].wartosc == 2) {//nwm czy -1
                                dm.wygrana = false;
                                dm.zniszczone = true;
                                pausa.setEnabled(false);
                            }

                        }


                    }


                }
                if (dm.przegrana) {
                    nowa_gra.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "PRZEGRANA! Las spłonął! :(",
                            null, JOptionPane.INFORMATION_MESSAGE);
                }
                if (dm.wygrana) {
                    nowa_gra.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "WYGRANA! Las ocalał! :)",
                            null, JOptionPane.INFORMATION_MESSAGE);
                }
                if (dm.zniszczone) {
                    nowa_gra.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "MIAŁEŚ SZCZĘŚCIE! Część lasu ocalała! :)",
                            null, JOptionPane.INFORMATION_MESSAGE);

                }
                dm.bgImg = img;
                canvas.repaint();

            }

        };


        palisie.addActionListener(e -> {
            strazak.setEnabled(false);

            switch (e.getActionCommand()) {
                case "Start":


                    if (dm.x != 7 && dm.y != 7) {


                        Random generatorwspx = new Random();
                        Random generatorwspy = new Random();
                        Random generatorliczbypkt = new Random();
                        int d = generatorliczbypkt.nextInt(800);


                        for (int i = 0; i < d; i++) {
                            int x = generatorwspx.nextInt(dm.bgImg.getWidth());
                            int y = generatorwspy.nextInt(dm.bgImg.getHeight());
                            if (dm.automacierz2[x][y].wartosc == 1)
                                dm.automacierz2[x][y].wartosc = 2;


                        }
                        timer3 = new Timer(t1, timerFunction3);
                        dm.czysazielone = true;
                        pl.pozar_lasu_funkcja(dm.automacierz2, 200);


                        timer3.start();
                        dm.running2 = true;
                        nowa_gra.setEnabled(false);
                        palisie.setText("Wznów");
                        palisie.setEnabled(false);
                        pausa.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Proszę podać siłę wiatru i wybrać jego rodzaj",
                                null, JOptionPane.ERROR_MESSAGE);
                    }


                    break;
                case "Wznów":
                    if (!dm.running2) {

                        timer3.start();

                        cos2++;
                        dm.running2 = true;
                        nowa_gra.setEnabled(false);
                        palisie.setEnabled(false);
                        pausa.setEnabled(true);
                    } else
                        dm.running2 = true;
                    break;
                default:
                    break;


            }


        });

        strazak.addActionListener(e -> canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BufferedImage img = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                img = dm.bgImg;
                Graphics2D g = img.createGraphics();
                int x = e.getX();
                int y = e.getY();
                for (int k = -4; k <= 4; k++) {
                    for (int l = -4; l <= 4; l++) {
                        if (dm.automacierz2[x + k][y + l].wartosc == 1) {

                            dm.automacierz2[x + k][y + l].wartosc = 4;

                            g.setPaint(Color.white);
                            g.fillRect(x, y, 8, 8);
                        }
                    }
                }
                dm.bgImg = img;
                canvas.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }));

        deszcz.addActionListener(e -> dm.deszczyk = true);

        canvas = new JCanvasPanel(dm);
        buttonPanel = new JPanel();
        buttonPanel1 = new JPanel();
        buttonPanel2 = new JPanel();
        buttonPanel3 = new JPanel();


        tp = new JTabbedPane();

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();


        p1.setBackground(Color.pink);
        p2.setBackground(Color.pink);
        p3.setBackground(Color.pink);
        p4.setBackground(Color.pink);


        tp.add("Obrazek ", p1);
        tp.add("Automat ", p2);
        tp.add("Gra w życie ", p3);
        tp.add("Pożar lasu", p4);
        p1.add(BorderLayout.EAST, buttonPanel);
        p2.add(BorderLayout.CENTER, buttonPanel1);
        p3.add(BorderLayout.WEST, buttonPanel2);
        p4.add(BorderLayout.CENTER, buttonPanel3);


        buttonPanel.add(loadphoto);
        buttonPanel.add(loadfromtxt);
        buttonPanel.add(lighter);
        buttonPanel.add(darker);
        buttonPanel.add(wartosc);
        buttonPanel.add(blackandwhite);
        buttonPanel.add(prog);
        buttonPanel.add(filtr);
        buttonPanel.add(filtr1);
        buttonPanel.add(filtr2);
        buttonPanel.add(filtr3);
        buttonPanel.add(dylatacja);
        buttonPanel.add(erozja);
        buttonPanel.add(otwarcie);
        buttonPanel.add(zamknięcie);

        buttonPanel1.add(automat);

        buttonPanel2.add(start);
        buttonPanel2.add(stop);
        buttonPanel2.add(wyczysc);
        buttonPanel2.add(rozmiarrkom);
        buttonPanel2.add(stany);
        buttonPanel2.add(czas);

        buttonPanel3.add(pozarlasuobrazek);
        buttonPanel3.add(wiatr);
        buttonPanel3.add(silawiatru);
        buttonPanel3.add(czas_pozar);
        buttonPanel3.add(strazak);
        buttonPanel3.add(palisie);
        buttonPanel3.add(pausa);
        buttonPanel3.add(nowa_gra);
        buttonPanel3.add(progressBar, BorderLayout.CENTER);


        buttonPanel2.add(deszcz);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        buttonPanel.setLayout(new GridLayout(9, 2));
        buttonPanel2.setLayout(new GridLayout(3, 1));
        buttonPanel3.setLayout(new GridLayout(6, 3));


        mainPanel.add(BorderLayout.CENTER, canvas);
        mainPanel.add(BorderLayout.EAST, tp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(1200, 900));
        this.setLocationRelativeTo(null);


    }


    public static void main(String[] args) {

        gui mw = new gui("PROJEKT I");
        mw.setVisible(true);


        mw.canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
