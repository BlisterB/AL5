package com.mklr.graphics.engine;
import com.mklr.graphics.stage.GameTitle;

public class Launcher {
	public static String PATH = "";//Variable dont on se sert pour ajuster le repertoire courant
	
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
