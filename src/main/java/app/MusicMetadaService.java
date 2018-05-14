package app;

import de.odysseus.ithaka.audioinfo.AudioInfo;
import de.odysseus.ithaka.audioinfo.m4a.M4AInfo;
import de.odysseus.ithaka.audioinfo.mp3.MP3Info;

import java.io.*;

public class MusicMetadaService {

    private File musicFile;
    private String musicType;
    private AudioInfo metadata;


    public MusicMetadaService(){}
    public MusicMetadaService(InputStream musicFile, String musicType, long musicSize) {
        this.musicType = musicType;

        try(InputStream input = musicFile){
            this.metadata = (!this.musicType.contains("MP4")) ? new M4AInfo(input) : new MP3Info(input, musicSize);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMusicArtist(){
        return metadata.getArtist();
    }

    public int getMusicDate(){
        return metadata.getYear();
    }

    public String getMusicAlbum(){
        return metadata.getAlbum();
    }

    public String getMusicTitle(){
        return metadata.getTitle();
    }

    public String getMusicGenre(){
        return metadata.getGenre();
    }

    public String getDuration(){
        Long timeMiliseconds = metadata.getDuration();
        Long minutes = timeMiliseconds / 60000;
        Long seconds = (timeMiliseconds % 60000) / 1000;
        return minutes + ":" + seconds;
    }

    public void setMusicFilePath(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicFormat() {
        return this.metadata.getBrand();
    }
}
