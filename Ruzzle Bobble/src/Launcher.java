import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Window;

public class Launcher {
	public static void main(String[] args){
		Engine engine; 
		
		//Creation de la fenetre
		Window window = new Window();
		
		//Recuperation du moteur de jeu et lancement de l ecran de titre
		engine = window.getEngine();
		engine.setGameTitle();
		window.repaint();
	}
}
