package pt.amane.dscatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscatalogApplication { //implements CommandLineRunner{

//	@Autowired
//	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(DscatalogApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		//chama a imagem para subir na nuven de aws...
//		s3Service.uploadFile("C:\\Developement\\img\\aws_print.PNG");
//	}

}
