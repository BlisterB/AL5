package com.mklr.graphics.engine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	public static void playMusic(final String path){
		new Thread(new Runnable(){
			public void run(){
				File file = new File(path);
				AudioInputStream ais = null;
				try{
					ais = AudioSystem.getAudioInputStream(file);
				}catch(UnsupportedAudioFileException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				AudioFormat audioFormat = ais.getFormat();
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				SourceDataLine line = null;
				try{
					line = (SourceDataLine)AudioSystem.getLine(info);
				}catch(LineUnavailableException e){
					e.printStackTrace();
				}
				try{
					line.open(audioFormat);
				}catch(LineUnavailableException e){
					e.printStackTrace();
				}
				line.start();
				try {
					byte bytes[] = new byte[1024];
					int bytesRead=0;
					while (((bytesRead = ais.read(bytes, 0, bytes.length)) != -1)){
						line.write(bytes, 0, bytesRead);
					}
				} catch (IOException io) {
					io.printStackTrace();
					return;
				}
				line.close();
			}
		}).start();
	}
}
