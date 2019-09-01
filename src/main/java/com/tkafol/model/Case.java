package com.tkafol.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the case database table.
 * 
 */
@Entity
@Table(name = "cases")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Case implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address;

	@Column(name = "CASE_NAME")
	private String caseName;

	@Column(name = "FILE_NUMBER")
	private int fileNumber;

	@Column(name = "ID_ISSUED_DATE")
	private Timestamp idIssuedDate;

	@Column(name = "ID_NUMBER")
	private String idNumber;

	@Column(name = "RESEARCH_DATE")
	private Timestamp researchDate;

	@Column(name = "STORE_DATE", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date storeDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModifiedDate;

	private String telephone;

	// bi-directional many-to-one association to Area
	@ManyToOne
	private Area area;

	// bi-directional many-to-one association to Government
	@ManyToOne
	@JoinColumn(name = "ID_ISSUED_PLACE_ID")
	private Government government;

	// bi-directional many-to-one association to MatrialState
	@ManyToOne
	@JoinColumn(name = "MARITAL_STATUS_ID")
	private MaterialState matrialState;

	// bi-directional many-to-one association to ResearchType
	@ManyToOne
	@JoinColumn(name = "RESEARCH_TYPE")
	private ResearchType researchTypeBean;

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Case() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public int getFileNumber() {
		return this.fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public Timestamp getIdIssuedDate() {
		return this.idIssuedDate;
	}

	public void setIdIssuedDate(Timestamp idIssuedDate) {
		this.idIssuedDate = idIssuedDate;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Timestamp getResearchDate() {
		return this.researchDate;
	}

	public void setResearchDate(Timestamp researchDate) {
		this.researchDate = researchDate;
	}

	public Date getStoreDate() {
		return this.storeDate;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Government getGovernment() {
		return this.government;
	}

	public void setGovernment(Government government) {
		this.government = government;
	}

	public MaterialState getMatrialState() {
		return this.matrialState;
	}

	public void setMatrialState(MaterialState matrialState) {
		this.matrialState = matrialState;
	}

	public ResearchType getResearchTypeBean() {
		return this.researchTypeBean;
	}

	public void setResearchTypeBean(ResearchType researchTypeBean) {
		this.researchTypeBean = researchTypeBean;
	}

}