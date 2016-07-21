///////////////////////////////////////////////////////////////////////////////
// Title:            Midterm Project
// Files:            Album.java, ArtistComparator.java, Catalog.java,
//                   CatalogHelper.java, Track.java
// Author:           Sabina
// Description of Program’s Functionality:
// A program to read a text file and process its content into album objects
// sort them by album name or artist name upon users request, also allowing
// users to search for an album by album name or all albums by a certain
// artist. This program also allows the user to add an album to the catalog
// by entering the appropriate information.
//
//////////////////////////// 80 columns wide/////////////////////////////////

import java.util.ArrayList;

public class Album implements Comparable<Album>
{
    //Instance Variables
    String artist;                 //The name of the artist
    String album;                  //The title of the album
    ArrayList<Track> tracklist;    //The list of the tracks on the album
    
    /**
     * Constructor to create an Album object.
     * @param artist  the artist of the album
     * @param album   the album title
     * @param tracklist an array
     */
    public Album(String artist, String album, ArrayList<Track> tracklist)
    {
        this.artist = artist;
        this.album = album;
        this.tracklist = tracklist;
        
    }

    /**
     * Accesses the arraylist of track objects.
     * @return tracklist
     */
    public ArrayList<Track> getTracklist()
    {
        return tracklist;
    }
    /**
     * Accesses the artist name.
     * @return artist
     */
    public String getArtist() 
    {
        return artist;
    }
    /**
     * Accesses the album name.
     * @return album
     */
    public String getAlbum() 
    {
        return album;
    }
    
    /**
     * Method to return a string version of an album object.
     * @return string version of an album object
     */
    @Override
    public String toString()
    {
        album = album.replaceAll("_", " ");
        artist = artist.replaceAll("_", " ");
        
        String result = "\n Album: " + album
                + "\n Artist: " + artist
                + "\n Tracklist: \n";
        
      //Prints each element of the tracklist arraylist
      for(int i = 0; i < tracklist.size(); i++)
      {
          String track = tracklist.get(i).toString().replaceAll("_"," ");
           result += "\t‣ " + track + "\n"; 
      }
      
    //Astrick divider between each album 
    return result + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * "
            + "* * * * * * * * * * * * * * * * * * * * * * * * * * * \n";
    
    }

    /**
     * Method to return a string version of an album object when writing to a file.
     * @return a string version of an album object.
     */
    public String otherToString()
    {
        String track = "";
        for(int i = 0; i < tracklist.size(); i++)
        {
            track += tracklist.get(i).getSong() + " ";
        }
        return  artist + " " + album + " " + track;
    }
    
    /**
     * Method to compare two album objects by album name.
     * @param albumName
     * @return the comparison of the two objects
     */
    @Override
    public int compareTo(Album albumName) 
    {
        return this.getAlbum().compareToIgnoreCase(albumName.getAlbum());
    
    }
    
}
