///////////////////////////////////////////////////////////////////////////////
// Title:            Midterm Project
// Files:            Album.java, ArtistComparator.java, Catalog.java,
//                   CatalogHelper.java, Track.java
// Semester:         COP3337 Fall 2015
//
// Author:           5099647
// Lecturer's Name:  Professor Charters
//
// Description of Program’s Functionality:
// A program to read a text file and process its content into album objects
// sort them by album name or artist name upon users request, also allowing
// users to search for an album by album name or all albums by a certain
// artist. This program also allows the user to add an album to the catalog
// by entering the appropriate information.
//
//////////////////////////// 80 columns wide/////////////////////////////////


/**
 *
 * @author 5099647
 */
public class Track implements Comparable<Track>
{
    //Instance Variable
    String song;      //The name of a song

    /**
     * Contructs a track object.
     * @param song 
     */
    public Track(String song)
    {
        this.song = song;
    }
    
    /**
     * Acessor for the song
     * @return song
     */
    public String getSong() 
    {
        return song;
    }
    
    @Override
    /**
     * Compare method that from comparable interface to compare songs
     * @param song a track on the album
     * @return the comparison of one song to another
     */
    public int compareTo(Track song) 
    {   
        return this.getSong().compareToIgnoreCase(song.getSong());
    }
    
    /**
     * Return a string version of the track object
     * @return track object as a string
     */
    public String toString()
    {
        return this.song + " ";
    }
}
