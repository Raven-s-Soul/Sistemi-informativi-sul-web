package it.uniroma3.siw.model.entities;

import jakarta.persistence.*;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nameFile;
	private String path;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@OneToOne
	@JoinColumn(name = "author_id")
	private Author autore;

	// Getter e Setter
	public Long getId() {
		return id;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nameFile = nomeFile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Book getLibro() {
		return book;
	}

	public void setLibro(Book book) {
		this.book = book;
	}

	public Author getAutore() {
		return autore;
	}

	public void setAutore(Author autore) {
		this.autore = autore;
	}
}