package controller;

import app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class})
public class AlbumControllerTest {

    private String url = "http://localhost:8000/";
    private String albumName = "Addicted";
    private int albumYear = 2018;
    private String albumRecorder = "";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void albumGetTest() throws Exception {
        assertThat(this.testRestTemplate.getForObject(this.url + "get_albums", Integer.class)).isGreaterThan(0);
    }//testando conecão com o banco de dados

    @Test
    public void albumAddAndDeleteTest() {
        assertThat(this.testRestTemplate.postForObject(this.url+"add_album?albumName=" + this.albumName
                + "&albumYear=" + this.albumYear + "&albumRecorder=" + this.albumRecorder,
                null, String.class)).contains("Saved");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_album?albumName="
                + this.albumName , HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando adicionar e deletar álbum

    @Test
    public void saveSameAlbum(){
        assertThat(this.testRestTemplate.postForObject(this.url+"add_album?albumName=" + this.albumName
                        + "&albumYear=" + this.albumYear + "&albumRecorder=" + this.albumRecorder,
                null, String.class)).contains("Saved");

        assertThat(this.testRestTemplate.postForObject(this.url+"add_album?albumName=" + this.albumName
                        + "&albumYear=" + this.albumYear + "&albumRecorder=" + this.albumRecorder,
                null, String.class)).doesNotContain("Saved");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_album?albumName="
                + this.albumName, HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando adicionar o mesmo álbum

    @Test
    public void deleteInexistentAlbum(){
        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_album?albumName="
                + this.albumName, HttpMethod.DELETE, null, String.class)
                .getBody());
    }//Testando deletar um álbum inexistente
}