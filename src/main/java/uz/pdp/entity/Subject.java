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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"su_name","group_id"}))
public class Subject {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer subjectId;
	
	@Column(nullable = false, name = "su_name")
	private String suName;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Groups groupId;

	public Subject(String suName, Groups groups) {
		super();
		this.suName = suName;
		this.groupId = groups;
	}
	
	
}
