package net.kaopiz.internship.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kaopiz.internship.demo.entity.composite_key.OrderComboDetailId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderComboDetailId.class)
@Table(name = "order_combo_details")
public class Order_Combo_Detail extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "combo_id")
	private Combo combo;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	private Integer comboQuantity;

	private Double price;

}
