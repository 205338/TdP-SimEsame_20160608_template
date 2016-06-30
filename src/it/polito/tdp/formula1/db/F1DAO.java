package it.polito.tdp.formula1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Constructor;
import it.polito.tdp.formula1.model.ConstructorResult;
import it.polito.tdp.formula1.model.ConstructorStanding;
import it.polito.tdp.formula1.model.Driver;
import it.polito.tdp.formula1.model.DriverStanding;
import it.polito.tdp.formula1.model.LapTime;
import it.polito.tdp.formula1.model.Qualifying;
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Result;
import it.polito.tdp.formula1.model.Season;
import it.polito.tdp.formula1.model.Status;


public class F1DAO {

	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Season> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	/**
	 * 
	 * Ritorna i circuti usati nel campionato disputato nell'anno indicato.
	 * 
	 * @param year Anno i cui e' disputato il campionato
	 * @author Alessandro Vitali Salatino 
	 * @return
	 */
	
	public List<Circuit> getCircuitsOfThe(Year year) {
		String sql = "select * from circuits where circuitID in "
				+ "(select circuitID from races where year=?)" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, year.getValue());
			
			ResultSet rs = st.executeQuery() ;
			
			List<Circuit> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Circuit(rs.getInt("circuitId"), rs.getString("circuitRef"), rs.getString("name"), rs.getString("location"), rs.getString("country"), rs.getDouble("lat"), rs.getDouble("lng"), rs.getInt("alt"), rs.getString("url") )) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	/**
	 * 
	 * Ritorna la gara disputata su un circuito in quell'anno.
	 * 
	 * @param year Anno i cui e' disputato il campionato
	 * @param selectedCircuitsOfTheSeason Circuito in cui si e' gareggiato
	 * @return
	 */
	
	public Race getRaceOf(Year year, int selectedCircuitsOfTheSeason) {
		String sql = "select * from races where circuitID=? and year=?";
		
		Race race = null;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, selectedCircuitsOfTheSeason);
			st.setInt(2, year.getValue());

			ResultSet rs = st.executeQuery() ;
			
			if(rs.next()) {
				
				String time = "12:00:00";
				if(rs.getString("time") != null)
					time = rs.getString("time");
				
				race = new Race(rs.getInt("raceId"), Year.of(rs.getInt("year")), rs.getInt("round"), rs.getInt("circuitId"), rs.getString("name"), LocalDate.parse(rs.getString("date")), LocalTime.parse(time), rs.getString("url"));

			}
			
			conn.close();
			return race ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	

	public List<Circuit> getAllCircuits() {
		final String sql = "SELECT * FROM circuits";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Circuit> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Circuit(rs.getInt("circuitId"), rs.getString("circuitRef"), rs.getString("name"),
						rs.getString("location"), rs.getString("country"), rs.getDouble("lat"), rs.getDouble("lng"),
						rs.getInt("alt"), rs.getString("url")));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Constructor> getAllConstructors() {
		final String sql = "SELECT * FROM constructors";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Constructor> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Constructor(rs.getInt("constructorId"), rs.getString("constructorRef"),
						rs.getString("name"), rs.getString("nationality"), rs.getString("url")));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Driver> getAllDrivers() {
		String sql = "SELECT * FROM drivers LIMIT 0, 415";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Driver> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Driver(rs.getInt("driverId"), rs.getString("driverRef"), rs.getInt("number"),
						rs.getString("code"), rs.getString("forename"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate(), rs.getString("nationality"), rs.getString("url")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Driver> getDriversForRace() {
		String sql = "SELECT drivers.* FROM drivers, driverstandings, races "
				+ "WHERE drivers.driverId=driverstandings.driverId "
				+ "AND races.raceId=driverstandings.raceId AND races.raceId=1";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Driver> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Driver(rs.getInt("driverId"), rs.getString("driverRef"), rs.getInt("number"),
						rs.getString("code"), rs.getString("forename"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate(), rs.getString("nationality"), rs.getString("url")));
			}

			st.close();
			rs.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Result> getAllResults() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Result> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new Result(
								rs.getInt("resultId"), 
								rs.getInt("raceId"), 
								rs.getInt("driverId"), 
								rs.getInt("constructorId"), 
								rs.getInt("number"), 
								rs.getInt("grid"), 
								rs.getInt("position"), 
								rs.getString("positionText"), 
								rs.getInt("positionOrder"), 
								rs.getFloat("points"), 
								rs.getInt("laps"), 
								rs.getString("time"), 
								rs.getInt("milliseconds"), 
								rs.getInt("fastestLap"), 
								rs.getInt("rank"), 
								rs.getString("fastestLapTime"), 
								rs.getString("fastestLapSpeed"), 
								rs.getInt("statusId")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<LapTime> getDriversForGara(int driverId, int circuitId) {

		String sql = "select * from laptimes  where driverId=? and raceId in (select raceId from races where circuitId=?) order by lap" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, driverId);
			st.setInt(2, circuitId);
			ResultSet rs = st.executeQuery() ;
			
			List<LapTime> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new LapTime(rs.getInt("raceId"), rs.getInt("driverId"), rs.getInt("lap"), rs.getInt("position"), rs.getString("time"), rs.getInt("milliseconds"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
		
	public List<ConstructorResult> getAllConstructorResult() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<ConstructorResult> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new ConstructorResult(
								rs.getInt("constructorResultId"), 
								rs.getInt("raceId"), 
								rs.getInt("constructorId"), 
								rs.getFloat("points"), 
								rs.getString("status")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ConstructorStanding> getAllConstructorStanding() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<ConstructorStanding> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new ConstructorStanding(
								rs.getInt("constructorStandingId"), 
								rs.getInt("raceId"), 
								rs.getInt("constructorId"), 
								rs.getFloat("points"), 
								rs.getInt("position"), 
								rs.getString("positionText"), 
								rs.getInt("wins")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<DriverStanding> getAllDriverStanding() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<DriverStanding> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new DriverStanding(
								rs.getInt("driverStandingId"), 
								rs.getInt("raceId"), 
								rs.getInt("driverId"), 
								rs.getFloat("points"), 
								rs.getInt("position"), 
								rs.getString("positionText"), 
								rs.getInt("wins")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Qualifying> getAllQualifying() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Qualifying> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new Qualifying(
								rs.getInt("qualifyId"), 
								rs.getInt("raceId"), 
								rs.getInt("driverId"), 
								rs.getInt("constructorId"), 
								rs.getInt("number"), 
								rs.getInt("position"), 
								rs.getString("q1"), 
								rs.getString("q2"), 
								rs.getString("q3")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Status> getAllStatus() {
		final String sql = "SELECT * FROM results";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Status> list = new ArrayList<>();
			
			while (rs.next()) {
				
				list.add(
						
						new Status(
								rs.getInt("statusId"), 
								rs.getString("status")
								)
						);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static void main(String[] args) {
		F1DAO dao = new F1DAO() ;
		
		List<Season> seasons = dao.getAllSeasons() ;
		System.out.println(seasons);
		
		List<Result> results = dao.getAllResults();
		System.out.println(results);
	}
	
}
