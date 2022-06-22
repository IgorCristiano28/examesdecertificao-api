package igor.jesus.examesdecertificacao.service.imple;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.repository.AvailabilityRepository;
import igor.jesus.examesdecertificacao.service.AvailabilityService;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

	
	private AvailabilityRepository repository;
	
	@Autowired
	public AvailabilityServiceImpl(AvailabilityRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	@Override
	public Optional<Availability> obterPorId(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Availability salvar(Availability availability) {
		
		return repository.save(availability);
	}

	@Override
	public void delete(Availability availability) {
		Objects.requireNonNull(availability.getId());
		repository.delete(availability);
		
	}

	

	


}
