package com.ibm.app.utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ImageFileUtility
{
	final static Logger logger = Logger.getLogger(ImageFileUtility.class);

	public static void resizeImage(File inputImageFile, String outputImagePath, int width, int height)
	        throws IOException
	{
		logger.debug("ImageFileUtility.resizeImage()111111"); //$NON-NLS-1$
		BufferedImage inputImage = ImageIO.read(inputImageFile);
		BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, width, height, null);
		g2d.dispose();
		logger.debug("ImageFileUtility.resizeImage()2222222"); //$NON-NLS-1$
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1); //$NON-NLS-1$
		logger.debug("output image path :::"+outputImagePath); //$NON-NLS-1$
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
		logger.debug("small image created..."); //$NON-NLS-1$
	}

	public static File copyAndSaveProductImage(File sourceFile, String productId, int width, int height)
	{
		logger.debug("ImageFileUtility.copyAndSaveProductImage() ... starting"); //$NON-NLS-1$
		// If directory missing the create it .
		 File directory = new File(Messages.getString("ImageFileUtility.product-image-location") + productId + "\\"); //$NON-NLS-1$ //$NON-NLS-2$
		 File smallFile = new File(directory + Messages.getString("ImageFileUtility.image-small-prefix")); //$NON-NLS-1$
		if (!directory.exists()) 
		{
			directory.mkdir();
			logger.debug("ImageFileUtility.copyAndSaveProductImage()-- createdirectory"); //$NON-NLS-1$
		}
		try {
			FileUtils.copyFile(sourceFile,new File(directory+Messages.getString("ImageFileUtility.image-large-prefix")+sourceFile.getName())); //$NON-NLS-1$
			logger.debug("ImageFileUtility.copyAndSaveProductImage()... large file is copied"); //$NON-NLS-1$

			logger.debug("small file:"+smallFile); //$NON-NLS-1$
			try
			{
			resizeImage(sourceFile, smallFile.getPath()+Messages.getString("ImageFileUtility.13")+sourceFile.getName(), width, height); //$NON-NLS-1$
			}
			catch(Exception ex)
			{
				logger.debug(ex);
			}
			logger.debug(Messages.getString("ImageFileUtility.14")); //$NON-NLS-1$
		}
		catch (IOException ex) {
			logger.debug(Messages.getString("ImageFileUtility.15") + ex.getMessage()); //$NON-NLS-1$
		}

		return new File(smallFile.getPath()+Messages.getString("ImageFileUtility.16")+sourceFile.getName()); //$NON-NLS-1$

	}
}