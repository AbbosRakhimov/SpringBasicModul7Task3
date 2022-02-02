package uz.pdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import uz.pdp.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	@Query(value = "select * from subject st where st.su_name=:suName and st.group_id=:groupId",nativeQuery = true)
	Subject existsBySubjectNameAndGroupId(String suName, Integer groupId);
	
	@Query(value = "select * from subject st join groups gp on st.group_id=gr.group_id where gr.group_id=:groupsId", nativeQuery = true)
	List<Subject> getAllSubjectByGoupsId(Integer groupsId);
}
