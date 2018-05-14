package app.model;

import javax.persistence.*;

@Entity
@Table(name = "genero")
public class Genre {

    @Id
    @Column(name = "nome_genero", unique = true, nullable = false, length = 45)
    private String genreName;


    public Genre(){}
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
