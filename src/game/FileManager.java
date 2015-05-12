package game;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class FileManager {
	
	BufferedOutputStream bos;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	File base;
	String options = "options.opt";
	
	
	public FileManager(){
		String os = System.getProperty("os.name").toLowerCase();
		File root = new File(System.getProperty("user.home"));
		while(root.getParent() != null)root = new File(root.getParent());
		if(os.indexOf("win") >= 0){
			base = new File(System.getProperty("user.home")+
					System.getProperty("file.separator")+"Documents"+
					System.getProperty("file.separator")+"SavedGames"+
					System.getProperty("file.separator")+"PKS"+
					System.getProperty("file.separator"));
		}else if(os.indexOf("mac") >= 0){
			base = new File(root,"Library"+System.getProperty("file.separator")+
					"Application Support"+System.getProperty("file.separator")+
					"PKS"+System.getProperty("file.separator"));
		}else if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 ){
			base = new File(root,".PKS"+System.getProperty("file.separator"));
		}
		if(!base.exists())base.mkdirs();
	}
	
	public void saveImage(BufferedImage img,String url){
		File f = new File(base,url+".png");
		try {
			f.createNewFile();
			ImageIO.write(img,"png",f);
			System.out.println("[SAV]Saved image at "+f.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("[SAV]Failed to save at "+f.getAbsolutePath());
			System.err.println(e.getLocalizedMessage());
		}
		finally{
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveGame(Game g, String file){
		File f = new File(base,file);
		try {
			if(!f.exists())f.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(f));
			oos = new ObjectOutputStream(bos);
			oos.writeObject(g);
			oos.flush();
			bos.write(new byte[]{0});
			System.out.println("[SAV]Saved at "+f.getAbsolutePath());
			oos.close();
			bos.close();
		} catch (IOException e) {
			System.err.println("[SAV]Failed to save at "+f.getAbsolutePath());
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Game loadGame(String file){
		Game g = null;
		File f = new File(base,file);
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			g = (Game)ois.readObject();
			System.out.println("[SAV]Loaded at "+f.getAbsolutePath());
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("[SAV]Failed to load at "+f.getAbsolutePath());
			System.err.println(e.getLocalizedMessage());
		}
		finally{
			try {
				if(ois!=null)ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return g;
	}
	
	public boolean exist(String file){
		return new File(base,file).exists();
	}
	
	public boolean optionsExist(){
		return new File(base,options).exists();
	}
	
	public void saveOptions(Options o){
		File f = new File(base,options);
		try {
			if(!f.exists())f.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(f));
			oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			oos.flush();
			bos.write(new byte[]{0});
			System.out.println("[OPT]Saved at "+f.getAbsolutePath());
			oos.close();
			bos.close();
		} catch (IOException e) {
			System.err.println("[OPT]Failed to save at "+f.getAbsolutePath());
			System.err.println(e.getLocalizedMessage());
		}
		finally{
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Options loadOptions(){
		Options o = null;
		File f = new File(base,options);
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			o = (Options)ois.readObject();
			System.out.println("[OPT]Loaded at "+f.getAbsolutePath());
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("[OPT]Failed to load at "+f.getAbsolutePath());
			System.err.println(e.getLocalizedMessage());
		}
		finally{
			try {
				if(ois!=null)ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return o;
	}
}
