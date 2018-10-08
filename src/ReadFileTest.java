import entity.DcmLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ReadFileTest {
    public ReadFileTest() {
    }

    /**
     * read file,and make convert dcm to png
     */
    public static void readfile(String filepath, String outPath) throws Exception {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                if (file.getName().contains(".dcm") && !file.getName().contains(".png")) {
                    FormatUtil.dicomWrite(file.getAbsolutePath(), outPath + "/" + file.getName() + ".png");
                    System.out.println("deal:" + outPath + file.getName() + ".png");
                }
                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "/" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        if(readfile.getName().contains(".dcm")) {
                            DcmLog dcmLog = FormatUtil.insertToDB(filepath + File.separator + readfile.getName());

                            String fileAbsolutePath = readfile.getAbsolutePath();
                            String outputPngPath = outPath + File.separator + readfile.getName().replace(".dcm", ".png");


                            FormatUtil.linuxDcmWriter(fileAbsolutePath, outputPngPath, "dcm2png");
                            String finalPngName = FormatUtil.returnDicomToPngName(outPath + File.separator + readfile.getName());
                            String fPN[] = finalPngName.split("/");
                            System.out.println("dicom转换:" + fileAbsolutePath + "===>>> " + finalPngName);

                            dcmLog.setDcmName(fPN[fPN.length-1]);
                            JDBCUtil.insert(dcmLog);
                        }
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + File.separator + filelist[i], outPath);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String filePath = args[0];
            String outPath =args[1];

            readfile(filePath, outPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ok");
    }
}
