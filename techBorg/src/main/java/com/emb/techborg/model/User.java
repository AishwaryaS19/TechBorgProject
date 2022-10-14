package com.emb.techborg.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails  {
	
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private int id;

    //@Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
    @Pattern(regexp = "^[a-zA-Z ]{3,10}+$", message = "Invalid first name!(3-10 characters)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z ]{3,10}+$", message = "Invalid last name!(3-10 characters)")
    private String lastName;

    //@Email(message = "Invalid email address!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email address!")
    @Column(unique = true)
    private String email;

    @Length(min = 8, message = "Password should be atleast 8 characters long")
    private String password;

    //@Length(min = 10, message = "Mobile number should be atleast 10 number long")
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid mobile number.")
    @Column(unique = true)
    private String mobile;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean locked = false;

    private Boolean enabled = true;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Role getRole() { 
    	return role; 
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() { 
    	return email; 
    }

    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getFirstName() { 
    	return firstName; 
   	}

    public void setFirstName(String firstName) { 
    	this.firstName = firstName;
   	}

    public String getMobile() { 
    	return mobile; 
    	}

    public void setMobile(String mobile) { 
    	this.mobile = mobile; 
   	}

    public String getLastName() { 
    	return lastName;
    }

    public void setLastName(String lastName) { 
    	this.lastName = lastName; 
    }
}