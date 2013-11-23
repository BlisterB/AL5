package com.mklr.graphics.engine;

import com.mklr.graphics.stage.Stage;

public class GameTimer {
	private int time;//En milliseconde
	private Stage stage;
	
	public GameTimer(int time, Stage stage){
		this.time = time;
		this.stage = stage;
		startTimmer();
	}
	
	public void startTimmer(){
		new Thread(new Runnable(){
			public void run(){
				while(time > 0){
					try{
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					time -= 10;
				}
				stage.interaction(Stage.TIMMER_END);
			}
		}).start();
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
