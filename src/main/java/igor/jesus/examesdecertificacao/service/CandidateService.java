package igor.jesus.examesdecertificacao.service;

import java.util.List;
import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Candidate;

public interface CandidateService {
	
    public Candidate autenticar(String email, String senha);
	
	public Candidate salvar(Candidate candidate);
	
	void delete(Candidate candidate);
	
	void validarEmail(String email);
	
	List<Candidate> buscar(Candidate candidateFiltro);
	
	Optional<Candidate> obterPorId(Long id);


}
