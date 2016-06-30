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
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Season;


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
	
	public static void main(String[] args) {
		F1DAO dao = new F1DAO() ;
		
		List<Season> seasons = dao.getAllSeasons() ;
		System.out.println(seasons);
	}
	
}
