package com.kascend.music2.api3.service.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.launcher.CommandLauncher;
import org.apache.commons.exec.launcher.CommandLauncherFactory;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.service.util.Constants;

public class ImageUtil {

	private static final Logger log = Logger.getLogger(ImageUtil.class);

	public static String getFormatName(Object o) {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(o);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				return null;
			}
			ImageReader reader = iter.next();

			iis.close();
			return reader.getFormatName();
		} catch (IOException e) {

		}
		return null;
	}

	public static String scalePic(String sourcePath, String destPath, int new_width, int new_height,
			boolean proportion, String imageMagicDir) throws IOException, InterruptedException {
		try {
			File imgFile = new File(sourcePath);
			BufferedImage info = ImageIO.read(imgFile);
			int w = info.getWidth();
			int h = info.getHeight();
			if (w < new_width || h < new_height) {
//				FileUtils.copyFile(imgFile, new File(destPath));
//				return destPath;
				return null;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IOException(e.getMessage(), e);
		}

		CommandLine cmd = new CommandLine(imageMagicDir);
		cmd.addArgument("-strip");
		cmd.addArgument("-quality");
		cmd.addArgument("90");
		cmd.addArgument("-resize");
		cmd.addArgument(new_width + "x" + new_height);
		cmd.addArgument(sourcePath);
		cmd.addArgument(destPath);
		CommandLauncher commandLauncher = CommandLauncherFactory.createVMLauncher();
		Process p = commandLauncher.exec(cmd, null);
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(bStream);
		streamHandler.setProcessErrorStream(p.getErrorStream());

		streamHandler.setProcessOutputStream(p.getInputStream());
		streamHandler.start();
		streamHandler.stop();

		bStream.close();

		return destPath;
	}
	
	public static String generateSmallImg(String filePath, int width, int height, String imageMagicBin, boolean override){
		try {
			String sufix=generateSmallImgSuffix(filePath, width,height);
			String destFilePath=filePath+sufix;
			if(!override){
				if(filePath.lastIndexOf(sufix)!=-1 || new File(destFilePath).exists()){
					return destFilePath;
				}
			}
			
			log.debug("Scale img: "+filePath);
			return scalePic(filePath, destFilePath, width, height, true, imageMagicBin);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	public static String generateSmallImgSuffix(String filePath, int width, int height){
		String suffix="."+width+"x"+height+filePath.substring(filePath.lastIndexOf("."));
		return suffix;
	}
	
	public static String generateSmallImg(String filePath, int width, int height, String imageMagicBin){
		return generateSmallImg(filePath, width, height, imageMagicBin, false);
	}
	
	public static void doDirImg(File dir, String imageMagicBin, final int width, final int height, boolean override, final String filter){
		if(!dir.exists()){
			System.out.println("dir not exists");
			return;
		}
		File[] files=dir.listFiles(new FileFilter(){

			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory()){
					return true;
				}else{
					String filePath=pathname.getAbsolutePath();
					String sufix="."+width+"x"+height+filePath.substring(filePath.lastIndexOf("."));
					if(filePath.lastIndexOf(sufix)!=-1){
						return false;
					}else if(filter!=null && filePath.matches(filter)){
						return false;
					}else if(pathname.getName().toLowerCase().matches(".*\\.(jpg|png|gif)")){
						return true;
					}
				}
				return false;
			}});
		for(File file: files){
			if(file.isDirectory()){
				doDirImg(file, imageMagicBin, width, height, override, filter);
			}else{
				String filePath=file.getAbsolutePath();
				generateSmallImg(filePath, width, height, imageMagicBin, override);
			}
		}
	}
	public static String saveNativeImageByWidthAndHeight(String type,String imageUri,int width, int height){
		String relativePath = "/"
				+ type + "/" + DateUtil.getCurrentDate("yyyyMMdd/HH");
			
		String imgDir=Configer.getValueString(Constants.IMG_FILE_PATH);
		File newfile = new File(imgDir+relativePath);
		if (newfile != null && !newfile.exists()) {
			newfile.mkdirs();
		}
		long currentTime = new Date().getTime();
		int random = RandomUtils.nextInt(10000);
		relativePath += "/" + currentTime + random + ".jpg";
		String imgPath=downloadNativeFile(imageUri,imgDir+relativePath);
		log.debug("download image: " +imgPath);
		if(imgPath!=null){
			
			if(width==0 || height==0){
				File imgFile = new File(imgPath);
				try {
					BufferedImage  info = ImageIO.read(imgFile);
					if(null!=info){
						int w = info.getWidth();
						int h = info.getHeight();
						if(w < width|| h < height){
							imgFile.delete();
							return null;
						}
						info.flush();
					}
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
			
			if(Constants.OS_WIN){
				if(HttpUtil.uploadFileToServer(imgPath, relativePath)==0){
					return relativePath;
				}else{
					return null;
				}
			}else{
				return relativePath;
			}
		}else{
			return null;
		}
	}	
	
	public static String downloadNativeFile(String imageUri,String destPath){
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(destPath);
			FileInputStream reader =new FileInputStream(imageUri);
			byte buf[]=new byte[1024];
			int size=0;
			while ((size=reader.read(buf))!=-1) {
				fos.write(buf, 0, size);
			}
		} catch (Exception e) {
			destPath = null;
			log.error(e.getMessage(), e);
		}finally{
			try {
				if(fos!=null){
					fos.close();
					fos=null;
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return destPath;
	}
	
	public static String saveImageByWidthAndHeight(String type, String uri, int width,int height){
		
		String relativePath = "/"
			+ type + "/" + DateUtil.getCurrentDate("yyyyMMdd/HH");
		
		String imgDir=Configer.getValueString(Constants.IMG_FILE_PATH);
		
		File file = new File(imgDir+relativePath);
		if (file != null && !file.exists()) {
			file.mkdirs();
		}
		long currentTime = new Date().getTime();
		int random = RandomUtils.nextInt(10000);
		relativePath += "/" + currentTime + random + ".jpg";
		
		String imgPath = HttpUtil.downloadFile(uri, imgDir+relativePath);
		log.debug("download image: " +imgPath);
		if(imgPath!=null){
			
			if(width==0 || height==0){
				File imgFile = new File(imgPath);
				try {
					BufferedImage  info = ImageIO.read(imgFile);
					if(null!=info){
						int w = info.getWidth();
						int h = info.getHeight();
						if(w < width|| h < height){
							imgFile.delete();
							return null;
						}
						info.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(Constants.OS_WIN){
				if(HttpUtil.uploadFileToServer(imgPath, relativePath)==0){
					return relativePath;
				}else{
					return null;
				}
			}else{
				return relativePath;
			}
		}else{
			return null;
		}
	}
	public static String saveImage(String type, String uri){
		
		return saveImageByWidthAndHeight(type,uri,0,0);
	}
	public static String saveSmallImage(String relativePath, int width, int height){
		
		String imgDir=Configer.getValueString(Constants.IMG_FILE_PATH);
		
		String smallImgPath=ImageUtil.generateSmallImg(imgDir+relativePath
				, width
				, height
				, Configer.getValueString(Constants.IMAGE_MAGIC_BIN), false);
		
		if(smallImgPath!=null){
			String path=smallImgPath.replace(imgDir, "");
			path=path.replace("\\", "/");
			
			if(Constants.OS_WIN){
				if(HttpUtil.uploadFileToServer(smallImgPath, path)==0){
					return path;
				}else{
					return null;
				}
			}else{
				return path;
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		
		String dirPath=args[0];
		String imageMagicBin=args[1];
		int width=Integer.parseInt(args[2]);
		int height=Integer.parseInt(args[3]);
		boolean override=Boolean.parseBoolean(args[4]);
		String filter=null;
		if(args.length==6){
			filter=args[5];
		}
		File dir=new File(dirPath);
		doDirImg(dir, imageMagicBin, width, height, override, filter);
		System.out.println("Scale img finished");
	}

}
