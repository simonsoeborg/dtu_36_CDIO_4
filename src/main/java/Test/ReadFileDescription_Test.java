package Test;

public class ReadFileDescription_Test {
    public static void main(String[] args) {
        String gameLanguage = "da";
        ReadFieldDescription RFD_Test = new ReadFieldDescription();

        String test = RFD_Test.ReadFieldDescription(gameLanguage, 2);
        gameLanguage = "en";
        String test2 = RFD_Test.ReadFieldDescription(gameLanguage, 0);
        System.out.println(test);
        System.out.println(test2);

        gameLanguage = "de";
        String test3 = RFD_Test.ReadFieldDescription(gameLanguage, 1);
    }
}