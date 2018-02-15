package cs445.a4;

/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

    /**
     * Adds a new song to the system. If the song is a null then the method
     * throws a NullPointerException. If the song is not a Song object then the
     * method throws a IllegalArgumentException. If the same
     * song exists in the system already then the method will not add it to
     * the system and the method will return false.
     * @param  theSong The song being added to the system
     * @return True if the song was able to be added to the system and false
     * if the song already exists in the system
     * @throws IllegalArgumentException If the song is anything other than a
     * song object type
     * @throws NullPointerException If the song is passed in as null
     */
    boolean addSong(Song theSong)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Removes an existing song from the system. If the song is not
     * an instance of the Song object the method will throw an
     * IllegalArgumentException. If the song is null then the method
     * will throw a NullPointerException. If the song is not in the system
     * then the method will return false.
     * @param  theSong The song being found and removed from the
     * @return True if the song was sucessfully remove and false if the song
     * does not exist in the system
     * @throws IllegalArgumentException If the song is not an instance of the
     * @throws NullPointerException If the song is passed in as null
     */
    boolean removeSong(Song theSong)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Adds an existing song to the playlist for an existing radio station.
     * Adds the song at the end of the playlist. If the station does not exist
     * then the method throughs an IllegalArgumentException. If the song already
     * exists in the station then the method will return false and not add it to
     * the station. If the station does not exist then this method will create
     * a new station that will store the song
     * @param  theSong The song that is being added to the station speified
     * @param  theStation The station that a song is being added to.
     * @return True if the song was successfully added to the station and false
     * if the song was unable to be added because it already exitst
     * @throws IllegalArgumentException If song or station is an object type
     * other than the Song or Station respectivily
     * @throws NullPointerException If song or station is an null value
     */
    boolean addToStation(Song theSong, Station theStation)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Removes a song from the playlist for a radio station. If the song does
     * not exist on that station then the method throws a
     * IllegalArgumentException. If the station does not exist then the methods
     * throws a IllegalArgumentException.
     * @param  theSong The Song object that is being removed from the specified
     * stations
     * @param  theStation The Station object that is having a song be remove from
     * it
     * @return True if the song was successfully remove from the station and
     * false if the song does not exist on the station
     * @throws IllegalArgumentException If the station does not exist
     * @throws NullPointerException If the song or the station is a null value
     */
    boolean removeFromStation(Song theSong, Station theStation)
    throws IllegalArgumentException, NullPointerException;


    /**
     * Sets a user's rating for a song, as a number of stars from 1 to 5. If
     * theSong does not exist in the system then the method throws an
     * IllegalArgumentException. If the rating is outside the range of 1 to 5
     * then the method throws an IllegalArgumentException. If the user does not
     * exist then the method will create a new user.
     * @param theUser The user that is rating the song
     * @param theSong The song that the user is rating
     * @param rate The rating the user is giving to the song on a scale from
     * 1 to 5 stars
     * @throws IllegalArgumentException if the song is not in the system or if
     * the rating is less than 1 or greater than 5
     * @throws NullPointerException if either the user or the song is null
     */
    void rateSong(User theUser, Song theSong, int rate)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Clears a user's rating on a song. If this user has rated this song and
     * the rating has not already been cleared, then the rating is cleared and
     * the state will appear as if the rating was never made. If there is no
     * such rating on record (either because this user has not rated this song,
     * or because the rating has already been cleared), then this method will
     * throw an IllegalArgumentException.
     * @param theUser user whose rating should be cleared
     * @param theSong song from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a
     * rating on record for the song
     * @throws NullPointerException if either the user or the song is null
     */
    public void clearRating(User theUser, Song theSong)
    throws IllegalArgumentException, NullPointerException;


    /**
     * Predicts the rating a user will assign to a song that they have not yet
     * rated, as a number of stars from 1 to 5. If the user has already rated
     * the song the method will throw an IllegalArgumentException. If the user
     * or the song do not exist then the method will throw an
     * IllegalArgumentException
     * @param theUser The user that is the method is predicting the rating will
     * be of a certain song
     * @param theSong The song that the method will predict a user would rate
     * @return a value from 1 to 5 which represents the predicted rating of
     * User
     * @throws IllegalArgumentException If the user has already rated the songs
     * or the user or the song does not exist
     * @throws NullPointerException If the user or the song are a null value
     */
    int predictRating(User theUser, Song theSong)
    throws IllegalArgumentException, NullPointerException;

    /**
     * Suggests a song for a user that they are predicted to like. If the user
     * has not liked a song the method will through an IllegalArgumentException.
     * If the user does not exist then the method will through an
     * IllegalArgumentException. If the method is unable to return a song
     * (say the user has all the songs and no new songs exist to be suggested)
     * then the method will return null
     * @param  theUser The user that the method will take there likes into
     * account and predict a song
     * @return The song that the method predicts the user will like unless if
     * there are no more songs that exist to be recommened then the method will
     * return null
     * @throws IllegalArgumentException If the user does not have any liked
     * songs or the user does not exist
     * @throws NullPointerException If the user is a null value
     */
    Song suggestSong(User theUser)
    throws IllegalArgumentException, NullPointerException;

}
