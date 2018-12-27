package com.game.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.pdf.PdfReader;

/**
 * pdf工具类
 * 
 * @author John
 */
public class Pdf2Image {

	private final static Integer DPI = 300;

    public static void main(String[] args) {
        //pdf2Image("C:\\Users\\admin\\Desktop\\滴滴电子发票0811.pdf", "D:/pdf", 300);
    	File pdf = new File("d:/aaaa.pdf");
    	pdf2Png(pdf);
    }

    /**
     * pdf转png
     * 
     * @param pdf
     * @return
     */
	public static File pdf2Png(File pdf) {
		PDDocument pdDocument = null;
		File tempPng = null;
		try {
			tempPng = File.createTempFile("einvoice_", ".png");
			System.out.println("pdf转png的临时png文件路径：" + tempPng.getAbsolutePath());
			// 程序结束删除临时文件
			tempPng.deleteOnExit();
			pdDocument = PDDocument.load(pdf);
			PDFRenderer renderer = new PDFRenderer(pdDocument);
			/* dpi越大转换后越清晰，相对转换速度越慢 */
			InputStream inStream = new FileInputStream(pdf);
			PdfReader pdfReader = new PdfReader(inStream);

			int pages = pdfReader.getNumberOfPages();
			for (int i = 0; i < pages; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, DPI);
				ImageIO.write(image, "png", tempPng);
			}
			System.out.println("pdf转png的临时png文件路径：" + tempPng.getAbsolutePath());
			System.out.println("pdf转png的临时png文件名称：" + tempPng.getName());
			pdDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempPng;
	}

    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param pdfFilePath pdf完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     * @return
     */
    public static void pdf2Image(String pdfFilePath, String dstImgFolder, int dpi) {
        File file = new File(pdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }
            if (createDirectory(imgFolderPath)) {
                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                /* dpi越大转换后越清晰，相对转换速度越慢 */
                PdfReader reader = new PdfReader(pdfFilePath);
                int pages = reader.getNumberOfPages();
                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".png");
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);
                }
                System.out.println("PDF文档转PNG图片成功！");
            } else {
                System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

}
