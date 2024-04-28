package net.kaopiz.internship.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "combos")
public class Combo extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Combo's name must not be blank")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "Combo's price must not be blank")
	@Column(name = "price")
	private double price;

	@Column(name = "thumbnail")
	private String thumbnail;

}
