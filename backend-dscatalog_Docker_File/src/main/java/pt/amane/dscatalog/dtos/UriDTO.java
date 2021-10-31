package pt.amane.dscatalog.dtos;

import java.io.Serializable;

// sqao corpo da resposta de imagem par aws..
public class UriDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// endereço da imagem de aws..
	private String uri;

	public UriDTO() {
	}

	public UriDTO(String uri) {
		super();
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
