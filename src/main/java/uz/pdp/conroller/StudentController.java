package uz.pdp.conroller;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.Faculty;
import uz.pdp.entity.Groups;
import uz.pdp.entity.Student;
import uz.pdp.entity.University;
import uz.pdp.payload.FacultyDto;
import uz.pdp.payload.StudentDto;
import uz.pdp.repository.GroupsRepository;
import uz.pdp.repository.StudentRepository;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GroupsRepository geRepository;
	
	@GetMapping
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping(value ="/{id}")
	public Student getStudent(@PathVariable Integer id){
		Optional<Student> uOptional = studentRepository.findById(id);
		if(uOptional.isPresent()) {
			Student student = uOptional.get();
			return student;
		}
		return new Student();
	}
	@GetMapping(value ="/byGroupId/{groupId}")
	public List<Student> geStudentsByGroupId(@PathVariable Integer groupId){
		return studentRepository.getAllStudentByGroupId(groupId);
	}
	@GetMapping(value ="/byFacultyId/{facultyId}")
	public List<Student> geStudentsByFacultyId(@PathVariable Integer facultyId){
		return studentRepository.getAllStudentByFacultyId(facultyId);
	}
	@GetMapping(value ="/byUniversityId/{universityId}")
	public List<Student> geStudentsByUniversityId(@PathVariable Integer universityId){
		return studentRepository.getAllStudentByUnviversityIdId(universityId);
	}
	@PostMapping
	public String editUni(@RequestBody StudentDto studentDto){
		Student student = studentRepository.existsByStudentNameAndGroupId(studentDto.getStuName(), studentDto.getGroupId());
		if(student!=null)
			return "Student already exist";
		Optional<Groups> grOptional=geRepository.findById(studentDto.getGroupId());
		if(!grOptional.isPresent())
			return "Group not found";
		
		Student faculty = new Student(studentDto.getStuName(), grOptional.get());
		studentRepository.save(faculty);
		
		return "Successfuly edited";	
	}
	
	@PutMapping(value = "/{id}")
	public String putUniversi(@PathVariable Integer id, @RequestBody StudentDto studentDto){
		Student student = studentRepository.existsByStudentNameAndGroupId(studentDto.getStuName(), studentDto.getGroupId());
		if(student!=null)
			return "Student already exist";
		Optional<Student> uOptional = studentRepository.findById(id);
		Optional<Groups>gOptional= geRepository.findById(studentDto.getGroupId());
		if(uOptional.isPresent()&& gOptional.isPresent()) {
			Student faculty = uOptional.get();
			faculty.setStuName(studentDto.getStuName());
			faculty.setGroupId(gOptional.get());
			studentRepository.save(faculty);
			return "Successfuly updated";
		}
		return "Not Found Uni";
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteUniversity(@PathVariable Integer id){
		Optional<Student> uOptional = studentRepository.findById(id);
		if(uOptional.isPresent()) {
			studentRepository.deleteById(id);
			return "Successfuly deleted";
		}
		return "Not Found Faculty";
	}
}
