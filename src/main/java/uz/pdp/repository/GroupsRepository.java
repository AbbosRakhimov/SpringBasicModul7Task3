package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import uz.pdp.entity.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

	@Query(value = "select * from groups st where st.gr_name=:grName and st.faculty_id=:facultyId",nativeQuery = true)
	Groups existsByGrName(String grName, Integer facultyId);
	
	@Query(value = " select * from groups gp join faculty fk on gr.faculty_id=fk.faculty_id where fk.faculty_id=:facultyId",nativeQuery = true)
	List<Groups> getAllGroupsByFacultyId(Integer facultyId);
	
	@Query(value = "select * from groups gp join faculty fk on gr.faculty_id=fk.faculty_id join university u on fk.university_id=u.university_id where u.university_id=:universityId", nativeQuery = true)
	List<Groups> getAllGroupsByUniversityId(Integer universityId);
}
