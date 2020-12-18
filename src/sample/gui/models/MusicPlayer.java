package sample.gui.models;

import sample.be.Song;
import sample.bll.SongManager;

import sample.gui.controller.MainViewController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip audioClip;
    private AudioInputStream audioStream;
    private long clipTimePosition;
    private String path;
    public static Song currentSong;

    public MusicPlayer(Song currentSong){
    }

    public void play() {
        String audioFilePath = getUrlFromSong();
        File audioFile = new File(audioFilePath);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        try {
            audioClip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            audioClip.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        audioClip.start();
    }
    public String getUrlFromSong(){
        return currentSong.getUriString();
    }


    public void stopSong(){
        clipTimePosition = audioClip.getMicrosecondPosition();
        audioClip.stop();
    }

    public void resume(){
        if(clipTimePosition>0) {
            audioClip.setMicrosecondPosition(clipTimePosition);
            audioClip.start();
        }
        else{
            play();
        }
    }

    public void setCurrentSong(Song song){
        this.currentSong= song;
    }
}
