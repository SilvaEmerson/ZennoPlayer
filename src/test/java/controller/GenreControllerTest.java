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
public class GenreControllerTest {

    private String url = "http://localhost:8000/";
    private String genreName = "Pop";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void genreConnectionTest() throws Exception {
        assertThat(this.testRestTemplate.getForObject(this.url + "get_genres", Iterable.class)).isNotNull();
    }//Testa a conexão com o banco de dados

    @Test
    public void genreAddAndDeleteTest() {
        assertThat(this.testRestTemplate.postForObject(this.url+"add_genre?genreName=" + this.genreName,
                null, String.class)).contains("Saved");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_genre?genreName="
                        + this.genreName, HttpMethod.DELETE, null, String.class).getBody());
    }//Testando o método de adicionar e deletar música

    @Test
    public void deleteInexistentGenre(){
        assertNotEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_genre?genreName="
                + this.genreName, HttpMethod.DELETE, null, String.class).getBody());
    }//Testando deletar um gênero inexistente

    @Test
    public void genreAddDuplicated() {
        assertThat(this.testRestTemplate.postForObject(this.url+"add_genre?genreName=" + this.genreName,
                null, String.class)).contains("Saved");

        assertThat(this.testRestTemplate.postForObject(this.url+"add_genre?genreName=" + this.genreName,
                null, String.class)).contains("Saved");

        assertEquals("Deleted", this.testRestTemplate.exchange(this.url + "/delete_genre?genreName="
                + this.genreName, HttpMethod.DELETE, null, String.class).getBody());
    }//Testando um gênero duas vezes
}