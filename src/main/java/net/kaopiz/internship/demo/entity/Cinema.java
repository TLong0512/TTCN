package net.kaopiz.internship.demo.entity;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinemas")
public class Cinema extends AbstractBase {

	@Column(name = "cinema_name", unique = true)
	private String cinemaName;

	@Column(name = "cinema_location")
	private String location;

	@Column(name = "cinema_phone_number")
	private String phoneNumber;

	@Column(name = "cinema_description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "cinema_code")
	private String code;

	@Column(name = "cinema_revenue")
	private String revenue;

	@Column(name = "cinema_owner")
	private String owner;

	@JsonIgnore
	@OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToStringExclude
	private Collection<CinemaRoom> cinemaRooms;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_city_id")
	private City city;

}
