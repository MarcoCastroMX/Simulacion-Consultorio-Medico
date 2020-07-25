import java.io.*;

public class  AppendObject extends ObjectOutputStream
{
    @Override
    protected void writeStreamHeader() throws IOException {
        
    }
    public AppendObject() throws IOException {
        super();
    }
    public AppendObject(OutputStream out) throws IOException {
        super(out);
    } 
}
