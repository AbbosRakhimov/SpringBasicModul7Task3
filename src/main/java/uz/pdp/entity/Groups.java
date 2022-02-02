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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"gr_name","faculty_id"}))
public class Groups {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupId;
	
	@Column(nullable = false, name = "gr_name")
	private String grName;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Faculty facultyId;

	public Groups(String grName, Faculty faculty) {
		super();
		this.grName = grName;
		this.facultyId = faculty;
	}	
	
}
