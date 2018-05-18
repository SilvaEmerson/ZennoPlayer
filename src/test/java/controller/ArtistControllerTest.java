package controller;

import app.Application;
import app.repositories.ArtistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = {Application.class})
public class ArtistControllerTest {

    private String url = "http://localhost:8000/";
    private String artistName = "NEFFEX";
    private int artistAge = 0;
    private String artistOriginCountry = "EUA";
    private int artisAlbum = 5;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void getArtists() {
        assertThat(testRestTemplate.getForObject(this.url + "get_artists", Integer.class)).isGreaterThan(0);
    }//Testando conexão com o banco de dados

    @Test
    public void addAndDeleteArtist(){
        assertThat(this.testRestTemplate.postForObject(this.url + "add_artist?artistName=" + this.artistName
                + "&artistAge=" + this.artistAge + "&artistOriginCountry=" + this.artistOriginCountry,
                null, String.class)).isEqualTo("Saved!");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_artist?artistName="
                + this.artistName, HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando adicionar e deletar artista


    @Test
    public void addDuplicateArtists(){
        assertThat(this.testRestTemplate.postForObject(this.url + "add_artist?artistName=" + this.artistName
                        + "&artistAge=" + this.artistAge + "&artistOriginCountry=" + this.artistOriginCountry,
                null, String.class)).isEqualTo("Saved!");

        assertThat(this.testRestTemplate.postForObject(this.url + "add_artist?artistName=" + this.artistName
                        + "&artistAge=" + this.artistAge + "&artistOriginCountry=" + this.artistOriginCountry,
                null, String.class)).doesNotContain("Saved!");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_artist?artistName="
                + this.artistName, HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando adicionar artistas duplicados

    @Test
    public void deleteInexistentArtist(){
        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_artist?artistName="
                + this.artistName, HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando deletar artistas


}