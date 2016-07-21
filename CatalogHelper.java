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

import java.io.File;                    import java.io.FileNotFoundException;
import java.io.FileWriter;              import java.io.IOException;
import java.io.PrintWriter;             import java.util.ArrayList;
import java.util.Collections;           import java.util.Scanner;
import javax.swing.ImageIcon;           import javax.swing.JOptionPane;
import javax.swing.JScrollPane;         import javax.swing.JTextArea;


public class CatalogHelper 
{
    
    //Instance Variables
    ArrayList<Album> catalog = new ArrayList<>();   //Array of album objects
    File file = null;                               //a File object
   
    /**
     * Constructor to create a CatalogHelper Object which reads a file.
     * @throws IOException
     */
    public CatalogHelper() throws IOException
    {
        int mode = 1;
    
        while(mode != 0)
        {
         try
        {
            String input = JOptionPane.showInputDialog(null, "Enter the name "
                    + "of the file.\nEnter catalog2.txt for the file input.");
            file = new File(input);
            Scanner scan = new Scanner(file);
            mode = 1;
            
            while(scan.hasNextLine())
            {
                ArrayList<Track> tracklist = new ArrayList<>();
                String temp = scan.nextLine();
                String artist = "";
                String album = "";
                String[] split = temp.split(" ");
                for(int i = 0; i < split.length; i++)
                {
                    if(i == 0)
                    {
                        split[i] = split[i].replace("_", " ");
                        split[i] = split[i].toLowerCase();
                        artist = split[i];
                    }
                    else if(i == 1)
                    {
                        split[i] = split[i].replace("_", " ");
                        split[i] = split[i].toLowerCase();
                        album = split[i];
                    }
                    else
                    {
                        tracklist.add(new Track(split[i]));
                    }  
                }
                catalog.add(new Album(artist, album, tracklist));
                Collections.sort(tracklist);
            }
        
            //Calls the album and artist order
            askUser();

        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "The file entered was not found.");
        }
         catch(IOException e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
    }
    }
    
//************************The Sorting Menu Methods****************************//
    /**
     * Method to create a menu that asks the user how to sort or to continue.
     * @throws IOException
     */
    private void askUser() throws IOException
    {
        int mode = 0;
         do 
         {
            String[] options = {"Sort by Album", "Sort by Artist", 
                "More Options"};

            //Asks the user how they want to sort their courses
            mode = JOptionPane.showOptionDialog(null, "\tSelect your next "
                    + "course of action. Press X to exit.", "User Menu", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, null);

            switch(mode)
            {
                case 0: albumOrder();
                    break;
                case 1: artistOrder();
                    break;
                case 2: menu();
                    break;
                default: System.exit(0);
            }
         }while (mode >= 0 && mode < 2);
    }
    
    /**
     * Method that sorts the catalog by album and displays the catalog.
     */
    private void albumOrder() 
    {
        //Sorts the catalog array by Album Name and displays it
        Collections.sort(catalog);
        print(catalog, "Sorted By Album Name");
    }
    
    /**
     * Method that sorts the catalog by artist and displays the catalog.
     */
    private void artistOrder()
    {
        ArrayList<Album> catalog_2 = new ArrayList<>();
        catalog_2.addAll(catalog);
        
        Collections.sort(catalog_2, new ArtistComparator());
        print(catalog_2, "Sorted By Artist");
    }
    
