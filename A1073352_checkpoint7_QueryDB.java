import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class A1073352_checkpoint7_QueryDB {
	// Description : the driver description of mysql
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	// Description : the protocol description of mysql
	private static final String PROTOCOL = "jdbc:mysql:";
	// Description : the obstacle set queried from database.
	private static ArrayList<Integer[]> data;
	// Description : the filename of obstacle image queried from database.
	private static HashMap<Integer, String> typeChar;
	// Description : the ID of the map in database;
	private static String mapID;
	// Description : the sand set queried from database.
	private static ArrayList<Integer[]> sands;

	private static int WIDTH = 0;
	// Description : the past map size
	private static int HEIGHT = 0;

	// Description : the obstacle set queried from database.
	public A1073352_checkpoint7_QueryDB(String mapID) {
		this.data = new ArrayList<Integer[]>();
		this.sands = new ArrayList<Integer[]>();
		this.typeChar = new HashMap<Integer, String>();
		this.mapID = mapID;
		queryData(this.data, this.typeChar);
		querySand(this.sands);
	}

	private static void queryData(ArrayList<Integer[]> data, HashMap<Integer, String> typeChar) {
		/*********************************
		 * The Past TODO (Checkpoint2)********************************
		 * 
		 * TODO(Past) Querying the barrier location from the server, and set it into
		 * Arraylist.
		 * 
		 * TODO(Past) Querying the bar_type and the corresponding file_name from the
		 * server, and set it into HashMap.
		 * 
		 * Hint: for ckp2 to after, you need replace querying column "file_name" with
		 * querying column "display".
		 * 
		 ********************************** The End of the TODO
		 ***************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		String url = "jdbc:mysql://140.127.220.220:3306/CHECKPOINT";
		String user = "checkpoint";
		String password = "ckppwd";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			if (conn != null && !conn.isClosed()) {//
				System.out.println("success!");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("nono");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("nono");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement s = conn.createStatement();
			ResultSet rs = null;// let query save to resultset then take it out.
			// System.out.println("HEIGHT WIDTH");
			rs = s.executeQuery("SELECT * FROM map where map_id=" + mapID);
			while (rs.next()) {
				// System.out.println(rs.getInt("height")+" "+rs.getInt("width"));
				HEIGHT = rs.getInt("height");
				WIDTH = rs.getInt("width");
			}
			rs.close();
//--------------------------------------------------------------------------------------------------------------
			rs = s.executeQuery("SELECT * FROM barrier where map_id=" + mapID);//
			while (rs.next()) {
				data.add(new Integer[] { rs.getInt("row_idx"), rs.getInt("column_idx"), rs.getInt("bar_type") });
			}
			// for(int i=0;i<LocationList.size();i++){
			// for(int j=0;j<LocationList.get(i).length;j++){
			// System.out.print("LocationList:"+LocationList.get(i)[j]+" ");
			// }
			// System.out.println();
			// }
			rs.close();
//---------------------------------------------------------------------------------------------------------------
			rs = s.executeQuery("SELECT * FROM barrier_type");
			while (rs.next()) {
				typeChar.put(rs.getInt("bar_type"), rs.getString("file_name"));
			}
			// for (Object key : typeChar.keySet()) {
			// System.out.println(key + " : " + typeChar.get(key));
			// }
			rs.close();
//--------------------------------------------------------------------------------------------------------------------
			conn.close();
		} catch (SQLException e) {
			System.out.println("nono");
			e.printStackTrace();
		}
	}

	/********************************************************************************************
	 * END OF YOUR CODE
	 ********************************************************************************************/

	private static void querySand(ArrayList<Integer[]> sands) {
		/*********************************
		 * The TODO This Time (Checkpoint7)***************************
		 * 
		 * TODO(1) Querying the map size and the sand location from the server, and set
		 * it into Arraylist.
		 * 
		 ********************************** The End of the TODO
		 ***************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		String url = "jdbc:mysql://140.127.220.220:3306/CHECKPOINT";
		String user = "checkpoint";
		String password = "ckppwd";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			if (conn != null && !conn.isClosed()) {//
				System.out.println("success!");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("nono");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("nono");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement s = conn.createStatement();
			ResultSet rs = null;// let query save to resultset then take it out.
			// System.out.println("HEIGHT WIDTH");
		
			rs = s.executeQuery("SELECT sand.row_idx,sand.column_idx FROM sand where map_id=" + mapID);
			while (rs.next()) {
				// System.out.println(rs.getInt("height")+" "+rs.getInt("width"));
				sands.add(new Integer[] { rs.getInt("sand.row_idx"), rs.getInt("sand.column_idx") });
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("nono");
			e.printStackTrace();
		}
		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	public ArrayList getObstacle() {
		return this.data;
	}

	public void setObstacle(ArrayList<Integer[]> data) {
		this.data = data;
	}

	public String getMapID() {
		return this.mapID;
	}

	public void setMapID(String mapID) {
		this.mapID = mapID;
	}

	public HashMap getObstacleImg() {
		return this.typeChar;
	}

	public void setObstacleImg(HashMap<Integer, String> typeChar) {
		this.typeChar = typeChar;
	}

	public ArrayList getSands() {
		return this.sands;
	}

	public void setSands(ArrayList<Integer[]> sands) {
		this.sands = sands;
	}
}
