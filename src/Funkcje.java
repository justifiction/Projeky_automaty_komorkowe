
import java.awt.*;
import java.awt.image.BufferedImage;

public class Funkcje {
    DataMenager dm;

    public Funkcje(DataMenager dataMenager) {
        this.dm = dataMenager;
    }

    public BufferedImage rozjasnij(BufferedImage img, int factor) {

        BufferedImage picture2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (
                int wi = 0; wi < img.getWidth(); wi++) {
            for (int hi = 0; hi < img.getHeight(); hi++) {

                Color c = new Color(img.getRGB(wi, hi));

                int r = c.getRed() + factor;
                int b = c.getBlue() + factor;
                int g = c.getGreen() + factor;
                if (r >= 256) {
                    r = 255;
                } else if (r < 0) {
                    r = 0;
                }

                if (g >= 256) {
                    g = 255;
                } else if (g < 0) {
                    g = 0;
                }
                if (b >= 256) {
                    b = 255;
                } else if (b < 0) {
                    b = 0;
                }
                picture2.setRGB(wi, hi, new Color(r, g, b).getRGB());


            }
        }
        return picture2;
    }

    public BufferedImage przyciemnij(BufferedImage img, int factor) {
        BufferedImage picture2 = new BufferedImage(dm.bgImg.getWidth(), dm.bgImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int wi = 0; wi < dm.bgImg.getWidth(); wi++) {
            for (int hi = 0; hi < dm.bgImg.getHeight(); hi++) {

                Color c = new Color(dm.bgImg.getRGB(wi, hi));

                int r = c.getRed() - factor;
                int b = c.getBlue() - factor;
                int g = c.getGreen() - factor;
                if (r >= 256) {
                    r = 255;
                } else if (r < 0) {
                    r = 0;
                }

                if (g >= 256) {
                    g = 255;
                } else if (g < 0) {
                    g = 0;
                }
                if (b >= 256) {
                    b = 255;
                } else if (b < 0) {
                    b = 0;
                }
                picture2.setRGB(wi, hi, new Color(r, g, b).getRGB());


            }
        }
        return picture2;
    }

