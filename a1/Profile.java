package cs445.a1;

import java.io.Serializable;
import java.lang.Math;

public class Profile implements ProfileInterface {

  private String name;
  private String about;
  private SetInterface<ProfileInterface> followees;

  public Profile() {
    this("", "");
  }


  public Profile(String name, String about) {
    this.name = name;
    this.about = about;
    this.followees = new Set<ProfileInterface>();
  }

  /**
   * Sets this profile's name.
   *
   * <p> If newName is not null, then setName modifies this profile so that
   * its name is newName. If newName is null, then setName throws
   * IllegalArgumentException without modifying the profile, for example:
   *
   * <p> {@code throw new IllegalArgumentException("Name cannot be null")}
   *
   * @param newName  The new name
   * @throws IllegalArgumentException  If newName is null
   */
  @Override
  public void setName(String newName) throws IllegalArgumentException{
    this.name = newName;
  }

  /**
   * Gets this profile's name.
   *
   * @return  The name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets this profile's "about me" blurb.
   *
   * <p> If newAbout is not null, then setAbout modifies this profile so that
   * its about blurb is newAbout. If newAbout is null, then setAbout throws
   * IllegalArgumentException without modifying the profile.
   *
   * @param newAbout  The new blurb
   * @throws IllegalArgumentException  If newAbout is null
   */
  @Override
  public void setAbout(String newAbout) throws IllegalArgumentException {
    this.about = newAbout;
  }

  /**
   * Gets this profile's "about me" blurb
   *
   * @return  The blurb
   */
  @Override
  public String getAbout() {
    return about;
  }

  /**
   * Adds another profile to this profile's following set.
   *
   * <p> If this profile's following set is at capacity, or if other is null,
   * then follow returns false without modifying the profile. Otherwise, other
   * is added to this profile's following set and follow returns true. If this
   * profile already followed other, then follow returns true even though no
   * changes were needed.
   *
   * @param other  The profile to follow
   * @return  True if successful, false otherwise
   */
  @Override
  public boolean follow(ProfileInterface other) {
    if(this.equals(other)) {
      return false;
    }
    try {

      return followees.add(other);
    } catch(SetFullException e) {
      System.out.println("Set is Full");
    }
    return false;
  }

  /**
   * Removes the specified profile from this profile's following set.
   *
   * <p> If this profile's following set does not contain other, or if other
   * is null, then unfollow returns false without modifying the profile.
   * Otherwise, this profile in modified in such a way that other is removed
   * from this profile's following set.
   *
   * @param other  The profile to follow
   * @return  True if successful, false otherwise
   */
  @Override
  public boolean unfollow(ProfileInterface other) {
    return followees.remove(other);

  }

  /**
   * Returns a preview of this profile's following set.
   *
   * <p> The howMany parameter is a maximum desired size. The returned array
   * may be less than the requested size if this profile is following fewer
   * than howMany other profiles. Clients of this method must be careful to
   * check the size of the returned array to avoid
   * ArrayIndexOutOfBoundsException.
   *
   * <p> Specifically, following returns an array of size min(howMany, [number
   * of profiles that this profile is following]). This array is populated
   * with arbitrary profiles that this profile follows.
   *
   * @param howMany  The maximum number of profiles to return
   * @return  An array of size &le;howMany, containing profiles that this
   * profile follows
   */
  @Override
  public ProfileInterface[] following(int howMany) {
    Object[] tempArr = followees.toArray();
    ProfileInterface[] resultArr =
                      new ProfileInterface[smallerNum(howMany, tempArr.length)];
    int index = 0;
    while(index < howMany && index < tempArr.length){
      resultArr[index] = (ProfileInterface) tempArr[index];
      index++;
    }
    return resultArr;
  }
  /**
   * Takes in two numbers and returns the smaller value
   *
   * @param num1 One of the numbers to compare
   * @param num2 The other number to compare
   * @return The smaller number
   */
  private int smallerNum(int num1, int num2){
    if(num1 < num2)
      return num1;
    else
      return num2;
  }

  /**
   * Recommends a profile for this profile to follow. This returns a profile
   * followed by one of this profile's followed profiles. Should not recommend
   * this profile to follow someone they already follow, and should not
   * recommend to follow oneself.
   *
   * <p> For example, if this profile is Alex, and Alex follows Bart, and Bart
   * follows Crissy, this method might return Crissy.
   *
   * @return  The profile to suggest, or null if no suitable profile is found
   * (only if all of this profile's followees' followees are already followed
   * or this profile itself).
   */
  @Override
  public ProfileInterface recommend() {

    ProfileInterface[] fArr = following(numberOfFollowers());
    for(int i = 0; i < fArr.length; i++){
      ProfileInterface kevinBacon = fArr[i];
      ProfileInterface[] baconArr = kevinBacon.following(((Profile) kevinBacon).numberOfFollowers());

      for(int j = 0; j < baconArr.length; j++) {
        boolean unique = true;
        for(int k = 0; k < fArr.length; k++) {
          if(fArr[k].equals(baconArr[j]) || this.equals(baconArr[j])) {
            unique = false;
          }
        }
        if(unique) {
          return baconArr[j];
        }
      }

    }
    return null;
  }

  /**
   * A getter to get the current size of the set
   *
   * @return The number current size of the set followees
   */
  public int numberOfFollowers(){
    return followees.getCurrentSize();
  }
}
