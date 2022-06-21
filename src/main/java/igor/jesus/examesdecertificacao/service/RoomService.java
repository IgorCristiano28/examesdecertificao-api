package igor.jesus.examesdecertificacao.service;

import java.util.Optional;

import igor.jesus.examesdecertificacao.model.entity.Room;

public interface RoomService {
	
	public Room createRoom(Room room);
	
	void delete(Room room);
	
	//metodo pra buscar usuario por id, passando o id para retorna o usuario
    Optional<Room> obterPorId(Long id);
	


}
