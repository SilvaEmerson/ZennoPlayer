package app.model;

import javax.persistence.*;

@Entity
@Table(name = "álbum")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Álbum", updatable = false, nullable = false, length = 11)
    private int albumId;
    @Column(name = "nome", length = 45, unique = true, nullable = false)
    private String albumName;
    @Column(name = "ano_lancamento", length = 11)
    private int albumYear;
    @Column(name = "gravadora", length = 45)
    private String albumRecorder;

    public Album(){}
    public Album(String albumName){
        this.albumName = albumName;
    }

    public Album(String albumName, int albumYear, String albumRecorder) {
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.albumRecorder = albumRecorder;
    }

    @Override
    public String toString() {
        return "[" + this.albumName + "]";
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(int albumYear) {
        this.albumYear = albumYear;
    }

    public String getAlbumRecorder() {
        return albumRecorder;
    }

    public void setAlbumRecorder(String albumRecorder) {
        this.albumRecorder = albumRecorder;
    }

}
