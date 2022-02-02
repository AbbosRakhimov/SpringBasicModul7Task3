package uz.pdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class University {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer universityId;
	
	@Column(nullable = false)
	private String uniName;
	
	@OneToOne(optional = false)
	private Adresse adresseId;

	public University(String uniName, Adresse adresse) {
		super();
		this.uniName = uniName;
		this.adresseId = adresse;
	}
	
}
