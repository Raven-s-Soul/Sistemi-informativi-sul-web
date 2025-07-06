package it.uniroma3.siw.model.entities;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(nullable = true)
	private byte[] image;
	
	@Transient
	private MultipartFile imageFile; //Transiente per upload di immagini

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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Author getAutore() {
		return autore;
	}

	public void setAutore(Author autore) {
		this.autore = autore;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
}