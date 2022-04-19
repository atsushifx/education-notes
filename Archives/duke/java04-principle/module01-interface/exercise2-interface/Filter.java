/**
 * Write a description of interface Filter here.
 * 
 * @author  Atsushi Furukawa 
 * @version 1.0.0
 */
public interface Filter
{
    public boolean satisfies(QuakeEntry qe);
    
    public String getName();
}
