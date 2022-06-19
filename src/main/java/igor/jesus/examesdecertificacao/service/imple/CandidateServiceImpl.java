package igor.jesus.examesdecertificacao.service.imple;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igor.jesus.examesdecertificacao.exception.ErroAutenticacao;
import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Candidate;
import igor.jesus.examesdecertificacao.model.repository.CandidateRepository;
import igor.jesus.examesdecertificacao.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{

	
	private CandidateRepository repository;
	
	@Autowired
	public CandidateServiceImpl(CandidateRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Candidate autenticar(String email, String senha) {
       Optional<Candidate> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao ("Candidato não encontrado para o email informado.");
			
		}
		
		if(usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao ("Senha inválida.");
			
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Candidate salvarCandidato(Candidate candidate) {
		validarEmail(candidate.getEmail());
		return repository.save(candidate);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um candidato cadastrado com este email.");
			
		}
		
	}

	@Override
	public Optional<Candidate> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

}
