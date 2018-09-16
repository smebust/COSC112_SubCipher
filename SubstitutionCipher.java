import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

public class SubstitutionCipher extends Cipher{

    //Data member: List of chars in random order
    ArrayList subcipher1 = new ArrayList<Character>();

    //Constructor
    public SubstitutionCipher(long key){
        super(key);
        Random rand = new Random(getKey());
        ArrayList subcipher = new ArrayList<Character>();
        for (int i = 0; i<256; i++){
            subcipher.add((char) i);
        }

        /*
        Take values from subcipher and put them into subcipher1 in 
        random order, removing them from the former as they are added
        to the latter.

        Commented out code shows each step of this process.
        */
        for (int i = 256; i>0; i--){
            int toAdd = rand.nextInt(i);
            //System.out.println();
            //System.out.println("Taking: " +  subcipher.get(toAdd));
            if(subcipher.get(toAdd)==null){
                i++;
            } else {
                subcipher1.add(subcipher.get(toAdd));
                subcipher.remove(toAdd);
            }
            /*for(int j=0; j<subcipher.size(); j++){
                System.out.print(subcipher.get(j));
            }
            System.out.println();
            System.out.println();
            System.out.println("subcipher1:");
            for(int j=0; j<subcipher1.size(); j++){
                System.out.print(subcipher1.get(j));
            }
            System.out.println();*/
        }
    }


    public List<Character> encrypt (List<Character> cleartext){
        List<Character> ciphertext = new LinkedList<Character>();

        //give ciphertext encrypted character values 

        for(char clearchar : cleartext) {
            int index = (int) clearchar;

            char cipherchar = (char) subcipher1.get(index);

            ciphertext.add(cipherchar);
        }
        
        return ciphertext;
    }

    public List<Character> decrypt (List<Character> ciphertext){

        List<Character> cleartext = new LinkedList<Character>();

        //give cleartext decrypted character values 

        for(char cipherchar : ciphertext) {
            
            int i = 0; 

            while ((char) subcipher1.get(i) != (char) cipherchar){
                i++;
            }

            char clearchar = (char) i;

            cleartext.add(clearchar);
        }

        return cleartext;
    }
}