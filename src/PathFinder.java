import java.io.File;
import java.io.IOException;

public class PathFinder {
    private String user = System.getProperty("user.home");
    private String seperator = System.getProperty("file.separator"); //Windows oder Mac
    private File newFolder;
    private String path;
    private File [] files;
    private File file;


    public void createFolder(){
        newFolder = new File(user+seperator+"Desktop"+seperator+"testFolder");
        path = String.valueOf(newFolder);
        System.out.println(newFolder);
        newFolder.mkdirs();
        setHiddenAttrib(newFolder);
    }

    public void createFiles(){
        createFolder();
        files = new File[10];
        int i = 0;
        for (File file2: files) {
            file2 = new File(path+seperator+"file.txt"+i);
            i++;
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Vom Internet die Methode, dient dazu, dass der Ordner unsichtbar wird, im Explore kann man aber einstellen, dass hidden-ordner angezeigt werden...
    private static void setHiddenAttrib(File file) {
        try {
            // execute attrib command to set hide attribute
            Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
            // for removing hide attribute
            //Process p = Runtime.getRuntime().exec("attrib -H " + file.getPath());
            p.waitFor();
            if(file.isHidden()) {
                System.out.println(file.getName() + " hidden attribute is set to true");
            }else {
                System.out.println(file.getName() + " hidden attribute not set to true");
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PathFinder pathFinder = new PathFinder();
        pathFinder.createFiles();

    }
}

