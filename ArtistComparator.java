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

import java.util.Comparator;

/**
 *
 * @author 5099647
 */
public class ArtistComparator implements Comparator<Album>
{
/**
 * Method to compare two album objects by artist.
 * @param one the first album object
 * @param two the second album object
 * @return the corresponding number of their comparison
 */
    @Override
    public int compare(Album one, Album two) 
    {
        return one.getArtist().compareToIgnoreCase(two.getArtist());
    }
    
    

    
}
