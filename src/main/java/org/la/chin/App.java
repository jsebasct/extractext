package org.la.chin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        TextReader textReader = new TextReader();

//        textReader.printSample();

        String response = textReader.getFilteredText();
        System.out.println(response);
    }
}
