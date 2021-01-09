
public class CardTester 
{
    public static void main(String[] args) {
        Card one = new Card("Ace", 1);
        one.setFaceUp(true);
        System.out.println(one.toString());
        Card two = new Card("jack", 11);
        two.setFaceUp(true);
        System.out.println(two.toString());
        Card three = new Card("king", 13);
        three.setFaceUp(false);
        Card four = new Card("king", 13);
        System.out.println(three.toString());
        System.out.println(two.compareTo(one));
        System.out.println(three.compareTo(two));
        System.out.println(two.equals(one));
        System.out.println(three.equals(four));
       
    }
}
