package net.kaopiz.internship.demo.entity;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City extends AbstractBase {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "city_name")
	private String cityName;

	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<Cinema> cinemas;

}
