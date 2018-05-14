package app;

import app.controller.MusicController;
import app.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;

@SpringBootApplication
public class Application{

	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}

}
