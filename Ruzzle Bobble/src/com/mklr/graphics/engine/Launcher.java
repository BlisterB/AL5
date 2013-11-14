package com.mklr.graphics.engine;
import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Window;



public class Launcher {
	public static final String PATH = "";
	
	public static void main(String[] args){
		//Creation du moteur de jeu
		Engine engine = new Engine();
		
		//Creation de la fenetre
		Window window = new Window(engine);
		
		//Recuperation du moteur de jeu et lancement de l ecran de titre
		engine.setGameTitle();
	}
}
