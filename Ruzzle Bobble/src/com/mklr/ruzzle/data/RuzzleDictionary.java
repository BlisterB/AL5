package com.mklr.ruzzle.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.Locale;

import com.mklr.collection.Tree;
import com.mklr.graphics.engine.Engine;

public class RuzzleDictionary implements Runnable {
    private final String dictionnaryPath;
    private Locale locale;
    private Tree<Character> dictionnaryTree;
    private LetterSet letterSet;

    public RuzzleDictionary() {
        this(Locale.ENGLISH);
    }

    public RuzzleDictionary(Locale locale) {
        this(Locale.ENGLISH, "/usr/share/dict/words");
    }

    public RuzzleDictionary(Locale locale, String dictionnaryPath) {
        this(Locale.ENGLISH, dictionnaryPath, false);
    }

    public RuzzleDictionary(Locale locale, String dictionnaryPath, boolean init) {
        this.locale = locale;
        this.dictionnaryPath = dictionnaryPath;
        dictionnaryTree = new Tree<Character>();

        if (init)
            init();
    }

    /**
     * @return the dictionnaryPath
     */
    public String getDictionnaryPath() {
        return dictionnaryPath;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the dictionnaryTree
     */
    public Tree<Character> getDictionnaryTree() {
        return dictionnaryTree;
    }

    /**
     * @param dictionnaryTree the dictionnaryTree to set
     */
    public void setDictionnaryTree(Tree<Character> dictionnaryTree) {
        this.dictionnaryTree = dictionnaryTree;
    }

    /**
     * @return the letterSet
     */
    public LetterSet getLetterSet() {
        return letterSet;
    }

    public void init() {
        createLetterSet();
        createTree();
    }

    private void createTree() {
        try {
            FileReader fr = new FileReader(dictionnaryPath);
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            while (line != null) {
                Tree<Character> cur_pos = dictionnaryTree;
                int length = line.length();

                line = normalizeWord(line);
                for (int i = 0; i < length; i++) {
                    Character c = line.charAt(i);

                    if (!cur_pos.childExist(c))
                        cur_pos.add(c, new Tree<Character>(c));

                    cur_pos = cur_pos.getChild(c);
                }
                
                cur_pos.setStatus(Tree.TERMINAL);

                line = br.readLine();
            }
            fr.close();
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void createLetterSet() {
        letterSet = new LetterSet(locale);
        boolean success = true;
    
        if ((locale.getCountry()).equals(Locale.FRENCH)) {

        } else {
            try {
                FileReader fr = 
                    new FileReader(Engine.PATH + "config/lang/enEN.set");
                BufferedReader br = new BufferedReader(fr);

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
                        System.err.println("Arg error" + line);
                        success = false;
                        break;
                    }

                    Character c = splittedLine[0].charAt(0);
                    
                    try {
                        value = Integer.parseInt(splittedLine[1]);
                        percentage = Double.parseDouble(splittedLine[2]);
                    } catch(Exception e) {
                        System.err.println("Arg error" + line);
                        success = false;
                        break;
                    }

                    Letter newOne = new Letter(c, value, percentage);
                    if (letterSet.contains(newOne)) {
                        System.err.println("Letter " + c + " already exist");
                        success = false;
                        break;
                    }

                    letterSet.add(newOne);

                    line = br.readLine();
                }

                fr.close();
                br.close();
            } catch (Exception e) {
                success = false;
                System.err.println("Le fichier de configuration de la langue "
                        + "anglaise n'est pas disponible : "
                        + e.getMessage());
            } finally {
                if (success == false) {
                    letterSet = new LetterSet(locale);
                
                    for(int i = 97; i < 123; i++) {
                        letterSet.add(
                                new Letter((char)i, 1, 100.0/26.0));
                    }
                }
            }
        }
    }

    public boolean searchWord(String word) {
        Tree<Character> cur_pos = dictionnaryTree;
        int length = word.length();

        for (int i = 0; i < length; i++) {
            Character c = word.charAt(i);

            if (cur_pos.childExist(c))
                cur_pos = cur_pos.getChild(c);
            else
                return false;
        }

        return cur_pos.isTerminal();
    }

    private String normalizeWord(String s) {
        s = s.toLowerCase();
        return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[\u0300-\u036F]", "");
    }

    @Override
    public void run() {
        init();
    }
}
