package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;

/**
 * The panel class.  Also deals with the actionlisteners of the buttons
 * 
 * 
 * @author Brandon M Martin
 *
 */
@SuppressWarnings("serial")
public class RecordPanel extends JPanel implements ActionListener{
    
    /**
     * Creating the Database object
     */
    Database db = null;
    
    /**
     * Constructor
     */
    public RecordPanel() {
        super();
        start();

    }
    
    /**
     * Puts all the buttons and ActionListeners on the panel.  Would like to split this up.
     */
    private void start() {

        
        //creates two labels
        JLabel label = new JLabel("Welcome to RecordShop!");
        JLabel label2 = new JLabel("You're logged in as: root");
        
        //creates all the buttons
        JButton add_artist = new JButton("Add Artist");
        JButton remove_artist = new JButton("Remove Artist");
        JButton add_album = new JButton("Add Album");
        JButton remove_album = new JButton("Remove Album");
        JButton add_tracks = new JButton("Add Tracks");
        JButton remove_tracks = new JButton("Remove Tracks");
        JButton add_genre = new JButton("Add Genre");
        JButton remove_genre = new JButton("Remove Genre");
        JButton update_genre = new JButton("Update Genre");
        JButton search_artist = new JButton("Search For Artist");
        JButton search_album = new JButton("Search For Album");
        
        //puts the buttons in the pannel 
        this.add(label);
        this.add(label2);
        this.add(add_artist);
        this.add(remove_artist);
        this.add(add_album);
        this.add(remove_album);
        this.add(add_tracks);
        this.add(remove_tracks);
        this.add(add_genre);
        this.add(remove_genre);
        this.add(update_genre);
        this.add(search_artist);
        this.add(search_album);

        //all of the actionlisteners
        add_artist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();

                    try {
                        int artistid = Integer.parseInt(JOptionPane.showInputDialog("Enter ArtistID"));
                    
                        String artistname = JOptionPane.showInputDialog("Enter ArtistName");
                        db.insertArtist(artistid, artistname);
                    
                    } catch(NumberFormatException s) {
                        JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                    }
           }
        });
        
        remove_artist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                int artistid = Integer.parseInt(JOptionPane.showInputDialog("Enter ArtistID"));
                db.deleteAllArtistAlbum(artistid);
                db.deleteArtist(artistid);
            }
        });
        
        add_album.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                
                try{
                    int albumid = Integer.parseInt(JOptionPane.showInputDialog("Enter AlbumID"));
                    int artistid = Integer.parseInt(JOptionPane.showInputDialog("Enter ArtistID"));
                    String albumname = JOptionPane.showInputDialog("Enter AlbumName");
                    int genreid = Integer.parseInt(JOptionPane.showInputDialog("Enter GenreID"));
                    int rating = Integer.parseInt(JOptionPane.showInputDialog("Enter Rating"));
                    
                    db.insertAlbum(albumid, artistid, albumname, genreid, rating);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        remove_album.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try{
                    db = new Database();
                    int albumid = Integer.parseInt(JOptionPane.showInputDialog("Enter AlbumID"));
                    int artistid = Integer.parseInt(JOptionPane.showInputDialog("Enter ArtistID"));
                    db.deleteAllAlbumTracks(artistid);
                    db.deleteAlbum(albumid, artistid);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        add_tracks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try {
                    int trackid = Integer.parseInt(JOptionPane.showInputDialog("Enter TrackID"));
                    int albumid = Integer.parseInt(JOptionPane.showInputDialog("Enter AlbumID"));
                    int tracknum = Integer.parseInt(JOptionPane.showInputDialog("Enter TrackNum"));
                    String title = JOptionPane.showInputDialog("Enter Title");
                    String length = JOptionPane.showInputDialog("Enter Length (min:sec)");
                    db.insertTracks(trackid, albumid, tracknum, title, length);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
                
            }
        });
         
        remove_tracks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try {
                    int trackid = Integer.parseInt(JOptionPane.showInputDialog("Enter TrackID"));
                    int albumid = Integer.parseInt(JOptionPane.showInputDialog("Enter AlbumID"));
                    db.deleteTracks(trackid, albumid);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        add_genre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                
                try{
                    int genreid = Integer.parseInt(JOptionPane.showInputDialog("Enter GenreID"));
                    String genrename = JOptionPane.showInputDialog("Enter GenreName");
                    db.insertGenre(genreid, genrename);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        remove_genre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                
                try {
                    int genreid = Integer.parseInt(JOptionPane.showInputDialog("Enter GenreID"));
                    db.deleteGenre(genreid);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        update_genre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try{
                    int newgenreid = Integer.parseInt(JOptionPane.showInputDialog("Enter NewGenreID"));
                    String newgenrename = JOptionPane.showInputDialog("Enter GenreName");
                    int oldgenreid = Integer.parseInt(JOptionPane.showInputDialog("Enter OldGenreID"));
                    db.updateGenre(newgenreid, newgenrename, oldgenreid);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        search_artist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try{
                    String artistname = JOptionPane.showInputDialog("Enter ArtistName");
                    db.searchArtist(artistname);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
                
                
            }
        });
        
        search_album.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new Database();
                try{
                    String albumname = JOptionPane.showInputDialog("Enter AlbumName");
                    db.searchAlbum(albumname);
                } catch(NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Not Valid Input, try again");
                }
            }
        });
        
        //sets it visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
    }
    

}
