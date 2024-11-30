import UseCases.VideoConverter;

public class App {
    public static void main(String[] args) throws Exception {
        VideoConverter converter = new VideoConverter();

        String convertedFile = converter.convert("funny cats.ogg", "mp4");

        // save video
    }
}
