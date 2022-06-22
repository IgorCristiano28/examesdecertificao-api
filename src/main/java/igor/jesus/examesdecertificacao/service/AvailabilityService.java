package igor.jesus.examesdecertificacao.service;

import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Availability;

public interface AvailabilityService {
	
	public Availability salvar(Availability availability);
	
	void delete(Availability availability);
	
	//metodo pra buscar usuario por id, passando o id para retorna o usuario
    Optional<Availability> obterPorId(Long id);
	


}
