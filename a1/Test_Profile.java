package cs445.a1;

public class Test_Profile {
  public static void main(String args[]) {
    Profile user1 = new Profile("Jill", "I like dogs");
    Profile user2 = new Profile();
    Profile user3 = new Profile("Jerry", "I like cats");
    Profile user4 = new Profile("Jam", "I like dogs");
    Profile user5 = new Profile("PJ", "I like dogs");
    Profile user6 = new Profile("Nick", "I like dogs");
    System.out.println("Name: " + user1.getName()+ "\nAbout: "
                                + user1.getAbout());
    System.out.println("Name: " + user2.getName()+ "\nAbout: "
                                + user2.getAbout());
    user2.setName("Jack");
    user2.setAbout("I like cats");
    System.out.println("Name: " + user2.getName()+ "\nAbout: "
                                + user2.getAbout());
    user2.follow(user1);
    user2.follow(user3)
    // System.out.println("Unfollow: " + user2.unfollow(user1));
    // System.out.println("Unfollow: " + user2.unfollow(user3));
    printArr(user2.following(2));
    user1.follow(user2);
    user1.follow(user3);
    user1.follow(user4);
    user1.follow(user5);
    user1.follow(user6);
    user2.follow(user4);
    user2.follow(user5);
    System.out.println("I recommend following" + user1.recommend().getName);


  }

  public static void printArr(ProfileInterface[] arr){
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i].getName() + " ");
    }
    System.out.println();
  }
}
