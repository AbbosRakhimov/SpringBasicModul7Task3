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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"te_name","group_id"}))
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherId;
	
	@Column(nullable = false, name = "te_name")
	private String teName;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Groups groupId;

	public Teacher(String teName, Groups groups) {
		super();
		this.teName = teName;
		this.groupId = groups;
	}
	
}
