package com.gamotrance.OTT.services;

import com.gamotrance.OTT.Model.*;

import java.util.List;

public interface UserServicesDoa {
	User getValidate(String userName, String password);

	User getUser(String username);

	User updateUser(User user);

	User createAccount(User user);

	boolean deleteUser(User user);

	User getSubUser(int id);

	List<User> getAllUser();

	User getUserOtpVerification(String email, String otp);

	User getRequestUserOtp(String email);

	boolean checkIfUserExist(String email);

	String getConvertUser(Long id, String uri);

	// List<User> getAllUserbypayments();

	boolean deleteAllUser();

	User getUserByEmail(String email);

	boolean getPhoneExist(String number);

	// Document
	UserDocument addUserDocument(UserDocument userDocument);

	void deleteUserDocument(UserDocument userDocument);

	UserDocument updateUserDocument(UserDocument userDocument);

	List<UserDocument> getAllUserDocumentbyUserId(Long userId);

	UserDocument getUserDocumentsbyId(int id);

	User getPhoneVerify(String phone);



}
