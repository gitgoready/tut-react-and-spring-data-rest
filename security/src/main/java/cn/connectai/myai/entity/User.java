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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Data
@Entity
@ToString(exclude = "password")
public class User {
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String description;
	private String phoneNum;
	private String email;
	private String weiboId;
	private String qqId;
	private String wxOpenId;
	private String wxUnionId;
	private String name;

	private String wxAccessTocken;

	private  @JsonIgnore String password;
	private Date lastPasswordResetDate;
	private String[] roles;

	private @Version @JsonIgnore Long version;
	private @ManyToOne Manager manager;
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}


	private User() {this.roles = new String[]{"ROLE_USER"};}

	public User(String firstName, String lastName, String description, String password, Manager manager) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.manager = manager;
		this.setPassword(password);
		this.name = firstName;
		this.roles = new String[]{"ROLE_USER"};
	}
}
// end::code[]