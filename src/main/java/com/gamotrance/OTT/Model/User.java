package com.gamotrance.OTT.Model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Document
public class User implements Serializable {
    public User(int id, String phone, UserType userType) {
		super();
		this.id = id;
		this.phone = phone;
		this.userType = userType;
	}

	public User(int id, @NotNull String name, String email, String expdate, String password, String phone,
			String profileUri, LocalDate createdAt, LocalDate updatedAt, LocalDate suscriptionDate, Payment payments,
			UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.expdate = expdate;
		this.password = password;
		this.phone = phone;
		this.profileUri = profileUri;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.suscriptionDate = suscriptionDate;
		this.payments = payments;
		this.userType = userType;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String name;
    private boolean enabled;
	@DBRef
	private Set<Role> roles;
    
    public User(int id, @NotNull String name, boolean enabled, Set<Role> roles, String email, int userAgeRest,
			String expdate, String password, String phone, List<Device> devices, String profileUri, LocalDate createdAt,
			LocalDate updatedAt, LocalDate suscriptionDate, Payment payments, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.roles = roles;
		this.email = email;
		this.userAgeRest = userAgeRest;
		this.expdate = expdate;
		this.password = password;
		this.phone = phone;
		this.devices = devices;
		this.profileUri = profileUri;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.suscriptionDate = suscriptionDate;
		this.payments = payments;
		this.userType = userType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	private String email;
    private int userAgeRest;
private String expdate;
    

	public int getUserAgeRest() {
	return userAgeRest;
}

public void setUserAgeRest(int userAgeRest) {
	this.userAgeRest = userAgeRest;
}

	public User(int id, @NotNull String name, String email, int userAgeRest, String expdate, String password, String phone,
		List<Device> devices, String profileUri, LocalDate createdAt, LocalDate updatedAt, LocalDate suscriptionDate,
		Payment payments, UserType userType) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.userAgeRest = userAgeRest;
	this.expdate = expdate;
	this.password = password;
	this.phone = phone;
	this.devices = devices;
	this.profileUri = profileUri;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.suscriptionDate = suscriptionDate;
	this.payments = payments;
	this.userType = userType;
}

	public String getExpdate() {
	return expdate;
}

public void setExpdate(String expdate) {
	this.expdate = expdate;
}

	private String password;
    private String phone;
    private List<Device> devices;
    public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	

	private String profileUri;
    @Column(name = "createdAt", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDate createdAt;

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDate getSuscriptionDate() {
		return suscriptionDate;
	}

	public void setSuscriptionDate(LocalDate suscriptionDate) {
		this.suscriptionDate = suscriptionDate;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(String profileUri) {
        this.profileUri = profileUri;
    }


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Column(name = "updatedAt", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    @UpdateTimestamp
    private LocalDate updatedAt;
  
    @Column(name = "suscriptionDate", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDate suscriptionDate;
    
    
    private Payment payments;
  

	public Payment getPayments() {
		return payments;
	}

	public void setPayments(Payment payments) {
		this.payments = payments;
	}

	private UserType userType;
    public User() {
    }

}
