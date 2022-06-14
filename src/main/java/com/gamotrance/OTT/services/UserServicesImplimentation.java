package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.gamotrance.OTT.OttUtil;
import com.gamotrance.OTT.Doa.*;
import com.gamotrance.OTT.Model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserServicesImplimentation implements UserServicesDoa {
	@Autowired
	UserRepo userRepo;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public User getValidate(String userName, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("phone").is(userName).andOperator(Criteria.where("password").is(password)));
		User userTest2 = mongoTemplate.findOne(query, User.class);
		System.out.println("userTest2 - " + userTest2);
		// TODO Auto-generated method stub
		return userTest2;
	}

	@Override
	public boolean checkIfUserExist(String email) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("phone").is(email));
		boolean user1 = mongoTemplate.exists(query, User.class);
		System.out.println("userTest2 - " + user1);
		if (user1) {
			return true;
		} else {
			return false;
		}
	}

	public Role getRole(String r) {
		return roleRepository.findByRole(r);
	}

	@Override
	public User getPhoneVerify(String phone) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("phone").is(phone));
		User user1 = mongoTemplate.findOne(query, User.class);
		return user1;
	}

	@Override
	public String getConvertUser(Long id, String uri) {
		return null;
	}

	@Override
	public UserDocument addUserDocument(UserDocument userDocument) {
		return null;
	}

	@Override
	public void deleteUserDocument(UserDocument userDocument) {

	}

	@Override
	public UserDocument updateUserDocument(UserDocument userDocument) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userDocument.getId()));
		UserDocument user1 = mongoTemplate.findOne(query, UserDocument.class);
		System.out.println("payment is on flow - " + user1);
		if (user1 != null) {
			return mongoTemplate.save(userDocument);
		}
		return null;
	}

	@Override
	public List<UserDocument> getAllUserDocumentbyUserId(Long userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		List<UserDocument> user1 = mongoTemplate.find(query, UserDocument.class);
		return user1;
	}

	@Override
	public UserDocument getUserDocumentsbyId(int id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		UserDocument user1 = mongoTemplate.findOne(query, UserDocument.class);
		return user1;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(user.getName()));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("user Update - " + user1);
		if (user1 != null) {
			return mongoTemplate.save(user);
		}
		return null;
	}

	public User updateUserAgeRestc(int id, int age) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("user Update - " + user1);
		if (user1 != null) {
			user1.setUserAgeRest(age);
			return mongoTemplate.save(user1);
		}
		return null;
	}

	public List<User> getUserByDate(LocalDate ld) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("createdAt").is(ld));
		List<User> user1 = mongoTemplate.find(query, User.class);
		System.out.println("user Update - " + user1);

		return user1;
	}

	public List<User> getUserByDateSubscription(LocalDate ld) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("payments.paymentDate").is(ld));
		List<User> user1 = mongoTemplate.find(query, User.class);
		System.out.println("user Update - " + user1);

		return user1;
	}

	public User updateUserWithId(User user) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(user.getId()));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("user Update - " + user1);
		if (user1 != null) {
			return mongoTemplate.save(user);
		}
		return null;
	}

	@Override
	public User createAccount(User user) {
		User user1 = new User(user.getPhone().hashCode(), user.getName(), user.getEmail(), user.getUserAgeRest(),
				user.getExpdate(), user.getPassword(), user.getPhone(), user.getDevices(), user.getProfileUri(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), user.getPayments(), user.getUserType());

		return userRepo.save(user1);
	}

	public void saveAll(List<User> user) {
		userRepo.saveAll(user);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepo.delete(user);
		return true;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		User user = mongoTemplate.findOne(query, User.class);

		return user;
	}

	@Override
	public User getSubUser(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User user = mongoTemplate.findById(id, User.class);
		System.out.println("userTest2 - " + user);
		return user;
	}

	public User getUserbyPassword(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User user = mongoTemplate.findById(id, User.class);
		System.out.println("userTest2 - " + user);
		return user;
	}

	public User deleteUserById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User user = mongoTemplate.findById(id, User.class);
		if (user != null) {
			userRepo.delete(user);
		}
		System.out.println("userTest2 - " + user);
		return user;
	}

	public List<User> getUserByuserType(String type) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("userType").is(type));
		List<User> user = mongoTemplate.find(query, User.class);
		System.out.println("userTest2 - " + user);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public User getUserOtpVerification(String email, String otp) {
//        // TODO Auto-generated method stub
//        Query query = new Query();
//        query.addCriteria(Criteria.where("email").is(email));
//        Otp otp1 = mongoTemplate.findOne(query, Otp.class);
//        User user=mongoTemplate.findOne(query, User.class);
//        if(otp1.getOtpValue().equalsIgnoreCase(otp))
//        {
//            user.setEmailVerified(true);
//        }
//        User user1=mongoTemplate.save(user);
//        System.out.println("the user is otp verified"+user.isEmailVerified());
		return null;
	}

	@Override
	public User getRequestUserOtp(String email) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("email").is(email));
