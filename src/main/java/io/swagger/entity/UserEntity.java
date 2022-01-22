package io.swagger.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
		name = "tbl_user",
		uniqueConstraints = @UniqueConstraint(
				name = "email_unique",
				columnNames = "email_address"
				)
		)
@ToString
public class UserEntity {

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
			)
	long userId;
	String username;
	
	@Column(name = "email_address",
			nullable = false)
	String email;
	
	@Exclude String password;
	
	@OneToMany(mappedBy = "user"
			, fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks;
}
