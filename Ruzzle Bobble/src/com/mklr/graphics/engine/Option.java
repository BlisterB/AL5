package com.mklr.graphics.engine;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class Option {
	public static RuzzleDictionary dico;
	public static String Algo;
	public static int timer;
	
	/**
	 * @return the dico
	 */
	public static RuzzleDictionary getDico() {
		return dico;
	}
	/**
	 * @param dico the dico to set
	 */
	public static void setDico(RuzzleDictionary dico) {
		Option.dico = dico;
	}
	/**
	 * @return the timer
	 */
	public static int getTimer() {
		return timer;
	}
	/**
	 * @param timer the timer to set
	 */
	public static void setTimer(int timer) {
		Option.timer = timer;
	}
	
	
}
