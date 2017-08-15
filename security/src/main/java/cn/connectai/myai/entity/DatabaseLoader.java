/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.connectai.myai.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ManagerRepository managers;

	@Autowired
	public DatabaseLoader(UserRepository userRepository,
						  ManagerRepository managerRepository) {

		this.userRepository = userRepository;
		this.managers = managerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

//		initDb();

	}

	private void initDb()
	{
		Manager greg = this.managers.save(new Manager("greg", "turnquist",
					"ROLE_MANAGER"));
		Manager oliver = this.managers.save(new Manager("oliver", "gierke",
							"ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("greg", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));


		this.userRepository.save(new User("Frodo", "Baggins", "ring bearer " , "pass", greg));
		this.userRepository.save(new User("Bilbo", "Baggins", "burglar", "pass", greg));
		this.userRepository.save(new User("Gandalf", "the Grey", "wizard", "pass", greg));

//		SecurityContextHolder.getContext().setAuthentication(
//				new UsernamePasswordAuthenticationToken("Frodo", "doesn't matter",
//						AuthorityUtils.createAuthorityList("ROLE_USER")));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("oliver", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.userRepository.save(new User("Samwise", "Gamgee", "gardener", "pass", oliver));
		this.userRepository.save(new User("Merry", "Brandybuck", "pony rider", "pass", oliver));
		this.userRepository.save(new User("Peregrin", "Took", "pipe smoker", "pass", oliver));

		SecurityContextHolder.clearContext();
	}
}
// end::code[]