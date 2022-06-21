package igor.jesus.examesdecertificacao.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import igor.jesus.examesdecertificacao.model.enums.StatusAvailability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "availabity", schema = "certificacao")
public class Availability {
	
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_room")
	private Room room;
	
	@Column (name= "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	

}
