/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Julie Zelenski
 * @author Chris Nevison
 * @author Cay Horstmann
 */

package gridworld.gui;

import gridworld.actor.*;
import gridworld.grid.Grid;
import gridworld.grid.Location;
import gridworld.world.World;
import project.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;


/**
 * The WorldFrame displays a World and allows manipulation of its occupants.
 * This is the JFrame,
 * <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class WorldFrame<T> extends JFrame
{
    private static int count = 0;

    private GUIController<T> control;

    private GridPanel display;

    // private JTextArea messageArea;

    private ArrayList<JMenuItem> menuItemsDisabledDuringRun;

    private World<T> world;

    private ResourceBundle resources;

    private DisplayMap displayMap;

    private Set<Class> gridClasses;

    private Point mousePos;


    /**
     * Constructs a WorldFrame that displays the occupants of a world
     *
     * @param world the world to display
     */
    public WorldFrame( World<T> world )
    {
        this.world = world;
        count++;
        resources = ResourceBundle.getBundle( getClass().getName() + "Resources" );
        try
        {
            System.setProperty( "sun.awt.exception.handler", GUIExceptionHandler.class.getName() );
        }
        catch ( SecurityException ex )
        {
            // will fail in an applet
        }

        addWindowListener( new WindowAdapter()
        {
            public void windowClosing( WindowEvent event )
            {
                count--;
                if ( count == 0 )
                    System.exit( 0 );
            }
        } );

        displayMap = new DisplayMap();
        String title = null;
        try // won't work in applets
        {
            System.getProperty( "gridworld.gui.frametitle" );
        }
        catch ( SecurityException ex )
        {
        }
        if ( title == null )
            title = resources.getString( "frame.title" );
        setTitle( title );
        setLocation( 25, 25 );
        //         setLocationRelativeTo( null );
        //        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //        this.setLocation(dim.width/2-this.getSize().width/2, dim
        // .height/2-this.getSize().height/2);
        //        this.pack();
        //        this.setLocationRelativeTo(null);

        setPreferredSize( new Dimension( 630, 750 ) );//656 (width),794 (height) pixels for
        //27 ROW and 24 COL
        //True for testing
        setResizable( false );

        setUndecorated( true );

        /////DRAG ON YELLOW BAR AT BOTTOM TO MOVE
        addMouseListener( new MouseAdapter()
        {
            @Override public void mousePressed( MouseEvent e )
            {
                mousePos = e.getPoint(); // update the position
            }
        } );
        addMouseMotionListener( new MouseAdapter()
        {
            @Override public void mouseDragged( MouseEvent e )
            {
                Point newPoint = e.getLocationOnScreen();
                newPoint.translate( -mousePos.x, -mousePos.y ); // Moves the point by given
                // values from its location
                setLocation( newPoint ); // set the new location
            }
        } );
        /////
        URL appIconUrl = getClass().getResource( "GridWorld.gif" );
        if ( appIconUrl != null )
        {
            ImageIcon appIcon = new ImageIcon( appIconUrl );
            setIconImage( appIcon.getImage() );
        }

        //        makeMenus();

        JPanel content = new JPanel();
        //        content.setBackground( Color.BLUE );
        //        content.setBorder( BorderFactory.createEmptyBorder( 15, 15,
        // 15, 15 ) );
        content.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 0 ) );
        content.setLayout( new BorderLayout() );
        setContentPane( content );

        display = new GridPanel( displayMap, resources, this );

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( new KeyEventDispatcher()
        {
            public boolean dispatchKeyEvent( KeyEvent event )
            {
                if ( getFocusOwner() == null )
                    return false;
                String text = KeyStroke.getKeyStrokeForEvent( event ).toString();
                final String PRESSED = "pressed ";
                int n = text.indexOf( PRESSED );
                if ( n < 0 )
                    return false;
                // filter out modifier keys; they are neither
                // characters or actions
                if ( event.getKeyChar() == KeyEvent.CHAR_UNDEFINED && !event.isActionKey() )
                    return false;
                text = text.substring( 0, n ) + text.substring( n + PRESSED.length() );
                boolean consumed = getWorld().keyPressed( text, display.getCurrentLocation() );
                if ( consumed )
                    repaint();
                return consumed;
            }
        } );

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewport( new PseudoInfiniteViewport( scrollPane ) );
        scrollPane.setViewportView( display );

        content.add( scrollPane, BorderLayout.CENTER );

        gridClasses = new TreeSet<Class>( new Comparator<Class>()
        {
            public int compare( Class a, Class b )
            {
                return a.getName().compareTo( b.getName() );
            }
        } );
        for ( String name : world.getGridClasses() )
            try
            {
                gridClasses.add( Class.forName( name ) );
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }

        Grid<T> gr = world.getGrid();
        gridClasses.add( gr.getClass() );

        control = new GUIController<T>( this, display, displayMap, resources );
        content.add( control.controlPanel(), BorderLayout.SOUTH );

        pack();
        repaint(); // to show message
        display.setGrid( gr );

    }


    /**
     * Gets the world that this frame displays
     *
     * @return the world
     */
    public World<T> getWorld()
    {
        return world;
    }


    /**
     * Repaints the frame, but can also be used as a gameloop
     */
    public void repaint()
    {
        display.repaint(); // for applet
        super.repaint();

        // Checks to see if there is 3 ghosts
//        if(Main.currentLevel == 1)
//        {
//            int ghostCount = 0;
//            ArrayList<Location> loc = Main.grid.getOccupiedLocations();
//            for(Location l: loc)
//            {
//                Actor a = (Actor)Main.grid.get( l );
//                if ( a instanceof Ghost )
//                {
//                    ghostCount++;
//                }
//            }
//
//            while(ghostCount <  3)
//            {
//                Inky c = new Inky( );
//                c.putSelfInGrid( Main.grid , new Location(10,11) );
//                ghostCount--;
//            }
//        }
        if ( Ghost.getPacmanLocation() == null )
        {
            String[] arr = { "G", "A", "M", "E", " ", "O", "V", "E", "R", "!" };
            gridMessage( arr, 13, 7 );
        }
        if ( Score.score == 250 )
        {
            String[] arr = { "Y", "O", "U", "", " W", "O", "N", "!", "!" };
            gridMessage( arr, 13, 9 );

        }

    }


    public static void gridMessage( String[] arr, int row, int col )
    {

        for ( String m : arr )
        {
            TextCell t = new TextCell( m );
            t.putSelfInGrid( Main.grid, new Location( row, col ) );
            col++;
        }

    }


    /**
     * Sets a new grid for this world. Occupants are transferred from
     * the old world to the new.
     *
     * @param newGrid the new grid
     */
    public void setGrid( Grid<T> newGrid )
    {
        Grid<T> oldGrid = world.getGrid();
        Map<Location, T> occupants = new HashMap<Location, T>();
        for ( Location loc : oldGrid.getOccupiedLocations() )
            occupants.put( loc, world.remove( loc ) );

        world.setGrid( newGrid );
        for ( Location loc : occupants.keySet() )
        {
            if ( newGrid.isValid( loc ) )
                world.add( loc, occupants.get( loc ) );
        }

        display.setGrid( newGrid );
        repaint();
    }


    /**
     * Displays an error message
     *
     * @param t        the throwable that describes the error
     * @param resource the resource whose .text/.title strings
     *                 should be used in the dialog
     */
    public void showError( Throwable t, String resource )
    {
        String text;
        try
        {
            text = resources.getString( resource + ".text" );
        }
        catch ( MissingResourceException e )
        {
            text = resources.getString( "error.text" );
        }

        String title;
        try
        {
            title = resources.getString( resource + ".title" );
        }
        catch ( MissingResourceException e )
        {
            title = resources.getString( "error.title" );
        }

        String reason = resources.getString( "error.reason" );
        String message = text + "\n" + MessageFormat.format( reason, t );

        JOptionPane.showMessageDialog( this, message, title, JOptionPane.ERROR_MESSAGE );
    }


    /**
     * Nested class that is registered as the handler for exceptions on the
     * Swing event thread. The handler will put up an alert panel and dump the
     * stack trace to the console.
     */

    public class GUIExceptionHandler
    {
        public void handle( Throwable e )
        {
            e.printStackTrace();

            JTextArea area = new JTextArea( 10, 40 );
            StringWriter writer = new StringWriter();
            e.printStackTrace( new PrintWriter( writer ) );
            area.setText( writer.toString() );
            area.setCaretPosition( 0 );
            String copyOption = resources.getString( "dialog.error.copy" );
            JOptionPane pane = new JOptionPane( new JScrollPane( area ),
                            JOptionPane.ERROR_MESSAGE,
                            JOptionPane.YES_NO_OPTION,
                            null,
                            new String[] { copyOption, resources.getString( "cancel" ) } );
            pane.createDialog( WorldFrame.this, e.toString() ).setVisible( true );
            if ( copyOption.equals( pane.getValue() ) )
            {
                area.setSelectionStart( 0 );
                area.setSelectionEnd( area.getText().length() );
                area.copy(); // copy to clipboard
            }
        }
    }
}