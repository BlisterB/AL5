import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Window;
import com.mklr.ruzzle.data.RuzzleDictionary;


/** RuzzleBobble est la classe contenant le main et se chargeant du demarrage du programme*/ 
public class RuzzleBobble {
	public static final String PATH = "";
    
    private static Thread[] threadArray;

    private static HashMap<String, RuzzleDictionary> findDictionaries() {
        HashMap<String, RuzzleDictionary> dicList = 
            new HashMap<String, RuzzleDictionary>();
        File folder = new File("dict/");

        if (!folder.exists()) {
        } else if (!folder.isDirectory()) {
        } else {
            File[] subfiles = folder.listFiles();
            threadArray = new Thread[subfiles.length]; 

            int i = 0;
            for (File dictFile : subfiles) {
                String name = dictFile.getName();
                RuzzleDictionary tmp = new RuzzleDictionary(
                        name.substring(0, name.indexOf('.')), "dict/" + name);
                
                threadArray[i] = new Thread(tmp);
                threadArray[i].start();

                dicList.put(name, tmp);
            }
        }

        return dicList;
    }

    private static boolean allFinished() {
        for (Thread t : threadArray) {
            if (t.getState() != Thread.State.TERMINATED)
                return false;
        }
        return true;
    }

	public static void main(String[] args){
		HashMap<String, RuzzleDictionary> dicList = findDictionaries();

        /**
         *  Default dictionary...
         */
        dicList.put("English", new RuzzleDictionary());
		
		
		//Creation du moteur de jeu
		Engine engine = new Engine(dicList);
		//game.setEngine(engine);
		
		//Creation de la fenetre
		Window window = new Window(engine);
		
		//Lancement de l ecran de titre
		engine.setGameTitle();

        int i = 1;
        while (!allFinished()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            System.out.println("i : " + i + "\n");
            i++;
        }

        engine.dictAreLoaded();
	}
}
