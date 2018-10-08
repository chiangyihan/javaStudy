import org.dcm4che3.imageio.codec.Decompressor;

import java.io.File;


public class DCMEXETest {
//    public static void main(String[] args) {
//        String name = "zhang gui hua F 42 y";
//        String nname = name.replaceAll(" ", "");
//        System.out.println(nname);
//        System.out.println("name:"+nname.substring(nname.length()-3,nname.length()));
//        System.out.println("sex:"+nname.substring(nname.length()-4,nname.length()-3));
//        switch (nname.substring(nname.length()-4,nname.length()-3)){
//            case "f" :
//                System.out.println(1);break;
//            case "F":
//                System.out.println(2);break;
////            case "m"
//            default:
//                System.out.println(3);break;
//        }
//    }

    public static void main(String[] args) {
        FormatUtil.dicomWrite("/home/qiang/ding/yihan/17603_000006.dcm","/home/qiang/ding/yihan/17603_000006.png");
    }
}
