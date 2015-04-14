
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writefile{
	
	String path = "C:\\Users\\panar_000\\Desktop\\spw\\scoretime.txt";
	File file = new File(path);
	
	private String score; 
	
	FileWriter writer;
	
	public void write(String score){
		this.score = score;		
		try {
			
			writer = new FileWriter(file, false);  //True = Append to file, false = Overwrite
			writer.write(score);
			
			writer.close();
			
			System.out.println("Write success!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}