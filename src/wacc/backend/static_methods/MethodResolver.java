package wacc.backend.static_methods;
import java.util.ArrayList;

public class MethodResolver {

    private static MethodResolver instance = null;
    private ArrayList<String> labels = new ArrayList<>();

    protected MethodResolver() {
      // Exists only to defeat instantiation.
    }

    public static MethodResolver resolver() {
      if(instance == null) {
         instance = new MethodResolver();
      }
      return instance;
    }

    public void addLabel(String label) {
        labels.add(label);
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

}