import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.util.SafeClose;

import java.io.File;
import java.io.IOException;

public class MetaRead {
    public static boolean uncompress(String url) throws IOException {
        boolean isUncompress = false;
//        File srcFile = new File(url);
//        File outFile = new File("G:/DicomImage/mini/L_" + srcFile.getName());
        DicomInputStream dis = null;
        DicomOutputStream dos = null;
//        StreamDecompressor decompressor = null;
//        StreamCompressor compressor = null;
        try {
            dis = new DicomInputStream(new File("d:/cv2/50991.dcm"));
//            dos = new DicomOutputStream(outFile);
            Attributes fmi = dis.readFileMetaInformation();
            String tsuid = fmi.getString(Tag.TransferSyntaxUID);
            System.out.println(tsuid);
//            fmi.setString(Tag.TransferSyntaxUID, VR.UI,
//                    "1.2.840.10008.1.2.1");
//            dos.writeFileMetaInformation(fmi);
//            decompressor = new StreamDecompressor(dis, tsuid, dos);
//            decompressor.decompress();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SafeClose.close(dis);
//            SafeClose.close(dos);
            isUncompress = true;
//            if (decompressor != null)
//                decompressor.dispose();
        }
        return isUncompress;
    }
}
