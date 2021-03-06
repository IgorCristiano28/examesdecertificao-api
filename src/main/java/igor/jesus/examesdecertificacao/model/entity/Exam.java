package igor.jesus.examesdecertificacao.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "id_availabity")
	private Availability availability;
	

	

}
