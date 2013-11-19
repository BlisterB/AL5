import java.util.HashMap;
import java.util.Map;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Window;
import com.mklr.ruzzle.data.RuzzleDictionary;



public class RuzzleBobble {
	public static final String PATH = "";
	
	public static void main(String[] args){
		HashMap<String, RuzzleDictionary> dicList = new HashMap<String, RuzzleDictionary>();
		dicList.put("ENGLISH", new RuzzleDictionary());
		
		
		//Creation du moteur de jeu
		Engine engine = new Engine(dicList/*,Game game*/);
		//game.setEngine(engine);
		
		//Creation de la fenetre
		Window window = new Window(engine);
		
		//Lancement de l ecran de titre
		engine.setGameTitle();
	}
}
