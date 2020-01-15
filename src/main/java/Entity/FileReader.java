package Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    /**
     * @param file1 : First file to read from
     * @param file2 : Second file to read from
     * @param file3 : Third file to read from
     */

    /**
     * @param line: The specific line to read
     * @return the content of the line
     */
    public String read(int filenumber, int line){

        String contentOfLine = "";
        String file = "";

        //Hvis vi vil have flere filer skal vi tilføje flere cases i switch-casen
        String file1 = "src/TextFiles/1FieldName_da.txt";
        String file2 = "src/TextFiles/2FieldDescription_da.txt";
        String file3 = "src/TextFiles/3ActionDescriptions_da.txt";
        String file4 = "src/TextFiles/4SameColoredFieldsInfo.txt";
        String file5 = "src/TextFiles/5ChanceCards.txt";
        switch (filenumber) {
            case 1: file = file1;
                break;
            case 2: file = file2;
                break;
            case 3: file = file3;
                break;
            case 4: file = file4;
                break;
            case 5: file = file5;
                break;
            default: System.out.println("Filen findes ikke");
        }


        try{
            Stream<String> text = Files.lines(Paths.get(file));
            contentOfLine = text.skip(line-1).findFirst().get();

        }catch (IOException e){

            System.out.println("Filen kunne ikke findes");
        }

        return contentOfLine;
    }
}
