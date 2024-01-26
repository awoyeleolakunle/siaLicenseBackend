package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
