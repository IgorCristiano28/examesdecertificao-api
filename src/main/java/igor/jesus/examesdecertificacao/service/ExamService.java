package igor.jesus.examesdecertificacao.service;

import java.util.List;
import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Exam;



public interface ExamService {
	
	public Exam createExam(Exam exam);
	public Exam atualizar (Exam exam);
	
	void delete(Exam exam);
	void validar (Exam exam);
	
	//metodo pra buscar usuario por id, passando o id para retorna o usuario
    Optional<Exam> obterPorId(Long id);
    
  //buscar, vai retorna uma lista de exames
  	List<Exam> buscar(Exam examFiltro);
	


}
