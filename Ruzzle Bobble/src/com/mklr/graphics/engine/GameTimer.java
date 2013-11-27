package com.mklr.graphics.engine;

import com.mklr.graphics.stage.Stage;

public class GameTimer {
	private int time;//En milliseconde
	private boolean run_timmer;
	private Stage stage;
	
	public GameTimer(int time, Stage stage){
		this.time = time;
		this.stage = stage;
		startTimmer();
	}
	
	public void startTimmer(){
		run_timmer = true;
		new Thread(new Runnable(){
			public void run(){
				while(time > 0 && run_timmer){
					try{
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					time -= 10;
				}
				if(run_timmer) //Si on a demandé l'arret du timmer, on ne comunique pas avec le stage associe
					stage.interaction(Stage.TIMMER_END);
			}
		}).start();
	}
	
	public void pause(){
		run_timmer = false;
	}
	
	public void close(){
		run_timmer = false;
	}
	
	public int getDizaineMinute(){
		return time/1000/60/10%10;
	}
	public int getUniteMinute(){
		return time/1000/60%10;
	}
	public int getDizaineSeconde(){
		int n = time/1000;//Nombre de Seconde
		while(n>60){
			n%=60;
		}
		return n/10%10;
	}
	public int getUniteSeconde(){
		int n = time/1000;//Nombre de Seconde
		while(n>60){
			n%=60;
		}
		return n%10;
	}
	public int getDiziemeSeconde(){
		return time/10%10;
	}
}
