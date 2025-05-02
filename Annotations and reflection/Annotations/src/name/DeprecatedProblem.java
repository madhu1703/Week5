package name;
class LegacyAPI {
    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature. Avoid using this.");
    }
    public void newFeature() {
        System.out.println("This is the new and improved feature.");
    }
}


public class DeprecatedProblem {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature();
        api.newFeature();
    }
}

