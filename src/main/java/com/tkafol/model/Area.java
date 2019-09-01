package com.tkafol.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the area database table.
 * 
 */

@Entity
@Table(name = "area")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "STORE_DATE", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date storeDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModifiedDate;

	// bi-directional many-to-one association to Branch
	@OneToMany(mappedBy = "area")
	private List<Branch> branches;

	// bi-directional many-to-one association to User
	@OneToMany(mappedBy = "area")
	private List<User> users;

	// bi-directional many-to-one association to Case
	@OneToMany(mappedBy = "area")
	private List<Case> cases;

	public Area() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStoreDate() {
		return this.storeDate;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public List<Branch> getBranches() {
		return this.branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch addBranch(Branch branch) {
		getBranches().add(branch);
		branch.setArea(this);

		return branch;
	}

	public Branch removeBranch(Branch branch) {
		getBranches().remove(branch);
		branch.setArea(null);

		return branch;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setArea(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setArea(null);

		return user;
	}

	public List<Case> getCases() {
		return this.cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public Case addCas(Case cas) {
		getCases().add(cas);
		cas.setArea(this);

		return cas;
	}

	public Case removeCas(Case cas) {
		getCases().remove(cas);
		cas.setArea(null);

		return cas;
	}

}