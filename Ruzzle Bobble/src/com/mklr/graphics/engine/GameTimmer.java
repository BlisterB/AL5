package com.mklr.graphics.engine;

import com.mklr.graphics.stage.Stage;

public class GameTimmer {
	private int time;
	private Stage stage;
	
	public GameTimmer(int time, Stage stage){
		this.time = time;
		this.stage = stage;
		startTimmer();
	}
	
	public void startTimmer(){
		new Thread(new Runnable(){
			public void run(){
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				time -= 100;
			}
		}).start();
		stage.interaction(Stage.TIMMER_END);
	}
}
