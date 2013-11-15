package com.mklr.graphics.engine;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MusicPlayer {
	boolean play;
	private Sequencer sequencer = null;;
	
	public MusicPlayer(final String path){
		play = true;
		
		new Thread(new Runnable(){
			public void run(){
				//Ouverture du fichier
				File file = new File(path);
				//Initialisation du sequenceur MIDI
				try{
					sequencer = MidiSystem.getSequencer();
				}catch(MidiUnavailableException e){
			        System.out.println(e.toString());
				}
				try {
			        sequencer.setSequence(MidiSystem.getSequence(file));
			        //Lancement de la lecture
			        sequencer.open();
			        sequencer.start();
				}catch(Exception e) {
				        System.out.println(e.toString());
				}
			}
		}).start();
	}
	
	public void stopPlaying(){
		sequencer.stop();
		sequencer.close();
	}

	/**
	 * @return the play
	 */
	public boolean isPlay() {
		return play;
	}

	/**
	 * @param play the play to set
	 */
	public void setPlay(boolean play) {
		this.play = play;
	}
	
	
	
	/*
	public static void playMusic(final String path){
		new Thread(new Runnable(){
			public void run(){
				//Ouverture du fichier
				File file = new File(path);
				
				//Ouverture d'un flux audio a partir de file et capture des erreurs
				AudioInputStream ais = null;
				try{
					ais = AudioSystem.getAudioInputStream(file);
				}catch(UnsupportedAudioFileException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				
				//On stocke le format du fichier a lire pour ensuite creer un DataLine afin de creer l'instance de Line adequate
				AudioFormat audioFormat = ais.getFormat();
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				
				//On instancie le Line
				SourceDataLine line = null;
				try{
					line = (SourceDataLine)AudioSystem.getLine(info);
				}catch(LineUnavailableException e){
					e.printStackTrace();
				}
				
				//On fait ouvrir l'instance de Line par le systeme a l'aide du format audio adequat
				try{
					line.open(audioFormat);
				}catch(LineUnavailableException e){
					e.printStackTrace();
				}
				
				//On demarre la ligne pour rediriger le flux audio vers la carte son
				line.start();
				
				//On lit le flux comme un flux de bas niveau
				try{
					byte bytes[] = new byte[128];
					int bytesRead=0;
					while (((bytesRead = ais.read(bytes, 0, bytes.length)) != -1)){
						line.write(bytes, 0, bytesRead);
					}
				}catch (IOException io) {
					io.printStackTrace();
					return;
				}
				
				//On ferme enfin la ligne
				line.close();
			}
		}).start();
	}
	*/
}
