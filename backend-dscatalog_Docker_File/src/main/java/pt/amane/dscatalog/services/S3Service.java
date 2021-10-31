package pt.amane.dscatalog.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	// log para logar no sonsole de aws para acompanhar o progresso de upload..
	private static Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	// Metodo que faz o upload de MultipartFile que chegou na requisição..
	public URL uploadFile(MultipartFile file) {
		try {
			// cria a maneira de pegar o nome de ficheiro e a extensões
			// diferentes(jpg,png,etc..)
			String originName = file.getOriginalFilename();
			String extencion = FilenameUtils.getExtension(originName);
			// p+ega o instante atual com o millsegundo com a extensao
			// original(jpg,png,etc..)
			String fileName = Instant.now().toDate().getTime() + "." + extencion;

			// chama o s3
			InputStream is = file.getInputStream();
			// pega o conteudo de ficheiro com a extesao..
			String contentType = file.getContentType();

			return uploadType(is, fileName, contentType);

		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private URL uploadType(InputStream is, String fileName, String contentType) {
		ObjectMetadata meta = new ObjectMetadata();
		LOG.info("Upload start...");
		s3client.putObject(bucketName, fileName, is, meta);// pega informação..
		LOG.info("Upload Finish");
		// retorna url
		return s3client.getUrl(bucketName, fileName);
	}

	// Metodo que faz o upload dada na localização local(no meu pc)
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Upload start");
			s3client.putObject(new PutObjectRequest(bucketName, "test.jpg", file));
			LOG.info("Upload end");
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}
	}
}
