package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import uz.pdp.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query(value = "select * from teacher st where st.te_name=:teName and st.group_id=:groupId",nativeQuery = true)
	Teacher existsByTeacherNameAndGroupId(String teName, Integer groupId);
	
	@Query(value = "select * from teacher st join groups gp on st.group_id=gr.group_id where gr.group_id=:groupsId", nativeQuery = true)
	List<Teacher> getAllTeacherByGoupsId(Integer groupsId);
}
