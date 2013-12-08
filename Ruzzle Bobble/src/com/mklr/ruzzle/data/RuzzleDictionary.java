package com.mklr.ruzzle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.Locale;

import com.mklr.collection.Tree;
import com.mklr.graphics.engine.Engine;

/**
 * This class reprend a dictionary for the ruzzle game.
 * It implements the Runnable interface, which permits to load the
 * dictionnary in a new thread.
 *
 * Cette classe représente un dictionnaire pour le jeu Ruzzle.
 * Elle implément l'interface Runnable, permettant de charger le dictionnaire
 * dans un Thread. Ainsi on peut donc "profiter" de l'interface et d'un temps
 * de chargement le temps de charger tous les dictionnaires. (Tous les
 * dictionnaires sont chargés en même temps)
 *
 * La création du dictionnaire doit être fait telle que :
 *
 * RuzzleDictionary d = new RuzzleDictionary(prefixOfTheFilename, pathToTheFileName);
 *
 *      new Thread(d).start();
 *              OU
 *      d.init(); -> bloque le thread courant.
 *
 *
 * Les fichiers dictionnaires sont stockés dans le path ${PATH_TO_RUZZLE_BOBBLE}/dict/${LANG}.dict
 * La seule exception faite du dictionnaire anglais qui se situe dans le path /usr/share/dict/words
 *
 * ( ${LANG} correspond au prefixe du fichier. )
 *
 * /!\ ATTENTION :
 *  On peut définir aussi un fichier correspondant aux pourcentages des lettres.
 *  Si le fichier n'existe pas, on supposera que chaque lettre vaudra 1 point, et aura un pourcentage
 *  de (100.0 / 26.0) d'apparaître dans le board.
 *  Voir la classe LetterSet pour voir comment créer le fichier.
 *  @see LetterSet
 *
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class RuzzleDictionary implements Runnable {
    private final String dictionaryPath;
    private String lang;
    private final Tree<Character> dictionaryTree;
    private LetterSet letterSet;
    private int maxLength;

    /**
     * Créer le dictionnaire par défaut.
     * Par défaut, le dictionnaire est en langue Anglaise et se trouve dans le
     * fichier "/usr/share/dict/words" disponnible sous Unix.
     */
    public RuzzleDictionary() {
        this("English", "/usr/share/dict/words");
    }

    /**
     * Créer le dictionnaire selon la langue donnée.
     * Le dictionnaire se situera dans le chemin donné. Si le fichier n'existe
     * pas, alors le dictionnaire sera supprimé de la liste.
     * @param lang the langage
     * @param dictionaryPath path of the dictionary
     */
    public RuzzleDictionary(String lang, String dictionaryPath) {
        this.lang = lang;
        this.dictionaryPath = dictionaryPath;
        dictionaryTree = new Tree<Character>();
        maxLength = 0;
    }

    /**
     * @return the dictionaryPath
     */
    public String getDictionaryPath() {
        return dictionaryPath;
    }

    /**
     * @return the locale
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
     * @return the dictionnaryTree
     */
    public Tree<Character> getDictionaryTree() {
        return dictionaryTree;
    }

    /**
     * @return the letterSet
     */
    public LetterSet getLetterSet() {
        return letterSet;
    }

    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }


    /**
     * Initialise le dictionnaire.
     *
     * Tout d'abord il crée un ensemble de lettre, avec toutes les
     * données nécessaire pour la création du plateau aléatoire.
     * @see LetterSet
     *
     * Il crée ensuite un arbre lexicographique selon les mots donnés
     * dans le dictionnaire.
     * @see Tree
     */
    public void init() {
        createLetterSet();
        createTree();
    }


    /**
     * Recherche le mot dans le dictionnaire.
     * @param word
     * @return true if the word exists
     */
    public boolean searchWord(String word) {
        return searchWord(dictionaryTree, word);
    }

    /**
     * Recherche le mot dans le dictionnaire à partir d'une position donnée.
     * (Permet de simplifier les recherches en cas de jokers...)
     * @param beg début de la position courant dans l'arbre où l'on doit
     *            commencer la recherche
     * @param word mot ou partie du mot à rechercher...
     * @return true si le mot existe...
     */
    private boolean searchWord(Tree<Character> beg, String word) {
        Tree<Character> cur_pos = beg;

        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length;
             i++) {
            Character c = wordArray[i];

            if (c == '*') {
                char[] nextPart = new char[wordArray.length - i - 1];

                for (int j = 0, k = i+1; j < nextPart.length; j++)
                    nextPart[j] = wordArray[k++];

                if (nextPart.length == 0)
                    return true;
                
                for (Tree<Character> child
                        : cur_pos.getListOfChilds().values()) {
                    if (searchWord(child, new String(nextPart)))
                        return true;
                }

                return false;
            } else {
                if (cur_pos.childExist(c))
                    cur_pos = cur_pos.getChild(c);
                else
                    return false;
            }
        }

        return cur_pos.isTerminal();
    }

    @Override
    public void run() {
        init();
    }

    
    /**
     * Créer l'arbre selon le fichier donné en paramètre...
     * @see Tree
     */
    private void createTree() {
        try {
            FileReader fr = new FileReader(dictionaryPath);
            BufferedReader br = new BufferedReader(fr);
            
            try {
                String line = br.readLine();
                while (line != null) {
                    Tree<Character> cur_pos = dictionaryTree;

                    line = normalizeWord(line);
                    char lineArray[] = line.toCharArray();
                    if (lineArray.length > 1) {
                        if (lineArray.length > maxLength) {
                            maxLength = lineArray.length;
                        }
                        
                        for (Character c : lineArray) {
                            c = Character.toLowerCase(c);
                            Tree<Character> tmp = cur_pos.getChild(c);

                            if (tmp == null) {
                                cur_pos.add(c);
                                tmp = cur_pos.getChild(c);
                            }
                                
                            cur_pos = tmp;
                        }
                        cur_pos.setStatus(Tree.TERMINAL);
                    }

                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fr.close();
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Créer l'ensemble de lettre selon le fichier de configuration trouvé.
     * Si le fichier n'existe pas, un sera chargé par défaut.
     * @see LetterSet
     */
    private void createLetterSet() {
        letterSet = new LetterSet(lang);
        String path = Engine.PATH + "config/lang/" + lang + ".set";
        boolean success = true;
        double lastPercentage = 0.0;

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            try {
                String line = br.readLine();
                while (line != null) {
                    int value;
                    double percentage;

                    line = line.trim().replace(" ", "");
                    String splittedLine[] = line.split(":");

                    if (line.equals(""))
                        continue;

                    if (splittedLine.length != 3
                            && (splittedLine[0].length() > 1)) {
                        System.out.println("The line is incorrect : " + line
                                + "\nDEFAULT VALUE INITIALIZED.");
                        success = false;
                        break;
                    }

                    Character c = splittedLine[0].charAt(0);
                    
                    try {
                        value = Integer.parseInt(splittedLine[1]);
                        percentage = Double.parseDouble(splittedLine[2]);
                    } catch(Exception e) {
                        System.out.println("Arg error" + line);
                        success = false;
                        break;
                    }

                    Letter newOne = new Letter(c, value, 
                            new double[]{lastPercentage, lastPercentage + percentage});
                    lastPercentage += percentage;

                    if (letterSet.containsValue(newOne)) {
                        System.out.println("Letter " + c + " already exist"
                                + "DEFAULT VALUE INITIALIZED.");
                        success = false;
                        break;
                    }

                    letterSet.put(c, newOne);

                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fr.close();
                br.close();
            }
        } catch (Exception e) {
            success = false;
            System.out.println("Le fichier de configuration de la langue "
                    + "anglaise n'est pas disponible : "
                    + e.getMessage());
        } finally {
            if (!success) {
                letterSet = new LetterSet(lang);
                
                for(int i = 97; i < 123; i++) {
                    double percentage = (100.0/26.0) + lastPercentage;
                    letterSet.put((char)i, new Letter((char)i, 1,
                                new double[]{lastPercentage, percentage}));
                    lastPercentage += percentage;
                }
            }
        }
    }

    /**
     * Fonction qui normalise le mot donné.
     * C'est à dire, il enlève tout caractères qui n'est pas une lettre
     * dans le format [a-zA-Z], et remplace les caractères accentués par
     * leur caractère simple (par exemple : é se transforme en e).
     * @param s
     * @return the normalized string
     */
    private String normalizeWord(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[\u0300-\u036F]|[-\'.]", "");
    }
}
