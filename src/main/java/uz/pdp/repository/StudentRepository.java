package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uz.pdp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query(value = "select * from student st where st.stu_name=:studentName and st.group_id=:groupId",nativeQuery = true)
	Student existsByStudentNameAndGroupId(String studentName, Integer groupId);
	
	@Query(value = "select * from student st join groups gp on st.group_id=gp.group_id where gp.group_id=:groupsId", nativeQuery = true)
	List<Student> getAllStudentByGroupId(Integer groupsId);
	
	@Query(value = "select * from student st join groups gr on st.group_id=gr.group_id join faculty fk on gr.faculty_id=fk.faculty_id where fk.faculty_id=:facultyId", nativeQuery = true)
	List<Student> getAllStudentByFacultyId(Integer facultyId);
	
	@Query(value = "select * from student st join groups gr on st.group_id=gr.group_id join faculty fk on gr.faculty_id=fk.faculty_id join university u on fk.university_id=u.university_id where u.university_id=:groupsId", nativeQuery = true)
	List<Student> getAllStudentByUnviversityIdId(Integer groupsId);
}
