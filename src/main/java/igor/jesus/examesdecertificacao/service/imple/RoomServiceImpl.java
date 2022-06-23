package igor.jesus.examesdecertificacao.service.imple;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igor.jesus.examesdecertificacao.model.entity.Room;
import igor.jesus.examesdecertificacao.model.repository.RoomRepository;
import igor.jesus.examesdecertificacao.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	
	private RoomRepository repository;
	
	@Autowired
	public RoomServiceImpl(RoomRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Room createRoom(Room room) {
		
		return repository.save(room);
	}

	@Override
	public void delete(Room room) {
		Objects.requireNonNull(room.getId());
		repository.delete(room);
		
	}

	@Override
	public Optional<Room> obterPorId(Long id) {
		
		return repository.findById(id);
	}

	
	
	
	
		
	




}
