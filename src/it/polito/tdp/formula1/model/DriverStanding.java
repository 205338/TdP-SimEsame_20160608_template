package it.polito.tdp.formula1.model;

public class DriverStanding {

	private int driverStandingId;
	private int raceId;
	private int driverId;
	private float points;
	private int position;
	private String positionText;
	private int wins;

	public DriverStanding(int driverStandingId, int raceId, int driverId, float points, int position,
			String positionText, int wins) {
		super();
		this.driverStandingId = driverStandingId;
		this.raceId = raceId;
		this.driverId = driverId;
		this.points = points;
		this.position = position;
		this.positionText = positionText;
		this.wins = wins;
	}

	public int getDriverStandingId() {
		return driverStandingId;
	}

	public void setDriverStandingId(int driverStandingId) {
		this.driverStandingId = driverStandingId;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getPositionText() {
		return positionText;
	}

	public void setPositionText(String positionText) {
		this.positionText = positionText;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
}
