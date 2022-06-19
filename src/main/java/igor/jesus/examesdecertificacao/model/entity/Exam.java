package igor.jesus.examesdecertificacao.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam", schema = "certificacao")
public class Exam {
	
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column (name= "descricao")
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;
	
	@OneToOne
	@JoinColumn(name = "id_availabity")
	private Availability availability;
	
	@Column (name =  "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;

	

}
