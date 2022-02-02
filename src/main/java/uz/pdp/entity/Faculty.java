package uz.pdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fa_name","university_id"}))
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facultyId;
	
	@Column(nullable = false, name = "fa_name")
	private String faName;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private University university;

	public Faculty(String faName, University university) {
		super();
		this.faName = faName;
		this.university = university;
	}
	
}