    /**
     * Method to print a sorted catalog.
     * @param list the current state of the catalog
     * @param sortType the type of sort
     */
    private void print(ArrayList<Album> list, String sortType)
    {
        //Sets up the display window
        final ImageIcon icon = new ImageIcon("C:\\Users\\Carlos\\Documents"
        + "\\NetBeansProjects\\New Folder\\MidtermProject\\icon.png");
        JTextArea textArea = new JTextArea(20, 35);
        textArea.setText(list.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, sortType, 
                JOptionPane.INFORMATION_MESSAGE, icon); 
    }
   
//***************************Search And Add Menu******************************//
    /**
     * Method to create a menu for the user to search and add to the catalog.
     * @throws IOException
     */
    private void menu() throws IOException
    {
        int mode = 0;
         do 
         {
             //A list of button names
             String[] choices = {"Search by Album", "Search by Artist",
                 "Add New Album", "Sort Menu", "Quit"};
             //Prompts the user to choose an option
            String input = (String) JOptionPane.showInputDialog(null, "Choose a file to process.",
                     "Sort Menu.", JOptionPane.QUESTION_MESSAGE, null,
                     choices, choices[1]);
            
            if(input == null)
            {
                System.exit(0);
            }
            switch(input)
            {
                case "Search by Album": mode = 0; searchAlbum();
                    break;
                case "Search by Artist": mode = 1; searchArtist();
                    break;
                case "Add New Album": mode = 2; addAlbum();
                    break;
                case "Sort Menu": mode = 3; askUser();
                    break;
                case "Quit": System.exit(0);
            }
         }while(mode >= 0);
    }
         
    
    /**
     * Method to sort the catalog by album and search for a user specified
     * album.
     */
    private void searchAlbum() throws IOException
    {
        //Prompts the user to choose a file name
        String input = JOptionPane.showInputDialog(null, "Enter the name of "
                + "the album: ");
      
        //If the user chooses to exit or cancel, exit.
        if(input == null)
        {
            System.exit(0);
        }
        else
        {
            input = input.replaceAll("_", " ");
            input = input.toLowerCase();
            Collections.sort(catalog);
            Album key = new Album(null, input, null);
            int index = Collections.binarySearch(catalog, key, null);
        
            //If element not found
            if(index < 0)
            {
                JOptionPane.showMessageDialog(null, "This album was not found." ,
                        "Search by Album", JOptionPane.INFORMATION_MESSAGE);
                menu();
            }
            //Else print appropriate album
            else
            {
                //Sets up the display window 
                final ImageIcon icon = new ImageIcon("C:\\Users\\Carlos\\Documents"
                    + "\\NetBeansProjects\\New Folder\\MidtermProject\\icon.png");
                JTextArea textArea = new JTextArea(20, 35);
                textArea.setText(catalog.get(index).toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                //Prints the catalog with albums and tracks in alphabetic order
                JOptionPane.showMessageDialog(null, scrollPane, "Search by Album", 
                        JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }
    
    /**
     * Method that searches for a user specified artist.
     * @throws IOException
     */
    private void searchArtist() throws IOException
    {
        int end = 0;
        int start = 0;
        int count = 0;
        
        //Prompts the user to choose a file name
        String input = JOptionPane.showInputDialog(null, "Enter the name of "
                + "the artist: ");

        input = input.replace("_", " ");
        input = input.toLowerCase();
        Collections.sort(catalog, new ArtistComparator());
        Album key = new Album(input, null, null);
        int index = Collections.binarySearch(catalog, key, new ArtistComparator());
        
        //If the user chooses to exit or cancel, exit.
        if (input == null) 
        {
            System.exit(0);
        }
        //If the binary finds the first element of the catalog
        else if(index == 0)
        {
            start = index;
            count = index;
            do
            {
                count ++;
            }while(catalog.get(count).getArtist().equalsIgnoreCase(input));
            end = count -1;
            
        }
        //If the binary finds the last element of the catalog
        else if(index == catalog.size() - 1)
        {
            count = index;
            do
            {
                count--;
            }while(catalog.get(count).getArtist().equalsIgnoreCase(input));
            start = count + 1;
        }
        else
        {
            String result = "";
            if(index < 0)
            {
                JOptionPane.showMessageDialog(null, "This artist was not found!", 
                        "Search By Artist", JOptionPane.INFORMATION_MESSAGE);
                menu();
            }
            count = index;
            do
            {
                count --;   
            }while(catalog.get(count).getArtist().equalsIgnoreCase(input));
        
            //Initializes the start index
            start = count + 1;
        
            count = index;
            do
            {
                count ++;
            }while(catalog.get(count).getArtist().equalsIgnoreCase(input) 
                    && count < catalog.size() - 1);
        
        
            if(count == catalog.size() - 1 )
            {
                end = count;
            }
            else
            {
                end = count - 1;
            }
        }
        
        String result = "";
        //Goes through start to end to print all occurances of this artists
        for(int i = start; i < end + 1; i++)
        {
            result +=  catalog.get(i);
        }

        final ImageIcon icon = new ImageIcon("C:\\Users\\Carlos\\Documents"
                + "\\NetBeansProjects\\New Folder\\MidtermProject\\icon.png");

        JTextArea textArea = new JTextArea(20, 35);
        textArea.setText(result);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Prints the catalog with albums and tracks in alphabetic order
        JOptionPane.showMessageDialog(null, scrollPane, "Search by Artist",
                JOptionPane.INFORMATION_MESSAGE, icon);
        
    }
    
  
    /**
     * Method to have the user add a new album to the catalog.
     * @throws IOException 
     */
    private void addAlbum() throws IOException
    {
        int mode = 0;
        while(mode != 1)
        {
        try
        {
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        //Calls methods to gather info
        String artist = askArtist();
        String album = askAlbum();
        ArrayList<Track> tracks = askTracks();
        
        Album one = new Album(artist, album, tracks);
        pw.println();
        pw.append(one.otherToString());
        fw.close();
        pw.close();
        mode = 1;
        catalog.add(one);
        Collections.sort(catalog);
        displayNewCat();
        menu();
        mode = 1;
        }
        catch(FileNotFoundException e)
        {
         JOptionPane.showMessageDialog(null,"This file does not exist.");   
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());   
        }
        }
    }

    /**
     * Methods to gather the artist of the album being added to the catalog.
     * @return artist the name of the artist
     * @throws IOException 
     */
    private String askArtist() throws IOException
    {
            //Prompts the user to choose a file name
            String artist = JOptionPane.showInputDialog(null, "Enter the name of "
            + "the artist: ", "Add An Album!");
            //If the user chooses to exit or cancel, exit.
            if (artist == null) 
            {
                System.exit(0);
            }
            artist = artist.replaceAll(" ", "_");
            return artist;
    }
    
    /**
     * Method to gather the album name of the album being added to the catalog.
     * @return album the name of the album
     * @throws IOException 
     */
    private String askAlbum() throws IOException
    {
        //Prompts the user to choose a file name
        String album = JOptionPane.showInputDialog(null, "Enter the name of "
                + "the album: ", "Add An Album!");

            //If the user chooses to exit or cancel, exit.
            if (album == null) 
            {
                System.exit(0);
            }
            album = album.replaceAll(" ", "_");
        return album;
    }
    
    /**
     * Method to gather the tracks of the album being added to the catalog.
     * @return tracks an ArrayList of Track objects
     * @throws IOException 
     */
    private ArrayList<Track> askTracks() throws IOException
    {      
        int mode = JOptionPane.YES_OPTION;
        ArrayList<Track> tracks = new ArrayList<>();
        
        while(mode != JOptionPane.NO_OPTION)
        {
            mode = JOptionPane.showConfirmDialog(null, "Would You Like "
                    + "To Enter a Track?");
            if (mode == JOptionPane.YES_OPTION) 
            {
               String track = JOptionPane.showInputDialog(null, "Enter Track Name");
               track.replaceAll(" ", "_");
               tracks.add(new Track(track));
            }
        }
        Collections.sort(tracks);
                   return tracks;
    }
    
    /**
     * Method to display the updated catalog.
     */
    private void displayNewCat()
    {
        Collections.sort(catalog);
        //Sets up the display window 
        final ImageIcon icon = new ImageIcon("C:\\Users\\Carlos\\Documents"
                + "\\NetBeansProjects\\New Folder\\MidtermProject\\icon.png");
        JTextArea textArea = new JTextArea(20, 35);
        textArea.setText(catalog.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Prints the catalog with albums and tracks in alphabetic order
        JOptionPane.showMessageDialog(null, scrollPane, "Catalog of Albums",
                JOptionPane.INFORMATION_MESSAGE, icon);
    }
    }
