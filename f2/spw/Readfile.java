
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Readfile{
		
	String path = "C:\\Users\\panar_000\\Desktop\\spw\\scoretime.txt";
	File file = new File(path);
	String line,tmp;
	
	public void readfile(){
	
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));			
			while ((tmp = br.readLine()) != null) {
				line = tmp;
			}		
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String gbt(){
		return line;
	}
	
}