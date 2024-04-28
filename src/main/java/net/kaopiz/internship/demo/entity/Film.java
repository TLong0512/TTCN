package net.kaopiz.internship.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "films")
public class Film extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Film's name must not be blank")
	@Column(name = "name", unique = true)
	private String name;

	@NotBlank(message = "Film's length must not be blank")
	@Column(name = "length")
	private String length;

	@Column(name = "started_date")
	private Date startedDate;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "trailer")
	private String trailer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "views")
	private Long views;

}
