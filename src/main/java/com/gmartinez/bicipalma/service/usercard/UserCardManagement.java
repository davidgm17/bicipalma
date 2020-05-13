package com.gmartinez.bicipalma.service.usercard;

import com.gmartinez.bicipalma.domain.usercard.UserCard;
import org.springframework.stereotype.Service;

@Service
public class UserCardManagement {

	public UserCard cancelSubscription(UserCard userCard){

		return userCard.toBuilder()
			.actived(false)
			.build();
	}
}
