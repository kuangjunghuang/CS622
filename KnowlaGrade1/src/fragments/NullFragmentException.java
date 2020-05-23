package fragments;

/* This is my Exception class, I have named it NullFragmentException
 */

public class NullFragmentException extends Exception{
   String str = "Fragment is Null";
   /* Constructor of custom exception class
    * here I am copying the message that we are passing while
    * throwing the exception to a string and then displaying
    * that string along with the message.
    */
   NullFragmentException(String str1) {
		str=str1;
   }

   public String toString(){
		return ("RullFragmentException Occurred: "+str);
   }

   public String getMessage(){
   		return toString();
   }
}