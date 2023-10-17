package com.chatapp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the role_ms database table.
 * 
 */
@Entity
@Table(name="role_ms")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @Column(name="role_id") private int roleId;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;
	
	@Column(name="role_name")
	@Enumerated(EnumType.STRING)
	//@Column(length = 20)
	private ERole roleName;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	public Role() {
	}

	/*
	 * public int getRoleId() { return this.roleId; }
	 * 
	 * public void setRoleId(int roleId) { this.roleId = roleId; }
	 */

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/*
	 * public String getRoleName() { return this.roleName; }
	 * 
	 * public void setRoleName(String roleName) { this.roleName = roleName; }
	 */

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getRoleName() {
		return roleName;
	}

	public void setRoleName(ERole roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}