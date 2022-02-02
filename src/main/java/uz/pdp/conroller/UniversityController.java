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

import lombok.Delegate;
import uz.pdp.entity.Adresse;
import uz.pdp.entity.University;
import uz.pdp.payload.UniversityDto;
import uz.pdp.repository.AdresseRepository;
import uz.pdp.repository.UniversityRepository;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {

	@Autowired
	UniversityRepository universityRepository;
	
	@Autowired
	AdresseRepository adresseRepository;
	
	@GetMapping
	public List<University> getUniversities(){
		return universityRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public University getUniversity(@PathVariable Integer id){
		Optional<University> uOptional = universityRepository.findById(id);
		if(uOptional.isPresent()) {
			University university = uOptional.get();
			return university;
		}
		return new University();
	}
	
	@PostMapping
	public String editUni(@RequestBody UniversityDto universityDto){
		if(universityRepository.existsByUniName(universityDto.getUniName()))
			return "Uni alrady exist";
		Adresse adresse = new Adresse(universityDto.getCity(), universityDto.getStreet());
		Adresse adresse2 = adresseRepository.save(adresse);
		University university = new University(universityDto.getUniName(), adresse2);
		universityRepository.save(university);
		
		return "Successfuly edited";	
	}
	
	@PutMapping(value = "/{id}" )
	public String putUniversi(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
		if(universityRepository.existsByUniName(universityDto.getUniName()))
			return "Uni exist";
		Optional<University> uOptional = universityRepository.findById(id);
		if(uOptional.isPresent()) {
			University university = uOptional.get();
			Adresse adresse = university.getAdresseId();
			university.setUniName(universityDto.getUniName());
			adresse.setCity(universityDto.getCity());
			adresse.setStreet(universityDto.getStreet());
			adresseRepository.save(adresse);
			universityRepository.save(university);
			return "Successfuly updated";
		}
		return "Not Found Uni";
	}
	
	@DeleteMapping(value = "/{id}" )
	public String deleteUniversity(@PathVariable Integer id){
		Optional<University> uOptional = universityRepository.findById(id);
		if(uOptional.isPresent()) {
			universityRepository.deleteById(id);
			return "Successfuly deleted";
		}
		return "Not Found Uni";
	}
}
