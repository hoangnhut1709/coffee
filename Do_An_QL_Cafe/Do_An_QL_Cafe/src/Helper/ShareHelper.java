/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.TaiKhoan;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


/**
 *
 * @author trung
 */
public class ShareHelper {
    public static boolean saveLogo(File file) {
        File dir = new File("src\\image\\");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static ImageIcon readLogo(String fileName) {
        File path = new File("src\\image\\", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    public static TaiKhoan USER = null;

    public static void logoff() {  
        ShareHelper.USER = null;  
    }
    
    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }
}
