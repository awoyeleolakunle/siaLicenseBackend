package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import dansarkitechnology.sialicensebackend.data.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByQuestion(String question);
    List<Question> findAllByExamType(ExamType examType);
}
