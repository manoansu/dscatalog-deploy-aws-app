package pt.amane.dscatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.amane.dscatalog.services.S3Service;

@SpringBootApplication
public class DscatalogApplication implements CommandLineRunner{

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(DscatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//chama a imagem para subir na nuven de aws...
		s3Service.uploadFile("C:\\Developement\\img\\aws_print.PNG");
	}

}