//        User user = mongoTemplate.findOne(query, User.class);
//        // TODO Auto-generated method stub
//        Otp otp=new Otp(email.hashCode(), user.getPhone(), ApplicationUtils.OTP(4), user.getEmail());
//        otpRepo.save(otp);
//        ApplicationUtils.getOtpProvider(user, otp.getOtpValue());
//        System.out.println("the request gen:"+user.isEmailVerified());
		return null;
	}

	@Override
	public boolean deleteAllUser() {
		// TODO Auto-generated method stub
		userRepo.deleteAll();
		return true;
	}

	public boolean syncAll(List<User> user) {
		// TODO Auto-generated method stub
		userRepo.saveAll(user);
		return true;
	}

	public void validateUser() {
		List<User> allUser = userRepo.findAll();
		for (User user : allUser) {
			if (user.getPayments() != null) {
				System.out.println("paymentDate:" + user.getPayments().getPaymentDate());
				LocalDate ld = user.getPayments().getPaymentDate();
				System.out.println("Current Date:" + ld.plusDays(new OttUtil().getValue(user.getUserType()))
						+ "most current date:" + LocalDate.now());
				if (LocalDate.now().equals(ld.plusDays(new OttUtil().getValue(user.getUserType())))) {
					Payment pm = new Payment(0, "0", UserType.BASIC.toString(), LocalDate.now(), LocalDate.now());
					User user1 = new User(user.getId(), user.getName(), user.getEmail(), user.getExpdate(),
							user.getPassword(), user.getPhone(), user.getProfileUri(), user.getCreatedAt(),
							user.getUpdatedAt(), user.getSuscriptionDate(), pm, UserType.BASIC);

					updateUser(user1);
				}
			} else {
				System.out.println("ok no payment till yet");
			}

		}

	}

	@Override
	public boolean getPhoneExist(String number) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("phone").is(number));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("userTest2 - " + user1);
		if (user1 != null) {
			return true;
		} else {
			return false;
		}

	}
//
//	@Override
//	public String remindMeApi(String vdoId, String userId) {
//		// TODO Auto-generated method stub
//		
//		Query query = new Query();
//        query.addCriteria(Criteria.where("phone").is(number));
//        User user1 = mongoTemplate.findOne(query, User.class);
//        System.out.println("userTest2 - " + user1);
//        if(user1!=null)
//        {
//            return true;
//        }else
//        {
//            return false;
//        }
//		return null;
//	}
//	

	public List<User> getAllUserbypaymentsRep() {
		List<User> list = userRepo.findAll();
		List<User> result = list.stream() // convert list to stream
				.filter(line -> !(line.getPayments() != null)) // we dont like mkyong
				.collect(Collectors.toList());

		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("userTest2 - " + user1);

		return user1;
	}

	public User getUserByPhone(String phone) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("phone").is(phone));
		User user1 = mongoTemplate.findOne(query, User.class);
		System.out.println("userTest2 - " + user1);

		return user1;
	}

}
