import entity.DcmLog;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.util.SafeClose;
import org.dcm4che3.data.Tag;


import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FormatUtil {

    //src表示读取路径，path表示输出路径
    public static void Conversion(String src, String path) {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(src));
            BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
            grayImage.getGraphics().drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
            ImageIO.write(grayImage, "png", new File(path));
        } catch (Exception e) {
            System.out.println("转PNG格式转换出错");
            e.printStackTrace();
        }
    }

    //      判断文件是否为图片
    public static boolean isImage(File file) {
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * dicom转png(16bit)
     *
     * @param src  读取文件路径
     * @param path 保存文件路径
     */
    public static void dicomWrite(String src, String path) {
        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("DICOM");
        File file = new File(src);
        if (iterator.hasNext()) {
            ImageReader imageReader = iterator.next();
            try {
                FileInputStream fis = new FileInputStream(file);
                imageReader.setInput(fis);
                DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader.getDefaultReadParam();
                BufferedImage bufferedImage = imageReader.read(0, dicomImageReadParam);
                BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
                grayImage.getGraphics().drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
                fis.close();
                if (bufferedImage == null) {
                    System.out.println("Could not read image!!");
                } else {
                    ImageIO.write(grayImage, "png", new File(path));
                }
            } catch (IOException e) {
                System.out.println("DCM转PNG格式转换出错");
                e.printStackTrace();
            }
        }
    }


    //    保留double类型的几位小数
    public static Double hodedouble(Double doubleX, Integer number) {
        BigDecimal bigDecimal = new BigDecimal(doubleX);
        double data = bigDecimal.setScale(number, BigDecimal.ROUND_HALF_UP).doubleValue();
        return data;
    }

    public static DcmLog insertToDB(String path) throws Exception {

        List<DcmLog> dicomList = new ArrayList();
        String src[] = path.split("/");
        String realname = src[src.length - 1];
        try {
            File dicomFile = new File(path);
            DicomInputStream dis = new DicomInputStream(dicomFile);
            org.dcm4che3.data.Attributes attrs;

            try {
                dis.setIncludeBulkData(DicomInputStream.IncludeBulkData.URI);
                attrs = dis.readDataset(-1, -1);
            } finally {
                SafeClose.close(dis);
            }
            DcmLog dicom = new DcmLog();
            dicom.setPatientName(attrs.getString(1048592));
            if (attrs.getString(Tag.PatientSex) != null) {
                //1男0女
                dicom.setDcmSex(attrs.getString(Tag.PatientSex).equals("F") ? 0 : 1);
                // 046Y 1052688
                dicom.setDcmAge(attrs.getString(Tag.PatientAge));
            } else {
                String uname = dicom.getPatientName().replaceAll(" ", "");
                String uage = uname.substring(uname.length() - 3, uname.length());
//                String usex = uname.substring(uname.length() - 4, uname.length() - 3);
//                dicom.setDcmSex(usex.equals("f") ? 0 : 1);//1男0女
                dicom.setDcmAge(uage);
            }
            //左0右1无1
            dicom.setDcmLR("L".equals(attrs.getString(2097250)) ? 0 : 1);
            dicom.setDcmName(realname.replace(".dcm", ""));
            //cc mlo
            dicom.setDcmCC(attrs.getString(1593601));
            // StudyDate
            if (StringUtils.isNotBlank(attrs.getString(524320))) {
                dicom.setDcmDate(attrs.getString(524320));
            }
            // StudyTime
            if (StringUtils.isNotBlank(attrs.getString(524336))) {
                dicom.setDcmTime(attrs.getString(524336));
            }
//            System.out.println(dicom);
            return dicom;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(realname + "解析错误");
        }


    }


    //    linux环境dcm转png
    public static void linuxDcmWriter(String src, String path, String commandPath) {
        String command = commandPath + " -i " + src + " -o " + path;
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedInputStream bis = new BufferedInputStream(
                    process.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
            }

            process.waitFor();
            if (process.exitValue() != 0) {
                System.out.println("error!");
            }

            bis.close();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //  检测文件是否存在
    public static String returnDicomToPngName(String dicomName) {
        String returnPngName = null;
        try {
            String pngNameNormal = dicomName.replace(".dcm", "_NORMAL.png");
            String pngNameSofter = dicomName.replace(".dcm", "_SOFTER.png");
            String pngNameHarder = dicomName.replace(".dcm", "_HARDER.png");
            String pngNameUser = dicomName.replace(".dcm", "_USER.png");
            String pngName0 = dicomName.replace(".dcm", "_0.png");
            String pngName1 = dicomName.replace(".dcm", "_1.png");
            String pngName2 = dicomName.replace(".dcm", "_2.png");
            String pngName3 = dicomName.replace(".dcm", "_3.png");
            String pngNamePng = dicomName.replace(".dcm", ".png");

            if (new File(pngNameNormal).exists()) {
                returnPngName = pngNameNormal;
                deleteFile(pngName0);
            } else if (new File(pngName2).exists()) {
                returnPngName = pngName2;
                deleteFile(pngName0);
            } else if (new File(pngName0).exists()) {
                returnPngName = pngName0;
            } else if (new File(pngNamePng).exists()) {
                returnPngName = pngNamePng;
            } else {
                returnPngName = null;
            }


            deleteFile(pngName1);
            deleteFile(pngName3);
            deleteFile(pngNameSofter);
            deleteFile(pngNameHarder);
            deleteFile(pngNameUser);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnPngName;
    }

    private static Boolean deleteFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
                System.out.println(path + "删除成功");
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
