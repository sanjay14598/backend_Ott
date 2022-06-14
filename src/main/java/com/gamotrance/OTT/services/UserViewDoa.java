package com.gamotrance.OTT.services;

import com.gamotrance.OTT.Model.UserView;

public interface UserViewDoa {
	 UserView addUserView(UserView userView);
	 UserView updateUserView(UserView userView);
	 UserView getUserViewById(Integer id);
}
