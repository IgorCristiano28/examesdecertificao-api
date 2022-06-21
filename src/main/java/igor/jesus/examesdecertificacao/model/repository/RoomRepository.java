package igor.jesus.examesdecertificacao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import igor.jesus.examesdecertificacao.model.entity.Availability;
import igor.jesus.examesdecertificacao.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	


}
