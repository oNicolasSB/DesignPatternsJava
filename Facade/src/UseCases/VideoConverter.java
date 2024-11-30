package UseCases;

public class VideoConverter {
    public String convert(String fileName, String format) {
        //Code from a third party library that cant be manipulated by us
        System.out.println("Converting file");
        return "Converted file";
    }
}
