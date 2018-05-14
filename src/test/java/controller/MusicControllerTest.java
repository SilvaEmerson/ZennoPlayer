package controller;

import app.Application;
import app.repositories.AlbumRepository;
import app.repositories.ArtistRepository;
import app.repositories.MusicRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
public class MusicControllerTest {

    private String url = "http://localhost:8000/";
    private String musicName = "Addicted";
    private String musicDuration = "4:30";
    private String  musicPath = "/home/emerson/Downloads/01 Naked.m4a";
    private String musicFormat = "m4a";
    private int musicLaunchYear = 2018;
    private int musicArtist = 1;
    private int musicAlbum = 1;
    private String musicGenre = "Pop";


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Before
    @After
    public void deleteAfter(){
        int musicArtistId = this.artistRepository.findByArtistNameLike("James Arthur").getId();

        musicRepository.deleteByMusicNameAndMusicArtist("Naked", musicArtistId);
    }

    @Test
    public void getMusics() {
        assertThat(this.testRestTemplate.getForObject(this.url + "get_musics", Iterable.class)).isNotNull();
    }

    @Test
    public void addMusic(){
        assertThat(this.testRestTemplate.postForObject(this.url + "add_music?musicPath=" + this.musicPath,
                null, String.class)).contains("Saved");
    }

    @Test
    public void addMusicError() {
        this.musicPath = "/emerson/Downloads/Telegram Desktop/grandson - thoughts & prayers.mp3";
        assertNotEquals("Saved", this.testRestTemplate.postForObject(this.url + "add_music?musicPath="
                + this.musicPath, null, String.class));
    }
}