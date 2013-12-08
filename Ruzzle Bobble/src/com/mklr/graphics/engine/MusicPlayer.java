package com.mklr.graphics.engine;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** MusicPlayper permet de lire des fichier MIDI (music) et WAV (sound)*/
public class MusicPlayer {
	boolean play; //Defini si le thread de lecture doit continuer (false pour fermer le thread de lecture)
	boolean loopContinuously; //Defini si la musique doit être jouée en boucle
	private Sequencer sequencer = null;
	
	public static final int MIDI = 1;
	public static final int WAV = 2;
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// CONSTRUCTEURS //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	
	/** Cree un lecteur MIDI*/
	public MusicPlayer(final String path, final boolean loopContinuously){
		play = true;
		this.loopContinuously = loopContinuously;
		
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
			        //On boucle la musique indefiniment
			        if(loopContinuously)
			        	sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
				}catch(Exception e) {
				        System.out.println(e.toString());
				}
			}
		}).start();
	}
	
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	/** Ferme le lecteur MIDI */
	public void close(){
		if(sequencer != null && sequencer.isRunning()){
			sequencer.stop();
			sequencer.close();
		}
	}

	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	
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
	
	public static void playSound(final String path){
		new Thread(new Runnable(){
			public void run(){
				try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
                    // Get a sound clip resource.
                    Clip clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input stream.
                    clip.open(audioIn);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                	System.out.println("Format audio non supporté");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
			}
		}).start();
	}
}
