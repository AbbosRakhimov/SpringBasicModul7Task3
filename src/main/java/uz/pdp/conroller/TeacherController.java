package uz.pdp.conroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.Groups;
import uz.pdp.entity.Student;
import uz.pdp.entity.Teacher;
import uz.pdp.payload.StudentDto;
import uz.pdp.payload.TeacherDto;
import uz.pdp.repository.GroupsRepository;
import uz.pdp.repository.TeacherRepository;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	GroupsRepository groupsRepository;
	
	@GetMapping
	public List<Teacher> getFaculties(){
		return teacherRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Teacher getFaculty(@PathVariable Integer id){
		Optional<Teacher> uOptional = teacherRepository.findById(id);
		if(uOptional.isPresent()) {
			Teacher university = uOptional.get();
			return university;
		}
		return new Teacher();
	}
	@GetMapping(value = "/byGroupsId/{groupId}")
	public List<Teacher> getTeacherByGroupsId(@PathVariable Integer groupId){
		return teacherRepository.getAllTeacherByGoupsId(groupId);
	}
	
	@PostMapping
	public String editUni(@RequestBody TeacherDto teacherDto){
		Teacher teacher = teacherRepository.existsByTeacherNameAndGroupId(teacherDto.getTeName(), teacherDto.getGroupId());
		if(teacher!=null)
			return "Teacher already exist";
		Optional<Groups> gOptional= groupsRepository.findById(teacherDto.getGroupId());
		if(!gOptional.isPresent())
			return "Group not found";
		
		Teacher faculty = new Teacher(teacherDto.getTeName(), gOptional.get());
		teacherRepository.save(faculty);
		
		return "Successfuly edited";	
	}
	
	@PutMapping(value = "/{id}")
	public String putUniversi(@PathVariable Integer id, @RequestBody TeacherDto teacherDto){
		Teacher teacher = teacherRepository.existsByTeacherNameAndGroupId(teacherDto.getTeName(), teacherDto.getGroupId());
		if(teacher!=null)
			return "Teacher already exist";
		Optional<Teacher> uOptional = teacherRepository.findById(id);
		Optional<Groups> gOptional= groupsRepository.findById(teacherDto.getGroupId());
		if(uOptional.isPresent()&& gOptional.isPresent()) {
			Teacher faculty = uOptional.get();
			faculty.setTeName(teacherDto.getTeName());
			faculty.setGroupId(gOptional.get());
			teacherRepository.save(faculty);
			return "Successfuly updated";
		}
		return "Not Found Uni";
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteUniversity(@PathVariable Integer id){
		Optional<Teacher> uOptional = teacherRepository.findById(id);
		if(uOptional.isPresent()) {
			teacherRepository.deleteById(id);
			return "Successfuly deleted";
		}
		return "Not Found Faculty";
	}
}
