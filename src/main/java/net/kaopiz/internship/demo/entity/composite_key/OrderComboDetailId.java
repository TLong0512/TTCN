package net.kaopiz.internship.demo.entity.composite_key;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderComboDetailId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long order;

	private Long combo;

}
