package uz.pdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adresseId;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String street;

	public Adresse(String city, String street) {
		super();
		this.city = city;
		this.street = street;
	}
	
	
}
