public class palindromecheck {

    public static void main(String[] args){

        //Three tests for the bonus
        String test0 = "Never odd or even";
        String test1 = "Able was I ere I saw Elba";
        String test2 = "A man, a plan, a canal: Panama!";

        System.out.println("Is '" + test0 + "' a palindrome: " + palindromeChecker(test0));
        System.out.println("Is '" + test1 + "' a palindrome: " + palindromeChecker(test1));
        System.out.println("Is '" + test2 + "' a palindrome: " + palindromeChecker(test2));
    }

    public static boolean palindromeChecker(String string){
        //checks for only letters and not spaces, punctuation and is now case insensitive
        String onlyLetters = string.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String onlyLettersBackwards = backwards(onlyLetters);

        if(onlyLetters.equals(onlyLettersBackwards)){
            return true;
        }
        return false;
    }


    public static String backwards(String s){
        if( s.isEmpty() || s == null) {
            return s;
        }
        return s.charAt(s.length() - 1) + backwards(s.substring(0, s.length() - 1));
    }
}