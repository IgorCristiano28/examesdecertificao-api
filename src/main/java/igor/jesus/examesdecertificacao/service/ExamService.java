package igor.jesus.examesdecertificacao.service;

import java.util.List;
import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Exam;



public interface ExamService {
	
	public Exam salvar(Exam exam);
	public Exam atualizar (Exam exam);
	
	void delete(Exam exam);
	void validar (Exam exam);
	
	
    Optional<Exam> obterPorId(Long id);
    
    //Retorna uma lista de exames
  	List<Exam> buscar(Exam examFiltro);
	


}
