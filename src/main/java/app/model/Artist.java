package app.model;

import javax.persistence.*;

@Entity
@Table(name = "artista")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Artista", unique = true)
    private int id;

    @Column(name = "nome_artista", length = 45, nullable = false, unique = true, columnDefinition = "varchar")
    private String artistName;

    @Column(name = "idade", length = 11, nullable = true)
    private int artistAge;

    @Column(name = "país_origem", length = 45, nullable = true)
    private String artistOriginCountry;

    public Artist(){}

    public Artist(String artistName){
        this.artistName = artistName;
    }

    public Artist(String artistName, int artistAge, String artistOriginCountry) {
        this.artistName = artistName;
        this.artistAge = artistAge;
        this.artistOriginCountry = artistOriginCountry;
    }

    @Override
    public String toString() {
        return "Artist[ " + this.artistName + ", " + this.artistAge + ", " + this.artistOriginCountry + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistAge() {
        return artistAge;
    }

    public void setArtistAge(int artistAge) {
        this.artistAge = artistAge;
    }

    public String getArtistOriginCountry() {
        return artistOriginCountry;
    }

    public void setArtistOriginCountry(String artistOriginCountry) {
        this.artistOriginCountry = artistOriginCountry;
    }

}
