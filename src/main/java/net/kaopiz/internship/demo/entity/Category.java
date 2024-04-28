package net.kaopiz.internship.demo.entity;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends AbstractBase {

	@NotBlank(message = "Category's name must not blank")
	@Column(name = "name")
	private String name;

//	@Lob
//	@NotBlank(message = "Category's description must not blank")
//	private String description;

	@ManyToOne
	@JoinColumn(name = "parent_category")
	private Category parent_category;

	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<Film> films;

}
