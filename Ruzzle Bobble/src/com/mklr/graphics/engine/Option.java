package com.mklr.graphics.engine;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class Option {
	public String lang; 
	public RuzzleDictionary dico;
	public int timer_time;
	public Character[][] board;
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// CONSTRUCTEURS //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	Option(){
		
	}
	Option(int timer_time){
		this();
		this.timer_time = timer_time;
	}
	Option(String lang, RuzzleDictionary dico, int timer_time){
		this(timer_time);
		this.lang = lang;
		this.dico = dico;
	}
	Option(String lang, RuzzleDictionary dico, int timer_time, Character[][] board){
		this(lang, dico, timer_time);
		this.board = board;
	}
	
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////
	
	/** Renvoie un objet Option avec le timmer par defaut, le dictionnaire et la langue sont a definir */
	public static Option getDefaultOptions(){
		return new Option(1000 * 60 *2);
	}
	
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the dico
	 */
	public RuzzleDictionary getDico() {
		return dico;
	}
	/**
	 * @param dico the dico to set
	 */
	public void setDico(RuzzleDictionary dico) {
		this.dico = dico;
	}
	/**
	 * @return the timer_time
	 */
	public int getTimer_time() {
		return timer_time;
	}
	/**
	 * @param timer_time the timer_time to set
	 */
	public void setTimer_time(int timer_time) {
		this.timer_time = timer_time;
	}
	/**
	 * @return the board
	 */
	public Character[][] getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Character[][] board) {
		this.board = board;
	}
}
