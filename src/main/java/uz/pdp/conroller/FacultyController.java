package uz.pdp.conroller;

import java.util.List;
import java.util.Optional;

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

import lombok.experimental.PackagePrivate;
import uz.pdp.entity.Faculty;
import uz.pdp.entity.University;
import uz.pdp.payload.FacultyDto;
import uz.pdp.repository.UniversityRepository;
import uz.pdp.repository.FacultyRepository;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

	@Autowired
	UniversityRepository universityRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@GetMapping
	public List<Faculty> getFaculties(){
		return facultyRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Faculty getFaculty(@PathVariable Integer id){
		Optional<Faculty> uOptional = facultyRepository.findById(id);
		if(uOptional.isPresent()) {
			Faculty university = uOptional.get();
			return university;
		}
		return new Faculty();
	}
	@GetMapping(value = "/byUniversityId/{universityId}")
	public List<Faculty> getAllFAcultyByUniversityId(@PathVariable Integer universityId){
		return facultyRepository.getAllFacultyByUniId(universityId);
	}
	
	@PostMapping
	public String editUni(@RequestBody FacultyDto facultyDto){
		Faculty faculty = facultyRepository.existsByFaName(facultyDto.getFaName(), facultyDto.getUniversityId());
		if(faculty!=null)
			return "Faculty already exist";
		Optional<University> uOptional = universityRepository.findById(facultyDto.getUniversityId());
		if(!uOptional.isPresent())
			return "University not found";
		
		Faculty faculty2 = new Faculty(facultyDto.getFaName(), uOptional.get());
		facultyRepository.save(faculty2);
		
		return "Successfuly edited";	
	}
	
	@PutMapping(value = "/{id}")
	public String putUniversi(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
		Faculty faculty = facultyRepository.existsByFaName(facultyDto.getFaName(), facultyDto.getUniversityId());
		if(faculty!=null)
			return "Faculty already exist";
		Optional<Faculty> fOptional = facultyRepository.findById(id);
		Optional<University> uOptional= universityRepository.findById(facultyDto.getUniversityId()); 
		if(fOptional.isPresent() && uOptional.isPresent()) {
			Faculty faculty2 = fOptional.get();
			faculty2.setFaName(facultyDto.getFaName());
			faculty2.setUniversity(uOptional.get());
			facultyRepository.save(faculty2);
			return "Successfuly updated";
		}
		return "Not Found Uni";
	}
	
	@DeleteMapping(value ="/{id}")
	public String deleteUniversity(@PathVariable Integer id){
		Optional<Faculty> uOptional = facultyRepository.findById(id);
		if(uOptional.isPresent()) {
			facultyRepository.deleteById(id);
			return "Successfuly deleted";
		}
		return "Not Found Faculty";
	}
}
