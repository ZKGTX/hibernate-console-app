package com.zerokikr.consoleapp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table (name = "items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "price")
	private double price;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Purchase> purchases;


	public Item() {
	
	}


	public Item(String name, double price) {
		this.name = name;
		this.price = price;
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


	public double getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public List<Purchase> getPurchases() {
		return purchases;
	}


	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}


	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", purchases=" + purchases + "]";
	}


	
	
	
	
	
	
}
