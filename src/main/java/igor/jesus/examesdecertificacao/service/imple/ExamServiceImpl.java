package igor.jesus.examesdecertificacao.service.imple;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import igor.jesus.examesdecertificacao.exception.RegraNegocioException;
import igor.jesus.examesdecertificacao.model.entity.Exam;
import igor.jesus.examesdecertificacao.model.repository.ExamRepository;
import igor.jesus.examesdecertificacao.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

	
	private ExamRepository repository;
	
	@Autowired
	public ExamServiceImpl(ExamRepository repository) {
		this.repository = repository;
	}

	
	@Override
	@Transactional
	public Exam salvar(Exam exam) {
		validar(exam);
		return repository.save(exam);
	}

	@Override
	public void delete(Exam exam) {
		Objects.requireNonNull(exam.getId());
		repository.delete(exam);
	}

	@Override
	public Optional<Exam> obterPorId(Long id) {
		
		return repository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Exam> buscar(Exam examFiltro) {
		Example example = Example.of(examFiltro,ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		
		return repository.findAll(example);
	}


	@Override
	@Transactional
	public Exam atualizar(Exam exam) {
		Objects.requireNonNull(exam.getId());
		validar(exam);
		return repository.save(exam);
	}


	@Override
	public void validar(Exam exam) {
		
		if(exam.getDescricao() == null || exam.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida");
				
		}
	}
	



}
