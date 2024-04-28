package net.kaopiz.internship.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractBase implements UserDetails {

	@NotEmpty(message = "Username must not be empty")
	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not format")
	@Column(name = "email", nullable = false)
	private String email;

	@NotEmpty(message = "Password must not be empty")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-=\\[\\]{};:'\"<>,.?/]).{8,}$", message = "Password is not valid")
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "full_name")
	private String fullName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String gender;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "address")
	private String address;

	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 characters")
	@Column(name = "phone_number")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String phoneNumber;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "code")
	private String code;

	@Column(name = "login_count")
	private Long logInCount;

	@Column(name = "total_spend")
	private BigDecimal totalSpent;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("users")
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (this.getActiveFlag() == null) {
			return false;
		}
		if (this.getActiveFlag() == false) {
			return false;
		}
		return true;
	}

}
