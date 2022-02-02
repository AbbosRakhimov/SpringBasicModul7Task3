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
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.Faculty;
import uz.pdp.entity.Groups;
import uz.pdp.payload.GroupsDto;
import uz.pdp.repository.FacultyRepository;
import uz.pdp.repository.GroupsRepository;

@RestController
@RequestMapping(value = "/group")
public class GroupsContoller {

	@Autowired
	GroupsRepository groupsRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@GetMapping
	public List<Groups> geGroups(){
		return groupsRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Groups getById(@PathVariable Integer id) {
		Optional<Groups> gOptional= groupsRepository.findById(id);
		if(gOptional.isPresent()) {
			return gOptional.get();
		}
		return new Groups();
	}
	@GetMapping(value = "/byFacultyId/{facultyId}")
	public List<Groups> groups(@PathVariable Integer facultyId){
		return groupsRepository.getAllGroupsByFacultyId(facultyId);
	}
	@GetMapping(value = "/byUniversityId/{}universityId")
	public List<Groups> geGroups (@PathVariable Integer universityId){
		return groupsRepository.getAllGroupsByUniversityId(universityId);
	}
	
	@PostMapping
	public String saveGroups(@RequestBody GroupsDto groupsDto) {
		Groups groups = groupsRepository.existsByGrName(groupsDto.getGrName(), groupsDto.getFacultyId());
		if(groups!=null)
			return "Groups exist";
		Optional<Faculty> faOptional = facultyRepository.findById(groupsDto.getFacultyId());
		if(!faOptional.isPresent())
			return "Faculty not found";
		Groups groups2 = new Groups(groupsDto.getGrName(), faOptional.get());
		groupsRepository.save(groups2);
		
		return "Group saved";
	}
	@PutMapping(value = "/{id}")
	public String editGroups(@PathVariable Integer id, @RequestBody GroupsDto groupsDto) {
		Groups groups = groupsRepository.existsByGrName(groupsDto.getGrName(), groupsDto.getFacultyId());
		if(groups!=null)
			return "Groups exist";
		Optional<Groups>gOptional=groupsRepository.findById(id);
		Optional<Faculty>fOptional= facultyRepository.findById(groupsDto.getFacultyId());
		if(gOptional.isPresent() && fOptional.isPresent()) {
			Groups groups2 = gOptional.get();
			groups2.setGrName(groupsDto.getGrName());
			groups2.setFacultyId(fOptional.get());
			groupsRepository.save(groups2);
			
			return "Successfuly edited";
		}
		return "Not Found";
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteGroup(@PathVariable Integer id) {
		Optional<Groups> grOptional = groupsRepository.findById(id);
		if(grOptional.isPresent()) {
			groupsRepository.deleteById(id);
			return "deleted";
		}
	return "Group not found";
	}
}
