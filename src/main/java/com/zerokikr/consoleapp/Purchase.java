package com.zerokikr.consoleapp;


import javax.persistence.*;

@Entity
@Table (name = "purchases")
public class Purchase {
	
	
	@EmbeddedId
	private PurchaseId purchaseId;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id", insertable = false, updatable = false)
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private Item item;
	
	@Column(name = "purchase_price")
	private double purchasePrice;

	public Purchase() {
		
	}

	public Purchase(Buyer buyer, Item item, double purchasePrice) {
		this.buyer = buyer;
		this.item = item;
		this.purchasePrice = purchasePrice;
	}

	public PurchaseId getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Override
	public String toString() {
		return "Purchase [buyer=" + buyer + ", item=" + item + ", purchasePrice=" + purchasePrice + "]";
	}
	
	
	
	
	
	
}
