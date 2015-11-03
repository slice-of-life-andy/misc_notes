import javax.inject.Inject;

public class HowtoUse {


    @Inject
    @Config
    private String serverAddress;

    public HowtoUse() {
        System.out.println("server Address " + serverAddress);
    }

