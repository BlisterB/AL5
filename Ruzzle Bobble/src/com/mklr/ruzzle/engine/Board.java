package com.mklr.ruzzle.engine;

import java.util.ArrayList;
import java.util.Random;

import com.mklr.collection.AbstractGrid;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.data.Letter;
import com.mklr.graphics.engine.Option;

/**
 * Créer le plateau nécessaire pour jouer.
 * La taille est fixée à 2 pour ce jeu.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Board extends AbstractGrid<Marble>{

    /**
     *  Langue du dictionnaire.
     */
    private String lang;

    /**
     *  Dictionnaire associé au plateau.
     */
    private RuzzleDictionary dico;


    /**
     *  Plateau associé aux options. Si celui ci est nul, alors un plateau
     *  aléatoire est généré. Sinon un plateau correspondant aux lettres
     *  donnés sera crée.
     */
    private Character[][] board = null;

    /**
     *  Option lié au plateau.
     *  Contient le tableau de caractère pour créer le plateau, un tableau
     *  des bonus, et enfin le dictionnaire ainsi que sa langue.
     */
    private Option boardOption = null;



    /**
     * Créer un plateau en fonction de son dictionnaire et sa langue.
     * @param lang
     * @param dico
     */
    public Board(String lang, RuzzleDictionary dico) {
        super(2);
        this.dico = dico;
        this.lang = lang;
    }


    /**
     *  Créer un plateau en fonction des options donnés par l'utilisateur.
     *  @param o
     */
    public Board(Option o) {
        super(2);

        board = o.getBoard();
        dico = o.getDico();
        lang = o.getLang();
        boardOption = o;
    }


    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the locale to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the dico
     */
    public RuzzleDictionary getDico() {
        return dico;
    }

    /**
     * @param dico the dico to set
     */
    public void setDico(RuzzleDictionary dico) {
        this.dico = dico;
    }

    /**
     *  @return le plateau
     */
    public Marble[][] getBoard() {
        return super.getGrid();
    }


    /**
     *  Fonction qui initialise le plateau.
     *  Celle ci doit être OBLIGATOIREMENT APPELEE AVANT de lancer
     *  le jeu.
     *  Elle s'occupe de générer le tableau de jeu (aléatoirement ou non,
     *  selon les options de l'utilisateur).
     */
    public void init() {
        tGrid = new Marble[2 * row][];
        for(int i = 0; i <= row/2; i++) {
            tGrid[i] = new Marble[(2 * row) + (2 * i) + 1];
            tGrid[(2 * row) - i - 1] = new Marble[(2 * row) + (2 * i) + 1];
        }

        if (board == null) {
            fillGrid();
        } else {
            fillGrid(board);
        }
    }


    /**
     *  Remplis la grille.
     */
    private void fillGrid() {
        Random r = new Random();
        for (int i = 0; i < tGrid.length; i++) {
            for (int j = 0; j < tGrid[i].length; j++) {
                int random = r.nextInt(10000);
                double randomLetterValue = ((double)random)/100.0;
                tGrid[i][j] = new Marble(dico.getLetterSet().getLetterByPercentage(randomLetterValue));

                addNeighbours(i, j);
            }
        }

        addSpecialMarble('*');
        addSpecialMarble('*');
        addSpecialMarble('-');
        addSpecialMarble('-');
        addSpecialMarble('-');
        addSpecialMarble('-');

        for (byte bonus :
                new byte[]{Marble.LETTER_COUNT_DOUBLE,
                    Marble.LETTER_COUNT_TRIPLE, Marble.WORD_COUNT_DOUBLE,
                    Marble.WORD_COUNT_TRIPLE}) {
            do {
                int r_first = r.nextInt(2*row);
                int r_second = r.nextInt(tGrid[r_first].length);
                if (tGrid[r_first][r_second].getBonus() == Marble.NO_BONUS
                        && tGrid[r_first][r_second].getLetter().getLetter() != '*'
                        && tGrid[r_first][r_second].getLetter().getLetter() != '-') {
                    tGrid[r_first][r_second].setBonus(bonus);
                    break;
                }
            } while(true);
        }
    }

    /**
     * Remplis la grille selon un tableau de caractères, et les options
     * données par l'utilisateur (bonus, ...)
     * @param b
     */
    private void fillGrid(Character[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j].equals('*')) {
                    tGrid[i][j] = new Marble(new Letter('*', 0));
                } else if (b[i][j].equals('-')) {
                    tGrid[i][j] = new Marble(new Letter('-', 0));
                } else {
                    tGrid[i][j] = new Marble(dico.getLetterSet().getLetter(b[i][j]));
                }

                addNeighbours(i, j);
            }
        }

        tGrid[boardOption.bonus[0][0]][boardOption.bonus[0][1]].setBonus(Marble.LETTER_COUNT_DOUBLE);
        tGrid[boardOption.bonus[1][0]][boardOption.bonus[1][1]].setBonus(Marble.LETTER_COUNT_TRIPLE);
        tGrid[boardOption.bonus[2][0]][boardOption.bonus[2][1]].setBonus(Marble.WORD_COUNT_DOUBLE);
        tGrid[boardOption.bonus[3][0]][boardOption.bonus[3][1]].setBonus(Marble.WORD_COUNT_TRIPLE);
    }


    /**
     * Fonction qui s'occupe d'ajouter les voisins de la case donné en argument.
     * @param i
     * @param j
     */
    private void addNeighbours(int i, int j) {
        ArrayList<Integer[]> newNeighbours = new ArrayList<Integer[]>(6);
        int lineOfTheTop = 
            ((i < row)  ? ((j&1) == 0 ? i-1 : i+1)
                        : ((j&1) == 0 ? i+1 : i-1));

        for (int currentLine = i-1; currentLine < i+2; currentLine++) {
            if (currentLine < 0 || currentLine >= tGrid.length)
                continue;

            for (int currentMarble = j-(2*row); 
                    currentMarble <= j+(2*row); currentMarble++) {
                if (currentMarble < 0 
                        || currentMarble >=tGrid[currentLine].length
                        || (currentLine == i && currentMarble == j)
                        || !toAdd(currentLine == lineOfTheTop,
                                    currentLine, currentMarble,
                                    i, j)) 
                {
                    continue;
                }

                newNeighbours.add(new Integer[]{currentLine, currentMarble});
            }
        }

        tGrid[i][j].setNeighbours(newNeighbours);
    }

    /**
     * Fonction qui vérifie si la case voisine est bien une case voisine.
     * @param tline
     * @param line
     * @param cpt
     * @param i
     * @param j
     * @return true if it's a neighbour.
     */
    private boolean toAdd(boolean tline, int line, int cpt, int i, int j) {
        if (tline) {
            if (line == i-1) {
                return cpt >= (j-3+i) && cpt <= (j+i-1);
            } else {
                return cpt >= (j-i) && cpt <= (j+2-i);
            }
        } else {
            if (line == i) {
                return cpt >= (j-2) && cpt <= (j+2);
            } else {
                if (line == i-1) {
                    return cpt >= (j-4+i) && cpt <= (j+i);
                } else {
                    return cpt >= (j-i-1) && cpt <= (j+3-i);
                }
            }
        }
    }

    /**
     * Ajoute les cases spéciales aléatoirement.
     * @param special
     */
    private void addSpecialMarble(char special) {
        Random r = new Random();

        do {
            boolean exist = false;
            int r_first = r.nextInt(2 * row);
            int r_second = r.nextInt(tGrid[r_first].length);

            Marble m = tGrid[r_first][r_second];
            if (m.getLetter().getLetter() != special) {
                for (Integer[] neighbour : m.getNeighbours()) {
                    if (tGrid[neighbour[0]][neighbour[1]].getLetter().getLetter() == special)
                    {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    tGrid[r_first][r_second] = new Marble(new Letter(special, 0));
                    if (special == '*')
                        addNeighbours(r_first, r_second);
                    return;
                }
            }
        } while(true);
    }

    @Override
    public String toString() {
        String result = "";

        result += "\n";
        for(Marble[] m1 : tGrid) {
            for (Marble m2 : m1) {
                System.out.print(m2);
            }
            System.out.println();
        }

        result += "\n";
        return result;
    }
}
