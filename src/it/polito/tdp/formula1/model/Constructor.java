package it.polito.tdp.formula1.model;

public class Constructor {

	private int constructorId;
	private String constructorRef;
	private String name;
	private String nationality;
	private String url;
	public Constructor(int constructorId, String constructorRef, String name, String nationality, String url) {
		super();
		this.constructorId = constructorId;
		this.constructorRef = constructorRef;
		this.name = name;
		this.nationality = nationality;
		this.url = url;
	}
	public int getConstructorId() {
		return constructorId;
	}
	public String getConstructorRef() {
		return constructorRef;
	}
	public String getName() {
		return name;
	}
	public String getNationality() {
		return nationality;
	}
	public String getUrl() {
		return url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + constructorId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constructor other = (Constructor) obj;
		if (constructorId != other.constructorId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + constructorId + " " + constructorRef;
	}
	
}
