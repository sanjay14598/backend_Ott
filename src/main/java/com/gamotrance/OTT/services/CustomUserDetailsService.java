package com.gamotrance.OTT.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamotrance.OTT.Doa.RoleRepository;
import com.gamotrance.OTT.Doa.UserRepo;
import com.gamotrance.OTT.Model.Role;
import com.gamotrance.OTT.Model.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	public User findUserByPhone(String phone) {
	    return userRepository.findByPhone(phone);
	}
	
	public void saveUser(User user) {
		user.setId(user.getPhone().hashCode());
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    String role="";
	    for(Role r:user.getRoles())
	    {
	    	role=r.getRole();
	    }
	   
	    Role userRole = roleRepository.findByRole(role);
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	public void updateUser(User user,String password) {
		//user.setId(user.getPhone().hashCode());
	    user.setPassword(bCryptPasswordEncoder.encode(password));
//	    user.setEnabled(true);
//	    String role="";
//	    for(Role r:user.getRoles())
//	    {
//	    	role=r.getRole();
//	    }
//	   
//	    Role userRole = roleRepository.findByRole(role);
//	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    User user = userRepository.findByPhone(email);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), authorities);
	}
}
