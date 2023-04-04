package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utils;

public class FiltrageLinaireGlobal {

    /**
     *  Methode réalisant le filtrage passe-bas de l’image en passant par sa transformée de Fourier
     *  et en utilisant une fenêtre de forme circulaire de rayon égal à la fréquence de coupure.
     * @param image
     * @param frequenceCoupure
     * @return
     */
    public static int[][] filtrePasseBasIdeal(int[][] image,int frequenceCoupure) {
        int hauteurImage = image.length;
        int largeurImage = image[0].length;
        int[][] resultatFiltre = new int[hauteurImage][largeurImage];

        MatriceComplexe fourier = Fourier.Fourier2D(Utils.intToDouble(image));

        for (int i = 0; i < hauteurImage; i++) {
            for (int j = 0; j < largeurImage; j++) {
                double h = Math.sqrt(
                        Math.pow(i,2) + Math.pow(j,2)
                );

                if (h <= frequenceCoupure) {
                    Complexe c = fourier.get(i,j);
                    c.multiplie(new Complexe(1,0));
                    fourier.set(i,j,c);
                } else {
                    fourier.set(i,j,new Complexe(0,0));
                }
            }
        }


        MatriceComplexe inverse = Fourier.InverseFourier2D(fourier);
        resultatFiltre = Utils.doubleToInt(inverse.getPartieReelle());


        return resultatFiltre;
    }

    /**
     *  Methode réalisant le filtrage passe-haut de l’image en passant par sa transformée de Fourier
     *  et en utilisant une fenêtre de forme circulaire de rayon égal à la fréquence de coupure.
     * @param image
     * @param frequenceCoupure
     * @return
     */
    public static int[][] filtrePasseHautIdeal(int[][] image,int frequenceCoupure){
        int hauteurImage = image.length;
        int largeurImage = image[0].length;
        int[][] resultatFiltre = new int[hauteurImage][largeurImage];

        MatriceComplexe fourier = Fourier.Fourier2D(Utils.intToDouble(image));

        for (int i = 0; i < hauteurImage; i++) {
            for (int j = 0; j < largeurImage; j++) {
                double h = Math.sqrt(
                        Math.pow(i,2) + Math.pow(j,2)
                );

                if (h >= frequenceCoupure) {
                    Complexe c = fourier.get(i,j);
                    c.multiplie(new Complexe(1,0));
                    fourier.set(i,j,c);
                } else {
                    fourier.set(i,j,new Complexe(0,0));
                }
            }
        }


        MatriceComplexe inverse = Fourier.InverseFourier2D(fourier);
        resultatFiltre = Utils.doubleToInt(inverse.getPartieReelle());


        return resultatFiltre;
    }

    /**
     * Methode réalisant le filtrage passe-bas de l’image en passant par sa transformée de Fourier
     *  et en utilisant la fonction de transfert de Butterworth correspondante.
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return
     */
    public static int[][] filtrePasseBasButterworth(int[][] image,int frequenceCoupure,int ordre){
        return null;
    }

    /**
     * Methode réalisant le filtrage passe-haut de l’image en passant par sa transformée de Fourier
     * et en utilisant la fonction de transfert de Butterworth correspondante.
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return
     */
    public static int[][] filtrePasseHautButterworth(int[][] image,int frequenceCoupure, int ordre){
        return null;
    }
}
