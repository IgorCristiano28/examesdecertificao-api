package igor.jesus.examesdecertificacao.service;

import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Candidate;

public interface CandidateService {
	
    Candidate autenticar(String email, String senha);
	
	Candidate salvarCandidato(Candidate candidate);
	
	void validarEmail(String email);
	
	 //metodo pra buscar usuario por id, passando o id para retorna o usuario
	 Optional<Candidate> obterPorId(Long id);


}
