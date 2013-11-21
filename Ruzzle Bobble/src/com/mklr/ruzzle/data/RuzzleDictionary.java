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
 * 
 * It has to be called like :
 * 
 * Dictionary d = new Dictionary(Locale.ENGLISH, dictionaryPath);
 * new Thread(d).start();
 * 
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class RuzzleDictionary implements Runnable {
    private final String dictionaryPath;
    private String lang;
    private Tree<Character> dictionaryTree;
    private LetterSet letterSet;
    private int maxLength;

    /**
     * Create the dictionary.
     * The default langage is English.
     * The default dictionary is at "/usr/share/dict/words"
     * (Only on Unix systems...)
     */
    public RuzzleDictionary() {
        this("English", "/usr/share/dict/words");
    }

    /**
     * Create the dictionary according to the dictionary path.
     * The name is set to "UNKNOWN".
     * @param dictionaryPath path of the dictionary
     */
    public RuzzleDictionary(String dictionaryPath) {
        this("UNKNOWN", dictionaryPath);
    }

    /**
     * Create the dictionary according to the langage,
     * and the dictionary given.
     * 
     * The given dictionary must respect the rule :
     *  one word by line.
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
     * It initialize the dictionary object.
     */
    public void init() {
        createLetterSet();
        createTree();
    }

    /**
     * Search the word in the current dictionary.
     * @param word
     * @return true if the word exists
     */
    public boolean searchWord(String word) {
        return searchWord(dictionaryTree, word);
    }

    private boolean searchWord(Tree<Character> beg, String word) {
        Tree<Character> cur_pos = beg;

        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length;
             i++) {
            Character c = wordArray[i];

            if (c.charValue() == '*') {
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
     * Create the tree.
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
                                tmp = new Tree<Character>(c);
                                cur_pos.add(c, tmp);
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

                    if (letterSet.contains(newOne)) {
                        System.out.println("Letter " + c + " already exist"
                                + "DEFAULT VALUE INITIALIZED.");
                        success = false;
                        break;
                    }

                    letterSet.add(newOne);

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
                    letterSet.add( new Letter((char)i, 1, 
                                new double[]{lastPercentage, percentage}));
                    lastPercentage += percentage;
                }
            }
        }
    }

    /**
     * Normalize the word given.
     * It returns the word without any capitalize characters and without
     * any accent.
     * @param s
     * @return the normalized string
     */
    private String normalizeWord(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[\u0300-\u036F]|[-\'.]", "");
    }
}
