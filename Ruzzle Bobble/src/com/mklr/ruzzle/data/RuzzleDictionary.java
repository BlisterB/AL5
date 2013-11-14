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
    private Locale locale;
    private Tree<Character> dictionaryTree;
    private LetterSet letterSet;

    /**
     * Create the dictionary.
     * The default langage is English.
     * The default dictionary is at "/usr/share/dict/words"
     * (Only on Unix systems...)
     */
    public RuzzleDictionary() {
        this(Locale.ENGLISH);
    }

    /**
     * Create the dictionary according to the langage given.
     * The default dictionary is at "/usr/share/dict/words"
     * (Only on Unix systems...)
     * @param locale the given langage
     */
    public RuzzleDictionary(Locale locale) {
        this(Locale.ENGLISH, "/usr/share/dict/words");
    }

    /**
     * Create the dictionary according to the langage,
     * and the dictionary given.
     * 
     * The given dictionary must respect the rule :
     * 	one word by line.
     * @param locale the langage
     * @param dictionnaryPath path of the dictionary
     */
    public RuzzleDictionary(Locale locale, String dictionnaryPath) {
        this.locale = locale;
        this.dictionaryPath = dictionnaryPath;
        dictionaryTree = new Tree<Character>();
    }

    /**
     * @return the dictionnaryPath
     */
    public String getDictionaryPath() {
        return dictionaryPath;
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
    public Tree<Character> getDictionaryTree() {
        return dictionaryTree;
    }

    /**
     * @param dictionnaryTree the dictionnaryTree to set
     */
    public void setDictionaryTree(Tree<Character> dictionnaryTree) {
        this.dictionaryTree = dictionnaryTree;
    }

    /**
     * @return the letterSet
     */
    public LetterSet getLetterSet() {
        return letterSet;
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
        int length = word.length();
        Tree<Character> cur_pos = dictionaryTree;
        
        for (int i = 0; i < length; i++) {
            Character c = word.charAt(i);

            if (cur_pos.childExist(c))
                cur_pos = cur_pos.getChild(c);
            else
                return false;
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
        letterSet = new LetterSet(locale);
        String path = Engine.PATH + "config/lang/enEN.set";
        boolean success = true;
    
        if ((locale.getCountry()).equals(Locale.FRENCH)) {
        	path = Engine.PATH + "config/lang/frFR.set";
        } 
        
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
                        System.out.println("The line is incorrect : \n" + line
                        		+ "DEFAULT VALUE INITIALIZED.");
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

                    Letter newOne = new Letter(c, value, percentage);
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
        	if (success == false) {
        		letterSet = new LetterSet(locale);
                
                for(int i = 97; i < 123; i++) {
                	letterSet.add( new Letter((char)i, 1, 100.0/26.0));
                }
            }
        }
    }

    /**
     * Normalize the word given.
     * It returns the word without any capitalize characters and without
     * any accent.
     * @param s
     * @return
     */
    private String normalizeWord(String s) {
        s = s.toLowerCase();
        return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[\u0300-\u036F]", "");
    }
}
