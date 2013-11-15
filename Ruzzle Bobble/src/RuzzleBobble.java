import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Window;



public class RuzzleBobble {
	public static final String PATH = "";
	
	public static void main(String[] args){
		//Creation du moteur de jeu
		Engine engine = new Engine();
		
		//Creation de la fenetre
		Window window = new Window(engine);
		
		//Lancement de l ecran de titre
		engine.setGameTitle();
	}
}