    public BufferedImage binaryzacja(BufferedImage image, int prog) {
        BufferedImage picture2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int wi = 0; wi < image.getWidth(); wi++) {
            for (int hi = 0; hi < image.getHeight(); hi++) {

                Color c = new Color(image.getRGB(wi, hi));


                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();


                if (r > prog || g > prog || b > prog) {
                    r = 255;
                    g = 255;
                    b = 255;
                } else {
                    r = 0;
                    g = 0;
                    b = 0;
                }


                picture2.setRGB(wi, hi, new Color(r, g, b).getRGB());


            }
        }
        return picture2;

    }

    public BufferedImage filtr(int[][] macierz, BufferedImage image) {
        BufferedImage picture2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


        float[][] obrazek = new float[image.getWidth()][image.getHeight()];
        float[][] kopia = new float[image.getWidth()][image.getHeight()];

        float suma = 0.0f;
        float sumamacierzyflitr = 0.0f;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++)
                sumamacierzyflitr += macierz[j][i];
        }
        for (int wi = 0; wi < image.getWidth(); wi++) {
            for (int hi = 0; hi < image.getHeight(); hi++) {
                Color c = new Color(image.getRGB(wi, hi));
                float r = c.getRed();
                obrazek[wi][hi] = r;


            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        suma += obrazek[wi + i][hi + j] * macierz[i + 1][j + 1];


                    }
                }
                if (sumamacierzyflitr != 0)
                    suma = suma / sumamacierzyflitr;

                if (suma > 255)
                    suma = 255;
                else if (suma < 0)
                    suma = 0;
                kopia[wi][hi] = suma;

                suma = 0;
            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {

                picture2.setRGB(wi, hi, new Color((int) kopia[wi][hi], (int) kopia[wi][hi], (int) kopia[wi][hi]).getRGB());
            }
        }
        return picture2;

    }

    public BufferedImage dylatacja(BufferedImage image) {
        BufferedImage picture2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


        float[][] obrazek = new float[image.getWidth()][image.getHeight()];
        float[][] kopia = new float[image.getWidth()][image.getHeight()];

        for (int wi = 0; wi < image.getWidth(); wi++) {
            for (int hi = 0; hi < image.getHeight(); hi++) {
                Color c = new Color(image.getRGB(wi, hi));
                float r = c.getRed();
                obrazek[wi][hi] = r;


            }
        }

        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                kopia[wi][hi] = 255f;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (obrazek[wi + i][hi + j] == 0)
                            kopia[wi][hi] = 0;


                    }
                }

            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                picture2.setRGB(wi, hi, new Color((int) kopia[wi][hi], (int) kopia[wi][hi], (int) kopia[wi][hi]).getRGB());
            }
        }
        return picture2;
    }

    public BufferedImage erozja(BufferedImage image) {

        BufferedImage picture2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


        float[][] obrazek = new float[image.getWidth()][image.getHeight()];
        float[][] kopia = new float[image.getWidth()][image.getHeight()];

        for (int wi = 0; wi < image.getWidth(); wi++) {
            for (int hi = 0; hi < image.getHeight(); hi++) {
                Color c = new Color(image.getRGB(wi, hi));
                float r = c.getRed();
                obrazek[wi][hi] = r;


            }
        }


        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                kopia[wi][hi] = obrazek[wi][hi];
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (obrazek[wi + i][hi + j] == 255f)
                            kopia[wi][hi] = 255f;

                    }

                }

            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                picture2.setRGB(wi, hi, new Color((int) kopia[wi][hi], (int) kopia[wi][hi], (int) kopia[wi][hi]).getRGB());
            }
        }

        return picture2;
    }


    public BufferedImage otwarcie_morf(BufferedImage image) {
        BufferedImage picture2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);


        float[][] obrazek = new float[image.getWidth()][image.getHeight()];
        float[][] kopia = new float[image.getWidth()][image.getHeight()];
        float[][] kopia2 = new float[image.getWidth()][image.getHeight()];


        for (int wi = 0; wi < image.getWidth(); wi++) {
            for (int hi = 0; hi < image.getHeight(); hi++) {
                Color c = new Color(image.getRGB(wi, hi));
                float r = c.getRed();
                obrazek[wi][hi] = r;


            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                kopia[wi][hi] = obrazek[wi][hi];
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (obrazek[wi + i][hi + j] == 0)
                            kopia[wi][hi] = 0;

                    }
                }

            }
        }
        for (int i = 0; i < kopia.length; i++)
            for (int j = 0; j < kopia[i].length; j++) {
                kopia2[i][j] = kopia[i][j];
            }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                kopia[wi][hi] = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (obrazek[wi + i][hi + j] == 255f)
                            kopia[wi][hi] = 255f;


                    }
                }

            }
        }
        for (int wi = 1; wi < image.getWidth() - 1; wi++) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {
                picture2.setRGB(wi, hi, new Color((int) kopia[wi][hi], (int) kopia[wi][hi], (int) kopia[wi][hi]).getRGB());
            }
        }
        image = picture2;
        return image;
    }

    public BufferedImage zamkniecie_morf(BufferedImage img) {
        BufferedImage picture2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);


        float[][] obrazek = new float[img.getWidth()][img.getHeight()];
        float[][] kopia = new float[img.getWidth()][img.getHeight()];
        float[][] kopia2 = new float[img.getWidth()][img.getHeight()];

        for (int wi = 0; wi < img.getWidth(); wi++) {
            for (int hi = 0; hi < img.getHeight(); hi++) {
                Color c = new Color(img.getRGB(wi, hi));
                float r = c.getRed();
                obrazek[wi][hi] = r;


            }
        }
        for (int wi = 1; wi < img.getWidth() - 1; wi++) {
            for (int hi = 1; hi < img.getHeight() - 1; hi++) {
                kopia[wi][hi] = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (obrazek[wi + i][hi + j] == 255f)
                            kopia[wi][hi] = 255f;


                    }
                }

            }
        }
        for (int i = 0; i < kopia.length; i++)
            for (int j = 0; j < kopia[i].length; j++) {
                kopia2[i][j] = kopia[i][j];
            }

        for (int wi = 1; wi < img.getWidth() - 1; wi++) {
            for (int hi = 1; hi < img.getHeight() - 1; hi++) {
                kopia[wi][hi] = kopia2[wi][hi];
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        if (kopia2[wi + i][hi + j] == 0)
                            kopia[wi][hi] = 0;

                    }
                }

            }
        }
        for (int wi = 1; wi < img.getWidth() - 1; wi++) {
            for (int hi = 1; hi < img.getHeight() - 1; hi++) {
                picture2.setRGB(wi, hi, new Color((int) kopia[wi][hi], (int) kopia[wi][hi], (int) kopia[wi][hi]).getRGB());
            }
        }
        img = picture2;
        return img;
    }


}
