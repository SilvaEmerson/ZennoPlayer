package app.model;

import javax.persistence.*;

@Entity
@Table(name = "single")
public class Music {

    @Id
    @Column(name = "titulo", nullable = false, length = 45)
    private String musicName;

    @Column(name = "caminho", unique = true, length = 255)
    private String musicPath;

    @Column(name = "duracao", length = 10)
    private String musicDuration;

    @Column(name = "formato", length = 45)
    private String musicFormat;

    @Column(name = "ano_lancamento")
    private int musicLaunchYear;
//    @OneToOne(cascade = CascadeType.ALL)

    @Column(name = "fk_artista", columnDefinition = "INTEGER")
    private int musicArtist;
//    @OneToOne(cascade = CascadeType.ALL)

    @Column(name = "fk_genero")
    private String musicGenre;
//    @OneToOne(cascade = CascadeType.ALL)

    @Column(name = "fk_album")
    private int musicAlbum;

    public Music(){}
    public Music(String musicName, String musicPath, String musicDuration, String musicFormat, int musicLaunchYear,
                 int musicArtist, String musicGenre, int musicAlbum) {
        this.musicName = musicName;
        this.musicPath = musicPath;
        this.musicDuration = musicDuration;
        this.musicFormat = musicFormat;
        this.musicLaunchYear = musicLaunchYear;
        this.musicArtist = musicArtist;
        this.musicGenre = musicGenre;
        this.musicAlbum = musicAlbum;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(String musicDuration) {
        this.musicDuration = musicDuration;
    }

    public String getMusicFormat() {
        return musicFormat;
    }

    public void setMusicFormat(String musicFormat) {
        this.musicFormat = musicFormat;
    }

    public int getMusicLaunchDate() {
        return musicLaunchYear;
    }

    public void setMusicLaunchDate(int musicLaunchDate) {
        this.musicLaunchYear = musicLaunchDate;
    }

    public int getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(int musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public int getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(int musicAlbum) {
        this.musicAlbum = musicAlbum;
    }
}
