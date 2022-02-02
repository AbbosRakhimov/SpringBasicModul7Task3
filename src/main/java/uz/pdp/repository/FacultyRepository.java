package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uz.pdp.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	
	@Query(value = "select * from faculty st where st.fa_name=:faName and st.university_id=:universityId",nativeQuery = true)
	Faculty existsByFaName(String faName, Integer universityId);
	
	@Query(value = "select * from faculty fk join university un on fk.university_id=un.university_id where un.university_id=:universityId ", nativeQuery = true)
	List<Faculty>getAllFacultyByUniId(Integer universityId);
}
