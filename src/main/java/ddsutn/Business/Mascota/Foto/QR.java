package ddsutn.Business.Mascota.Foto;

import java.io.*;
import java.util.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QR {

    public String generarQR(String id_qr_mascota) throws WriterException, IOException, NotFoundException {

        int imageSize = 200;
        String data = "¡Estoy perdida! Para ayudarme a encontrar a mi dueño completá el siguiente formulario: https://patitashtml.herokuapp.com/Datos_Mascota_Perdida.html?id=" + id_qr_mascota;
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, imageSize, imageSize);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", bos);
        String image = Base64.getEncoder().encodeToString(bos.toByteArray()); // base64 encode

        return "data:image/png;base64," + image;
    }

}
