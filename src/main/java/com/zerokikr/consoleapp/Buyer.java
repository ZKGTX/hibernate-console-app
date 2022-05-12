package com.zerokikr.consoleapp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import lombok.Data;

@Entity
@Table (name = "buyers") 
public class Buyer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Purchase> purchases;

	public Buyer() {
		
	}

	public Buyer(String name) {
		this.name = name;
		this.purchases = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", name=" + name + ", purchases=" + purchases + "]";
	}
	
	
	
	
	
}
