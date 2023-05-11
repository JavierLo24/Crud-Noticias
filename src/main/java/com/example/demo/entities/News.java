package com.example.demo.entities;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {

	@Id
	@SequenceGenerator(name="news_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="news_id_seq")
	private Integer id;
	
	private String titulo;
	private String desarrollo;
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categorianews categoria;
	
	private Date fecha;
	private String url;
	private String resumen;
	
	
	
	
}
