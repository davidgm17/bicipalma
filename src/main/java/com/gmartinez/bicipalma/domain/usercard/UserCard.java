package com.gmartinez.bicipalma.domain.usercard;

import com.gmartinez.bicipalma.domain.common.UniqueModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCard implements UniqueModel {

	private final long id;
	private boolean actived = false;

	private UserCard(long id) {
		this.id = id;
	}

	public static UserCardBuilder builder(long id) {
		return new UserCardBuilder().id(id);
	}

	public UserCardBuilder toBuilder() {

		return new UserCardBuilder()
			.id(this.id)
			.actived(this.actived);
	}

	public static class UserCardBuilder {

		private long id;
		private boolean actived;

		UserCardBuilder() {
		}

		public UserCardBuilder id(long id) {
			this.id = id;
			return this;
		}

		public UserCardBuilder actived(boolean actived) {
			this.actived = actived;
			return this;
		}

		public UserCard build() {
			return new UserCard(this.id, this.actived);
		}

	}
}
