package br.com.seubanco.boleto.pdf;

import br.com.seubanco.boleto.builder.Boleto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

public class BoletoPdfGenerator {

    public static void gerar(Boleto boleto, String nomeArquivoPdf) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                float y = 750;
                float pageWidth = PDRectangle.A4.getWidth();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 20);
                content.newLineAtOffset((pageWidth - 200) / 2, y);
                content.showText("BOLETO BANCÁRIO");
                content.endText();

                y -= 60;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 12);
                content.newLineAtOffset(50, y);
                content.showText("Linha Digitável:  " + boleto.getLinhaDigitavel());
                content.endText();

                y -= 20;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Beneficiário: " + boleto.getBeneficiario().getNome() +
                        " (CNPJ: " + boleto.getBeneficiario().getDocumento() + ")");
                content.endText();

                y -= 15;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Agência: " + boleto.getBeneficiario().getAgencia() +
                        "   Conta: " + boleto.getBeneficiario().getConta() +
                        "   Carteira: " + boleto.getBeneficiario().getCarteira());
                content.endText();

                y -= 20;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Sacado: " + boleto.getSacado().getNome() +
                        " (CPF: " + boleto.getSacado().getDocumento() + ")");
                content.endText();

                y -= 15;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Endereço: " + boleto.getSacado().getEndereco().formatado());
                content.endText();

                y -= 20;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Título: Nº " + boleto.getTitulo().getNumeroDocumento() +
                        "   Vencimento: " + boleto.getTitulo().getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        "   Valor: R$ " + boleto.getTitulo().getValor().setScale(2));
                content.endText();

                y -= 100;
                // Gera imagem do código de barras com ZXing
                BitMatrix bitMatrix = new MultiFormatWriter().encode(boleto.getCodigoDeBarras(), BarcodeFormat.CODE_128, 400, 50);
                BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(barcodeImage, "png", baos);
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, baos.toByteArray(), "barcode");
                content.drawImage(pdImage, (pageWidth - 400) / 2, y);

                y -= 60;
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(50, y);
                content.showText("Código de Barras: " + boleto.getCodigoDeBarras());
                content.endText();
            }

            document.save(new File(nomeArquivoPdf));
            System.out.println("PDF gerado: " + nomeArquivoPdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
