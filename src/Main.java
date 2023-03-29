import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Main {
    public static void main(String[] args) {
        String zipPath = "D:/Games/savegames/zip.zip";
        String dirOutPath = "D:/Games/savegames/";
        openZip(zipPath, dirOutPath);
        System.out.println(openProgress(dirOutPath + "save2.dat"));
    }

    public static void openZip(String zipPath, String dirOutPath) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(dirOutPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fos.write(c);
                }
                fos.flush();
                zin.closeEntry();
                fos.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String fileSavePath) {
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(fileSavePath))) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
