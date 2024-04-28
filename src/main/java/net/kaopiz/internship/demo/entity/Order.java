package net.kaopiz.internship.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "status")
	private String status;

	@Column(name = "number_of_combos")
	private int number_of_combos;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
