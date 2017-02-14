import java.io.File;
import java.util.ArrayList;

/**
 * @author Kristian
 */
public class GetImages  {
    public static void main(String[] args) {
        String[] fileTypes = {"jpg", "png"};
        File folder = new File("./");
        String[] parts;
        ArrayList<File> listOfFiles = new ArrayList<>();

        for (File f: folder.listFiles()) {
            listOfFiles.add(f);
        }

        System.out.println(listOfFiles);

        for (int i = 0; i < listOfFiles.size(); i++) {
            if (listOfFiles.get(i).isFile()) {
                parts = listOfFiles.get(i).getName().split("\\.");

                String extension = parts[parts.length-1];
                System.out.println(extension);

                boolean remove = true;
                for (String s : fileTypes) {
                    if (extension.equals(s)) {
                        remove = false;
                        break;
                    }
                }
                if (remove) {
                    listOfFiles.remove(i);
                    i--;
                }

            } else {
                listOfFiles.remove(i);
                i--;
            }
        }

        System.out.println(listOfFiles);
    }
}
