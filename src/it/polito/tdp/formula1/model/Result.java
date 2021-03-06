package it.polito.tdp.formula1.model;

public class Result {
	
	private int resultId;
	private int raceId;
	private int driverId;
	private int constructorId;
	private int number;
	private int grid;
	private int position;
	private String positionText;
	private int positionOrder;
	private float points;
	private int laps;
	private String time;
	private int milliseconds;
	private int fastestLap;
	private int rank;
	private String fastestLapTime;
	private String fastestLapSpeed;
	private int statusId;
	
	public Result(int resultId, int raceId, int driverId, int constructorId, int number, int grid, int position,
			String positionText, int positionOrder, float points, int laps, String time, int milliseconds,
			int fastestLap, int rank, String fastestLapTime, String fastestLapSpeed, int statusId) {
		super();
		this.resultId = resultId;
		this.raceId = raceId;
		this.driverId = driverId;
		this.constructorId = constructorId;
		this.number = number;
		this.grid = grid;
		this.position = position;
		this.positionText = positionText;
		this.positionOrder = positionOrder;
		this.points = points;
		this.laps = laps;
		this.time = time;
		this.milliseconds = milliseconds;
		this.fastestLap = fastestLap;
		this.rank = rank;
		this.fastestLapTime = fastestLapTime;
		this.fastestLapSpeed = fastestLapSpeed;
		this.statusId = statusId;
	}
	
	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
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

	public int getConstructorId() {
		return constructorId;
	}

	public void setConstructorId(int constructorId) {
		this.constructorId = constructorId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getGrid() {
		return grid;
	}

	public void setGrid(int grid) {
		this.grid = grid;
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

	public int getPositionOrder() {
		return positionOrder;
	}

	public void setPositionOrder(int positionOrder) {
		this.positionOrder = positionOrder;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getFastestLap() {
		return fastestLap;
	}

	public void setFastestLap(int fastestLap) {
		this.fastestLap = fastestLap;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getFastestLapTime() {
		return fastestLapTime;
	}

	public void setFastestLapTime(String fastestLapTime) {
		this.fastestLapTime = fastestLapTime;
	}

	public String getFastestLapSpeed() {
		return fastestLapSpeed;
	}

	public void setFastestLapSpeed(String fastestLapSpeed) {
		this.fastestLapSpeed = fastestLapSpeed;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resultId;
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
		Result other = (Result) obj;
		if (resultId != other.resultId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", raceId=" + raceId + ", driverId=" + driverId + ", constructorId="
				+ constructorId + "]\n";
	}

}
