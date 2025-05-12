package br.com.seubanco.boleto.pdf;

import br.com.seubanco.boleto.builder.Boleto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.Code128Writer;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BoletoPdfGenerator {

    public static void gerar(Boleto boleto, String nomeArquivo) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);

            float pageWidth = PDRectangle.A4.getWidth();
            float marginLeft = 50;
            float maxTextWidth = pageWidth - 2 * marginLeft;
            float cursorY = 770;

            // Logo do banco
            String codigoBanco = boleto.getCodigoDeBarras().substring(0, 3);
            String caminhoLogo = switch (codigoBanco) {
                case "001" -> "src/main/resources/images/bb.png";
                case "341" -> "src/main/resources/images/itau.png";
                case "237" -> "src/main/resources/images/bradesco.png";
                default -> null;
            };

            if (caminhoLogo != null) {
                PDImageXObject logo = PDImageXObject.createFromFile(caminhoLogo, document);
                content.drawImage(logo, marginLeft, cursorY - 30, 100, 30);
            }

            // Título "Boleto Bancário"
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 20);
            content.newLineAtOffset(marginLeft + 120, cursorY - 5);
            content.showText("BOLETO BANCÁRIO");
            content.endText();

            cursorY -= 50;

            // Linha Digitável quebrada em duas partes se necessário
            String linha = boleto.getLinhaDigitavel();
            String linha1 = linha.length() > 36 ? linha.substring(0, 36) : linha;
            String linha2 = linha.length() > 36 ? linha.substring(36) : "";

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.newLineAtOffset(marginLeft, cursorY);
            content.showText("Linha Digitável:");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 16);
            content.newLineAtOffset(marginLeft + 130, cursorY);
            content.showText(linha1);
            content.endText();

            if (!linha2.isEmpty()) {
                cursorY -= 20;
                content.beginText();
                content.setFont(PDType1Font.COURIER_BOLD, 16);
                content.newLineAtOffset(marginLeft + 130, cursorY);
                content.showText(linha2);
                content.endText();
            }

            cursorY -= 40;

            // Código de barras (imagem)
            BufferedImage barcodeImage = gerarCodigoDeBarras(boleto.getCodigoDeBarras());
            PDImageXObject barcode = PDImageXObject.createFromByteArray(document, toByteArray(barcodeImage), "barcode");
            float barcodeWidth = 400;
            float barcodeX = (pageWidth - barcodeWidth) / 2;
            content.drawImage(barcode, barcodeX, cursorY - 40, barcodeWidth, 50);

            cursorY -= 60;

            // Código de barras (texto)
            content.beginText();
            content.setFont(PDType1Font.COURIER, 12);
            content.newLineAtOffset(marginLeft, cursorY);
            content.showText("Código de Barras: " + boleto.getCodigoDeBarras());
            content.endText();

            content.close();
            document.save(new File(nomeArquivo));
            System.out.println("PDF gerado: " + nomeArquivo);

        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage gerarCodigoDeBarras(String codigo) throws WriterException {
        Code128Writer writer = new Code128Writer();
        BitMatrix matrix = writer.encode(codigo, BarcodeFormat.CODE_128, 400, 50);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    private static byte[] toByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        }
    }
}
