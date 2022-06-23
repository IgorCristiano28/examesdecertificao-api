package igor.jesus.examesdecertificacao.service;

import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.enums.StatusAvailability;

public interface AvailabilityService {
	
	public Availability salvar(Availability availability);
	
	void delete(Availability availability);
	
	//metodo pra buscar usuario por id, passando o id para retorna o usuario
    Optional<Availability> obterPorId(Long id);
	
    void validar (Availability availability);
    
    //Tem q estar com id
  	Availability atualizar (Availability availability);
    
    void atualizarStatus(Availability lancamento, StatusAvailability status);


}
