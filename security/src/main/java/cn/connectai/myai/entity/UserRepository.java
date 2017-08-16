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

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@RepositoryRestResource
//@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findBySso(@Param("sso") @RequestParam(value="sso") String sso);

	@Override
	@PreAuthorize("#user?.manager == null or #user?.manager?.name == authentication?.name")
	User save(@Param("user") User user);

	@Override
	@PreAuthorize("@userRepository.findOne(#id)?.manager?.name == authentication?.name")
	void delete(@Param("id") Long id);

	@Override
	@PreAuthorize("#user?.manager?.name == authentication?.name")
	void delete(@Param("user") User user);
//
//	User findBySso(String sso);
}
// end::code[]
