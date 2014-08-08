package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * The database class.  Deals with connecting to the database.
 * 
 * 
 * @author Brandon M Martin
 *
 */
public class Database {
    
    /*
     * Instance of database
     */
    private static Database me;
    
    /**
     * Username for database
     */
    private static final String USERNAME = "root";
    
    /**
     * Password for database
     */
    private static final String PW = "root";
    
    /**
     * Name of dbms
     */
    private static final String DBMS = "mysql";
    
    /**
     * Server name
     */
    private static final String SERVER_NAME = "localhost";
    
    /**
     * Port your sql is open to
     */
    private static final String PORT = "3306";
    
    /**
     * Name of DB
     */
    private static final String DB_NAME = "recorddb";
    
    
    private Connection my_conn = null;
    
    
    /**
     * Constructor
     */
    public Database() {
        my_conn = getConnection();
    }
    
    /**
     * Gets an instance of the database
     * @return me the instance
     */
    public static Database getInstance() {
        if (me == null){
            me = new Database();
        }
        return me;
    }
    
    /**
     * Inserts an artist in the database
     * 
     * @param artistid id for artist
     * @param artistname name for artist
     */
    public void insertArtist(int artistid, String artistname) {
        try {
            Statement stmt = my_conn.createStatement();
            String insert = "INSERT INTO ARTIST VALUES(" + artistid + ", '" + artistname + "');";
            stmt.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes one artist from the database
     * 
     * @param artistid id for artist
     */
    public void deleteArtist(int artistid) {
        try {
            Statement stmt = my_conn.createStatement();
            String delete = "DELETE FROM ARTIST WHERE ARTISTID = " + artistid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Searches for the specified artist.  Prints to console,
     * would liked to have put into a panel but couldnt figure it out.
     * 
     * @param artistname name of artist
     */
    public void searchArtist(String artistname) {
        try {
            ResultSet rs = null;
            Statement stmt = my_conn.createStatement();
            String select = "Select * FROM ARTIST WHERE ARTISTNAME = '" + artistname + "';";
            stmt.execute(select);          
            rs = stmt.getResultSet();
            System.out.println("Searching Artists for '" + artistname + "': ");
            while(rs.next()) {
                System.out.println("ArtistName: " + rs.getString("ArtistName"));
                System.out.println("ArtistID: " + rs.getString("ArtistID"));
                System.out.println("--------------------------------");
            }
            System.out.println("++++++++++++++END OF SEARCH+++++++++++++++++");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Inserts an album into the database
     * 
     * @param albumid id for the album
     * @param artistid id for the artist
     * @param albumname name for the album
     * @param genreid id for the genre
     * @param rating rating of album 0-5
     */
    public void insertAlbum(int albumid, int artistid, String albumname, int genreid, int rating) {
        try {
            Statement stmt = my_conn.createStatement();
            String insert = "INSERT INTO ALBUM VALUES(" + albumid + ", " + artistid + ", '" + albumname + "', " + genreid + ", " + rating + ");";
            stmt.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    /**
     * Deletes a single album from database
     * 
     * @param albumid id of album
     * @param artistid id of artist
     */
    public void deleteAlbum(int albumid, int artistid) {
        try {
            Statement stmt = my_conn.createStatement();
            String delete = "DELETE FROM ALBUM WHERE ARTISTID = " + artistid + " AND ALBUMID = " + albumid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * Deletes all tracks and albums tied to artist
     * 
     * @param artistid id of artist
     */
    public void deleteAllArtistAlbum(int artistid) {
        try {
            Statement stmt = my_conn.createStatement();
            
            String delete = "DELETE FROM TRACKS WHERE ALBUMID = (SELECT ALBUMID FROM ALBUM WHERE ARTISTID = '" + artistid + "');";
            stmt.execute(delete);
            stmt = my_conn.createStatement();
            delete = "DELETE FROM ALBUM WHERE ARTISTID = " + artistid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Searches database for an album
     * 
     * @param albumname name of album
     */
    public void searchAlbum(String albumname) {
        try {
            ResultSet rs = null;
            Statement stmt = my_conn.createStatement();
            String select = "SELECT * FROM ALBUM WHERE ALBUMNAME = '" + albumname + "';";
            stmt.execute(select);
            rs = stmt.getResultSet();
            System.out.println("Searching Albums for '" + albumname +"': ");
            while(rs.next()) {
                System.out.println("AlbumName: " + rs.getString("AlbumName"));
                System.out.println("ArtistID: " + rs.getString("ArtistID"));
                System.out.println("AlbumID: " + rs.getString("AlbumID"));
                System.out.println("GenreID: " + rs.getString("GenreID"));
                System.out.println("Rating: " + rs.getString("Rating"));
                System.out.println("--------------------------------");
            }
            System.out.println("++++++++++++++END OF SEARCH+++++++++++++++++");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserts a track into the database
     * 
     * @param trackid id of track
     * @param albumid id of album
     * @param tracknum track number
     * @param title title of track
     * @param length length of track in min:sec form
     */
    public void insertTracks(int trackid, int albumid, int tracknum, String title, String length ) {
        try {
            Statement stmt = my_conn.createStatement();
            String insert = "INSERT INTO TRACKS VALUES(" + trackid + ", " + albumid + ", " + tracknum + ", '" + title + "', '" + length + "');";
            stmt.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a track from database 
     * 
     * @param trackid id of track 
     * @param albumid id of album
     */
    public void deleteTracks(int trackid, int albumid) {
        try {
            Statement stmt = my_conn.createStatement();
            String delete = "DELETE FROM TRACKS WHERE TRACKID = " + trackid + " AND ALBUMID = " + albumid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes all the track on that album
     * 
     * @param albumid id of album
     */
    public void deleteAllAlbumTracks(int albumid) {
        try {
            Statement stmt = my_conn.createStatement();
            String delete = "DELETE FROM TRACKS WHERE ALBUMID = " + albumid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserts a genre into database
     * 
     * @param genreid id of the genre
     * @param genrename name of the genre
     */
    public void insertGenre(int genreid, String genrename) {
        try {
            Statement stmt = my_conn.createStatement();
            String insert = "INSERT INTO GENRE VALUES(" + genreid + ", '" + genrename + "');";
            stmt.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Updates the genre
     * 
     * @param newgenreid  id of the new genre
     * @param newgenrename name of the new genre
     * @param oldgenreid id of the old genre
     */
    public void updateGenre(int newgenreid, String newgenrename, int oldgenreid) {
        try {
            Statement stmt = my_conn.createStatement();
            String insert = "UPDATE GENRE SET GENREID = " + newgenreid + ", GENRENAME = '" + newgenrename + "' WHERE GENREID = " + oldgenreid + ";";
            stmt.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete genres from database
     * 
     * @param genreid id of the genre
     */
    public void deleteGenre(int genreid) {
        try {
            Statement stmt = my_conn.createStatement();
            String delete = "DELETE FROM GENRE WHERE GENREID = " + genreid + ";";
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
 
    /**
     * Gets the connection to the database 
     * @return the connection
     */
    private static Connection getConnection() {
        Connection conn = null;
        Properties connectionprops = new Properties();
        connectionprops.put(PW, USERNAME);
        if(DBMS.equals("mysql")) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + SERVER_NAME + ":" + PORT + "/" + DB_NAME, USERNAME, PW);
            } catch (SQLException e) {
                e.printStackTrace();
            }
         }        
        return conn;
        
    }

}
