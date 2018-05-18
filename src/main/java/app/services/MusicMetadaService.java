package app.services;

import de.odysseus.ithaka.audioinfo.AudioInfo;
import de.odysseus.ithaka.audioinfo.m4a.M4AInfo;
import de.odysseus.ithaka.audioinfo.mp3.ID3v2Exception;
import de.odysseus.ithaka.audioinfo.mp3.MP3Exception;
import de.odysseus.ithaka.audioinfo.mp3.MP3Info;

import java.io.*;

public class MusicMetadaService {

    private File musicFile;
    private String musicType;
    private AudioInfo metadata;


    public MusicMetadaService(){}
    public MusicMetadaService(InputStream musicFile, String musicType) {
        this.musicType = musicType;
        InputStream input = musicFile;
        try {
            if (!this.musicType.contains("mpeg")) {
                this.metadata = new M4AInfo(input);
            } else {
                input = new BufferedInputStream(input);
                this.metadata = new MP3Info(input, input.available());
            }
        }catch (IOException e1) {
            e1.printStackTrace();
        } catch (ID3v2Exception e1) {
            e1.printStackTrace();
        } catch (MP3Exception e1) {
            e1.printStackTrace();
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
