package io.swagger.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_task")
@ToString
public class TaskEntity {
	
	@Id
	@SequenceGenerator(
			name = "task_sequence",
			sequenceName = "task_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "task_sequence"
			)
	long taskId;
	
	String task;
	String description;
	int priority;
	
	int status;
	
	@ToString.Exclude
    @ManyToOne
    @JoinColumn(name="user_id"
    , nullable=false)
    private UserEntity user;
	
}
